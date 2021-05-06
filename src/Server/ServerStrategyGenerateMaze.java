package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(new MyCompressorOutputStream(outToClient));

            int[] clientdimensions= (int[]) fromClient.readObject();
            MyMazeGenerator mg = new MyMazeGenerator();
            Maze toclientMaze = mg.generate(clientdimensions[0],clientdimensions[1]);
            //printing maze for tests only
            toclientMaze.superprint();



            toClient.write(toclientMaze.toByteArray());
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

