package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * the class that we use to retrieve the users data that he wants us to use, this class is implemented using the singleton desigh
 * instance - the status of the singelton class
 * configfile - the configuration file we retrieve data from
 * properties - an object of type propery that we store the properties of the user
 * Generators - a hashmap that contains the two options of generators we implemented
 * Searchers - a hashmap that contains the three options of searchers we implemeted
 */
public class Configurations {
    private static Configurations instance = null;
    static private Properties properties = new Properties();
    static private InputStream configfile = null;
    static private HashMap<String, AMazeGenerator> Generators;
    static private HashMap<String, ASearchingAlgorithm> Searchers;

    /**
     * when initialized the static class reads the data from the configuration file
     */
    public Configurations() {
        try {
            configfile = new FileInputStream("resources/config.properties");
            properties.load(configfile);
            Initialize();
            setProp(2, "MyMazeGenerator", "DepthFirstSearch");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Configurations getInstance(){
        if (Configurations.instance == null){
            Configurations.instance = new Configurations();
        }
        return Configurations.instance;
    }

    /**
     * the method intializes all data members
     */
    private static void Initialize(){
      //  try{
           // Properties properties = new Properties();
           // InputStream configfile = new FileInputStream("resources/config.properties");
            Generators = new HashMap<>();
            Searchers = new HashMap<>();

            Generators.put("EmptyMazeGenerator", new EmptyMazeGenerator());
            Generators.put("SimpleMazeGenerator", new SimpleMazeGenerator());
            Generators.put("MyMazeGenerator", new MyMazeGenerator());

            Searchers.put("BestFirstSearch", new BestFirstSearch());
            Searchers.put("BreadthFirstSearch", new BreadthFirstSearch());
            Searchers.put("DepthFirstSearch", new DepthFirstSearch());
            setProp(4, "MyMazeGenerator", "BestFirstSearch");

    }
    public static void setProp(int size, String Generator, String Searcher) {

        try{
            OutputStream output = new FileOutputStream("resources/config.properties");
            Properties prop = new Properties();
            prop.setProperty("threadPoolSize", String.valueOf(size));
            prop.setProperty("mazeGeneratingAlgorithm", Generator);
            prop.setProperty("mazeSearchingAlgorithm", Searcher);
            prop.store(output, null);
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this are all getter functions to retrieve the data from the file
     */
    public static int getThreadPoolSize() {

        return Integer.valueOf(properties.getProperty("threadPoolSize"));
    }

    public static AMazeGenerator getMazeGeneratingAlgorithm() {
        String Generator = properties.getProperty("mazeGeneratingAlgorithm");
        return Generators.get(Generator);
    }

    public static ASearchingAlgorithm getMazeSearchingAlgorithm(){
        String Searcher = properties.getProperty("mazeSearchingAlgorithm");
        return  Searchers.get(Searcher);
    }


}

