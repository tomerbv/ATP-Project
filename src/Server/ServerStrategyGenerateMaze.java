package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            if(inFromClient != null && outToClient != null){
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                MyCompressorOutputStream mycomp = new MyCompressorOutputStream(bytearray);
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

                int[] clientdimensions= (int[]) fromClient.readObject();
                MyMazeGenerator mg = new MyMazeGenerator();
                Maze toclientMaze = mg.generate(clientdimensions[0],clientdimensions[1]);
                mycomp.write(toclientMaze.toByteArray());
                toClient.writeObject(bytearray.toByteArray());
                mycomp.flush();
                toClient.flush();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

