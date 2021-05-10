package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    private AMazeGenerator Generator;

    public ServerStrategyGenerateMaze(AMazeGenerator generator) {
        Generator = Configurations.getMazeGeneratingAlgorithm();
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            if(inFromClient != null && outToClient != null){
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                MyCompressorOutputStream mycomp = new MyCompressorOutputStream(bytearray);
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

                int[] clientdimensions= (int[]) fromClient.readObject();
                Maze toclientMaze = Generator.generate(clientdimensions[0],clientdimensions[1]);
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

