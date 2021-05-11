package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the server class that is implemented very similar as in the pratics
 */
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private ExecutorService threadPool;
    private volatile boolean stop;


    /**
     * @param port - the port we are connecting to
     * @param listeningIntervalMS - time we will wait for a connection
     * @param strategy- the strategy that we will execut
     * threadpool - a threadpool that its size will be set by the input in the configurations file
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        Configurations config = Configurations.getInstance();
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(config.getThreadPoolSize());
    }

    /**
     * same as in the practices
     */
    public void start(){
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(listeningIntervalMS);


                while(!stop) {
                    try {
                        Socket clientSocket = serverSocket.accept();

                        threadPool.submit(() -> {
                            handleClient(clientSocket);
                        });

                    } catch (SocketTimeoutException e) {

                       // System.out.println("Socket Timeout");
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
