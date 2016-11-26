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

    public ServerConnection(String serverName, int startPort) throws Exception {
        this.serverName = serverName;
        findPort(startPort, startPort+100);
        if (port > -1) {
            startConnection();
            //TODO #kareem
            //create a class inheriting Exception do identify error
        }else throw new Exception("Server Not Found");
    }
    private void findPort(int sPort, int ePort)
    {
        if (sPort == ePort) return ;
        try {
            connectionSocket = new Socket(serverName, sPort);
            port = sPort;
        }
        catch (IOException e) {
            findPort(sPort + 1, ePort);
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export it to log file
            System.out.println("StartConnection\t"+ e.getMessage());
        }
    }

}
