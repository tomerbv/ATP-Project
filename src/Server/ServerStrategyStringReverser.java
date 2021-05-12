package Server;

import java.io.*;
import java.nio.channels.Channels;

public class ServerStrategyStringReverser implements IServerStrategy {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        // The Streams from Channels are interruptible,
        // so we decorate our input stream even more to enable it to also be interruptible:
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(interruptibleInputStream));
        BufferedWriter toClient = new BufferedWriter(new PrintWriter(outToClient));

        String clientCommand;
        try {
            while (fromClient != null && !(clientCommand = fromClient.readLine()).toLowerCase().equals("exit")) {
                Thread.sleep(2000);
                String reversed = new StringBuilder(clientCommand).reverse().toString();
                toClient.write(reversed + "\n");
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // if the current Thread is interrupted, we will exit the strategy
            // (our thread pool is shut down and terminated the running threads)
            e.printStackTrace();
        }
    }
}
