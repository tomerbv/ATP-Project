package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{


    public ServerStrategyGenerateMaze() {

    }

    /**
     * @param inFromClient input stream with the clients dimensions
     * @param outToClient  output stream to send the byte array to the client
     *  first we create all the decorators and streams need to retrieve the data and sent it back to the client
     *  then we read the object sent to us, we know this object is a array of ints of size 2(the dimensions wanted),
     *  after that we generate a maze using the algorithm specified by the user in the configurations file,finally we compress the maze
     *  and send it back.
     *
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            if(inFromClient != null && outToClient != null){
                ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                MyCompressorOutputStream mycomp = new MyCompressorOutputStream(bytearray);
                ObjectInputStream fromClient = new ObjectInputStream(inFromClient);

                int[] clientdimensions= (int[]) fromClient.readObject();
                Configurations config = Configurations.getInstance();
                AMazeGenerator Generator = null;
                Generator = config.getMazeGeneratingAlgorithm();
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

