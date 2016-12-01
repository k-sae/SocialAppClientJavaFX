package SocialAppClient;

import SocialAppGeneral.Connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by kemo on 28/10/2016.
 */
abstract class ServerConnection implements Connection{
    private int port;
    private String serverName;
    Socket connectionSocket;
    //if no parameters passed set default connection
    //TODO #kareem
    //after creating users levels accept user of type registeredUser or login user

    ServerConnection(String serverName, int startPort) throws Exception {
        this.serverName = serverName;
        findPort(startPort, startPort+10);
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
            //verify if the socket found is the desired socket
            verifyConnection();
            port = sPort;
            connectionSocket.setSoTimeout(500);
        }
        catch (IOException e) {
            System.out.println("cant find socket on "+ sPort);
            findPort(sPort + 1, ePort);
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export it to log file
            System.out.println("StartConnection\t"+ e.getMessage());
        }
    }
    private void verifyConnection() throws IOException {
        try {
            connectionSocket.setSoTimeout(200); //set time out for reading input
            DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
            byte[] b = new byte[VERIFICATION.length()];
            dataInputStream.readFully(b);//reading in bytes format because i cant make sure of the data coming from other sockets
            if (!(new String(b).equals(VERIFICATION))) throw new IOException("wrong verification code"); //throw exception if code is wrong
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
