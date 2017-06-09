package Connections.Client;

import java.io.IOException;

/**
 * Created by kemo on 20/12/2016.
 */
public class ServerNotFound extends IOException {
    public ServerNotFound()
    {
        super("Server Not Found");
    }
}
