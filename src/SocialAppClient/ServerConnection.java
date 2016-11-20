package SocialAppClient;

import SocialAppGeneral.Connection;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 28/10/2016.
 */
public abstract class ServerConnection implements Connection{
    protected int port;
    protected String serverName;
    protected Socket connectionSocket;
    //if no parameters passed set default connection
    //TODO #kareem
    //after creating users levels accept user of type registeredUser or login user

    public ServerConnection(String serverName, int port)
    {
        this.serverName = serverName;
        this.port = port;
        try {
            connectionSocket = new Socket(serverName, port);
        }
        catch (IOException e) {
            //TODO #Hazem
            //1-display an alert box to status non stable connection
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export it to log file
            System.out.println("StartConnection\t"+ e.getMessage());
        }

        startConnection();
    }

}
