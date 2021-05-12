package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * class represented the same way as the practices
 */
public interface IServerStrategy {
    void ServerStrategy(InputStream inFromClient, OutputStream outToClient);
}
