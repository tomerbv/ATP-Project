package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private ASearchingAlgorithm Searcher;
    private String DirPath;

    public ServerStrategySolveSearchProblem() {
        this.Searcher = Configurations.getMazeSearchingAlgorithm();
        this.DirPath = System.getProperty("java.io.tmpdir");
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze clientmaze = (Maze) fromClient.readObject();

            Solution solution = GetSolution(clientmaze);

            toClient.writeObject(solution);
            toClient.flush();

            fromClient.close();
            toClient.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Solution GetSolution(Maze clientmaze) {
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
                        ObjectInputStream input = new ObjectInputStream(new FileInputStream(DirPath + "Solution:R" + row + ",C" + col + ",SR" + startrow + ",SC" + startcol + ",GR" + goalrow + ",GC" + goalcol + "-num:" + i));
                        return (Solution) input.readObject();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            i++;
            file = new File(FilePath + i);
        }
        if(solution == null){
            ISearchable iSearchable = new SearchableMaze(clientmaze);
            ASearchingAlgorithm usedAlgorithm = (ASearchingAlgorithm)Searcher;
            solution = usedAlgorithm.solve(iSearchable);
            saveSolution(solution, clientmaze, i);
        }
        return solution;
    }


    private void saveSolution(Solution solution, Maze maze, int i) {
        int row = maze.getRows();
        int col = maze.getColumns();
        int startrow = maze.getStartPosition().getRowIndex();
        int startcol = maze.getStartPosition().getColumnIndex();
        int goalrow = maze.getGoalPosition().getRowIndex();
        int goalcol = maze.getGoalPosition().getColumnIndex();

        String fileName = "R" + row + ",C" + col + ",SR" + startrow + ",SC" + startcol + ",GR" + goalrow + ",GC" + goalcol + "-num:" + i;
        try {
            File MazeFile = new File(DirPath + "Maze:" + fileName);
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(MazeFile));
            out.write(maze.toByteArray());
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            File SolutionFile = new File(DirPath + "Solution:" + fileName);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SolutionFile));
            out.writeObject(solution);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
