package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * the class as represented in the practices
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
