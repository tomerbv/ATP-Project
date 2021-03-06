package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * the client class as shown in the practices
 */
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer(){
        try{
            Socket serverSocket = new Socket(serverIP, serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
