package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Properties;

static public class Configurations {
    static private Properties properties = new Properties();;
    static private InputStream input = null;
    static private HashSet<String, ASearchingAlgorithm> mazeSearchingAlgorithmMap;
    static private HashSet<String, AMazeGenerator> mazeGeneratingAlgorithmMap;

    public static void setProp(int size, String mazeGeneratingAlgorithm, String mazeSearchingAlgorithm) {
        try {
            OutputStream output = new FileOutputStream("resource/config.properties");
            Properties prop = new Properties();
            prop.setProperty("threadPoolSize", Integer.toString(size));
            prop.setProperty("mazeGeneratingAlgorithm", mazeGeneratingAlgorithm);
            prop.setProperty("mazeSearchingAlgorithm", mazeSearchingAlgorithm);
            prop.store(output, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void initializeSearchingAlgorithmMap(){
        mazeSearchingAlgorithmMap = new HashMap<>();
        mazeSearchingAlgorithmMap.put("BreadthFirstSearch", new BreadthFirstSearch());
        mazeSearchingAlgorithmMap.put("BestFirstSearch", new BestFirstSearch());
        mazeSearchingAlgorithmMap.put("DepthFirstSearch", new DepthFirstSearch());
    }

    private static void initializeGeneratingAlgorithmMap(){
        mazeGeneratingAlgorithmMap = new HashMap<>();
        mazeGeneratingAlgorithmMap.put("MyMazeGenerator", new MyMazeGenerator());
        mazeGeneratingAlgorithmMap.put("SimpleMazeGenerator", new SimpleMazeGenerator());
    }

    public static int getThreadPoolSize() {
        try {
            if (Integer.valueOf(prop.getProperty("threadPoolSize")) > 0)
                return Integer.valueOf(prop.getProperty("threadPoolSize"));
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public static ASearchingAlgorithm getMazeSearchingAlgorithm(){
        try {
            String mazeSearchingAlgorithmName = prop.getProperty("mazeSearchingAlgorithm");
            if(mazeSearchingAlgorithmMap.containsKey(mazeSearchingAlgorithmName))
                return (ASearchingAlgorithm)mazeSearchingAlgorithmMap.get(mazeSearchingAlgorithmName).cloneMe();
            return new BreadthFirstSearch(); // default...
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new BreadthFirstSearch(); // default...
    }

    public static AMazeGenerator getMazeGeneratingAlgorithm(){
        try {
            String mazeGeneratingAlgorithmName = prop.getProperty("mazeGeneratingAlgorithm");
            if(mazeGeneratingAlgorithmMap.containsKey(mazeGeneratingAlgorithmName))
                return (AMazeGenerator) mazeGeneratingAlgorithmMap.get(mazeGeneratingAlgorithmName).cloneMe();
            return new MyMazeGenerator(); // default...
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new MyMazeGenerator(); // default...
    }


}

