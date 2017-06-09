package Connections.Client;

import java.net.Socket;

/**
 * Created by kemo on 02/02/2017.
 */
public interface ServerFoundListener {
    void uponConnection(Socket server);
}
