package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream bytearray= new ByteArrayOutputStream();
            MyCompressorOutputStream mycomp = new MyCompressorOutputStream(bytearray);
            int[] clientdimensions= (int[]) fromClient.readObject();
            MyMazeGenerator mg = new MyMazeGenerator();
            Maze toclientMaze = mg.generate(clientdimensions[0],clientdimensions[1]);

            //printing maze for tests only
            toclientMaze.superprint();



            mycomp.write(toclientMaze.toByteArray());
            toClient.writeObject();
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

