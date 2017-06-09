package SocialAppClient.Control.Connections;

import Connections.Client.ServerConnection;
import Connections.Client.ServerNotFound;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public class MainServerConnection extends ServerConnection {
    public static Socket mainConnectionSocket;
    private static boolean isActiveConnection;
    public MainServerConnection() throws Exception {
        super("127.0.0.1",6000);
    }
    public void reconnect()
    {
        try {
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ServerConnection connect() throws ServerNotFound {
        if (mainConnectionSocket !=null)
        {
            if (mainConnectionSocket.isClosed())
            {
                super.connect();
            }
        }
        else if (!isActiveConnection)
        {
            isActiveConnection = true;
           super.connect();
        }
        return this;
    }
    private void start() throws ServerNotFound {
        //here i will check for user info and choose whether to continue the connection or to end it
    }
    public void endConnection()
    {
        if (mainConnectionSocket != null)
        {
            try {
                mainConnectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startConnection() {
        mainConnectionSocket = getConnectionSocket();
        isActiveConnection = false;
    }
}
