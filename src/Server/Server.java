package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ExecutorService threadPool;
    private volatile boolean stop;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getThreadPoolSize());
    }

    public void start(){
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(listeningIntervalMS);
                System.out.println("Starting server at port = " + port);

                while(!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client accepted: " + clientSocket.toString());
                        threadPool.submit(() -> {
                            handleClient(clientSocket);
                        });

                    } catch (SocketTimeoutException e) {
                        System.out.println("Socket Timeout");
                    }
                }
                serverSocket.close();
                threadPool.shutdownNow();
            }
            catch (IOException e) {
                e.printStackTrace();
            }}).start();
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }
}
