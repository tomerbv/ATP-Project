package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * the strategy that represents the server strategy when the client sends a maze that it needs to solve
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private String DirPath;


    /**
     * the constructor
     * Searcher - the algorithm that we use to solve the maze; we retrieve this data from the configuration file with the users request
     * DirPath - the path of the current directory in the system
     */
    public ServerStrategySolveSearchProblem() {


        this.DirPath = System.getProperty("java.io.tmpdir");
    }

    /**
     * @param inFromClient the client request( a Maze object)
     *
     * @param outToClient the servers return (a Solutio object)
     * we override the method of the IServerStrategy interface. in this strategy we
     * we retrieve the maze sent with the ObjectInputStream decorator and the use the GetSolution function to check
     * check if we have solved this maze and returns it if we have ,if not solves it saves it and returns it
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            Maze clientmaze = (Maze) fromClient.readObject();

            Solution solution = GetSolution(clientmaze);

            toClient.writeObject(solution);
            toClient.flush();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param clientmaze the maze the client wants us to solve
     * @return an object of type solution that holds the solution of the maze
     * in this methos first we retrieve all the meta data from the maze and we build its filename according to our saving strategy
     * then we check if there is a maze with this name(meaning it has the same metadata at least) then we check all other filenames with
     * the different indicators to check if the content is the same(meaning it is the same maze), if it is we return the
     * soultion we saved , and if its not we save this maze , solve it and the save the solution , and then return this solution.
     */
    private Solution GetSolution(Maze clientmaze) {


        Configurations config = Configurations.getInstance();
        int row = clientmaze.getRows();
        int col = clientmaze.getColumns();
        int startrow = clientmaze.getStartPosition().getRowIndex();
        int startcol = clientmaze.getStartPosition().getColumnIndex();
        int goalrow = clientmaze.getGoalPosition().getRowIndex();
        int goalcol = clientmaze.getGoalPosition().getColumnIndex();
        int i = 0;
        Solution solution = null;
        String FilePath = DirPath + "Maze:R" + row + ",C" + col + ",SR" + startrow + ",SC" + startcol + ",GR" + goalrow + ",GC" + goalcol + "-num:";
        File file = new File(FilePath + i);
        while(file.exists())
        {
            try {
                byte[] clientmazetobyte = clientmaze.toByteArray();
                byte[] decompressedtobytes = new byte[clientmazetobyte.length];
                MyDecompressorInputStream filepathdecompress = new MyDecompressorInputStream(new FileInputStream(FilePath));
                filepathdecompress.read(decompressedtobytes);
                if(Arrays.equals(clientmazetobyte, decompressedtobytes))
                {
                    try {
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream(DirPath +  "Solution:R" + row + ",C" + col + ",SR" + startrow + ",SC" + startcol + ",GR" + goalrow + ",GC" + goalcol + "-num:" + i));
                        return (Solution) input.readObject();
                    }
                    catch (Exception e) {
                        e.printStackTrace();                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();            }
            i++;
            file = new File(FilePath + i);
        }
        if(solution == null){
            ISearchable iSearchable = new SearchableMaze(clientmaze);
            ASearchingAlgorithm usedAlgorithm = null;
            usedAlgorithm= config.getMazeSearchingAlgorithm();
            solution = usedAlgorithm.solve(iSearchable);
            saveSolution(solution, clientmaze, i);
        }
        return solution;
    }


    /**
     * @param solution the solution of the Maze maze
     * @param maze the maze we have solved
     * @param i indicator of mazes with same metadata but different contents
     * this method is implemented to save solutions of mazes and the new mazes
     * we create a new file for both the solution and the maze so we can access them if we need to solve the same maze again, we name the file b
     * by its meta data and indicator
     */
    private void saveSolution(Solution solution, Maze maze, int i) {


        int row = maze.getRows();
        int col = maze.getColumns();
        int startrow = maze.getStartPosition().getRowIndex();
        int startcol = maze.getStartPosition().getColumnIndex();
        int goalrow = maze.getGoalPosition().getRowIndex();
        int goalcol = maze.getGoalPosition().getColumnIndex();

        String fileName = "R" + row + ",C" + col + ",SR" + startrow + ",SC" + startcol + ",GR" + goalrow + ",GC" + goalcol + "-num:" + i;
        try {
            File MazeFile = new File(DirPath +"Maze:" + fileName);
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(MazeFile));
            out.write(maze.toByteArray());
            out.flush();
        }
        catch (Exception e) {
            System.out.println("maze not saved");
            e.printStackTrace();        }
        try {
            File SolutionFile = new File(DirPath  + "Solution:" + fileName);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SolutionFile));
            out.writeObject(solution);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            System.out.println("solution not saved");
            e.printStackTrace();        }
    }
}
