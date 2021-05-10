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

public class Configurations {
    private static boolean instance = false;
    static private Properties properties;
    static private InputStream configfile;
    static private HashMap<String, AMazeGenerator> Generators;
    static private HashMap<String, ASearchingAlgorithm> Searchers;

    private static void Initialize(){
        try{
            Properties properties = new Properties();
            InputStream configfile = new FileInputStream("resource/config.properties");
            Generators = new HashMap<>();
            Searchers = new HashMap<>();

            Generators.put("EmptyMazeGenerator", new EmptyMazeGenerator());
            Generators.put("SimpleMazeGenerator", new SimpleMazeGenerator());
            Generators.put("MyMazeGenerator", new MyMazeGenerator());

            Searchers.put("BestFirstSearch", new BestFirstSearch());
            Searchers.put("BreadthFirstSearch", new BreadthFirstSearch());
            Searchers.put("DepthFirstSearch", new DepthFirstSearch());

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            instance = true;
        }

    }
    public static void setProp(int size, String Generator, String Searcher) {
        if (!instance)
            Initialize();
        try{
            OutputStream output = new FileOutputStream("resource/config.properties");
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

    public static int getThreadPoolSize() {
        if (!instance)
            Initialize();
        return Integer.valueOf(properties.getProperty("threadPoolSize"));
    }

    public static AMazeGenerator getMazeGeneratingAlgorithm() {
        if (!instance)
            Initialize();
        String Generator = properties.getProperty("mazeGeneratingAlgorithm");
        return Generators.get(Generator);
    }

    public static ASearchingAlgorithm getMazeSearchingAlgorithm(){
        if (!instance)
            Initialize();
        String Searcher = properties.getProperty("mazeSearchingAlgorithm");
        return  Searchers.get(Searcher);
    }


}

