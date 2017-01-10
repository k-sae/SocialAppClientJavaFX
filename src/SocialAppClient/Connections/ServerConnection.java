package SocialAppClient.Connections;


import SocialAppClient.SocialAppGeneral.Connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by kemo on 28/10/2016.
 */
public abstract class ServerConnection implements Connection{
    private int port;
    private String serverName;
    public Socket connectionSocket;
    private ArrayList<ConnectionListener> connectionListeners;
    public ServerConnection()
    {
        connectionListeners = new ArrayList<>();
    }
    public void connect(String serverName, int startPort) throws ServerNotFound
    {
        port = -1;
        this.serverName = serverName;
        triggerStartingConnection();
        while(port == -1) {
            findPort(startPort, startPort + 3);
        }
        startConnection();
    }
   public ServerConnection(String serverName, int startPort) throws ServerNotFound {
       connectionListeners = new ArrayList<>();
        connect(serverName,startPort);
    }
    private void findPort(int sPort, int ePort)
    {
        if (sPort == ePort) return ;
        try {
            connectionSocket = new Socket();
            connectionSocket.connect(new InetSocketAddress(serverName,sPort), 1500);
            //verify if the socket found is the desired socket
            verifyConnection();
            port = sPort;
            triggerConnectionStarted();
            connectionSocket.setSoTimeout(5000);
        }
        catch (IOException e) {

            findPort(sPort + 1, ePort);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    private void triggerStartingConnection()
    {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < connectionListeners.size(); i++) {
            connectionListeners.get(i).onStart();
        }
    }
    private void triggerConnectionStarted()
    {
        //noinspection ForLoopReplaceableByForEach
        for(int i = 0; i < connectionListeners.size(); i++) {
            connectionListeners.get(i).onConnectionSuccess();
        }
    }
    public void setConnectionListener(ConnectionListener connectionListener)
    {
        connectionListeners.add(connectionListener);
    }
}
