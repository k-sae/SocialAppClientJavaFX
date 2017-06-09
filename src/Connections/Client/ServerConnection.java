package Connections.Client;


import Connections.Connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by kemo on 28/10/2016.
 */
public abstract class ServerConnection extends BasicConnection implements Connection {
    private int readTimeout;
    public ServerConnection(int startPort)
    {
        super();
        readTimeout = 5000;
        customPorts.add(startPort);
    }
    public void connect(String serverName) throws ServerNotFound
    {
        port = -1;
        this.serverName = serverName;
        triggerStartingConnection();
        while(port == -1) {
            findPort(0);
        }
        startConnection();
    }
    public ServerConnection connect() throws ServerNotFound
    {
        port = -1;
        triggerStartingConnection();
        while(port == -1) {
            findPort(0);
        }
        startConnection();
        return this;
    }

   public ServerConnection(String serverName, int startPort) throws ServerNotFound {
        this(startPort);
        this.serverName = serverName;
    }
    private void findPort(int i)
    {
        if (i == customPorts.size()) return ;
        try {
            connectionSocket = new Socket();
            connectionSocket.connect(new InetSocketAddress(serverName,customPorts.get(i)), timeout);
            //verify if the socket found is the desired socket
            verifyConnection();
            port = customPorts.get(i);
            triggerConnectionStarted();
            connectionSocket.setSoTimeout(readTimeout);
        }
        catch (ConnectException e)
        {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            findPort(i + 1);
        }
        catch (IOException e) {
            findPort(i + 1);
        }
        catch (Exception e)
        {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public ArrayList<Integer> getCustomPorts() {
        return customPorts;
    }
    public void endConnection()
    {
        try {
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        try {
            connectionSocket.setSoTimeout(readTimeout);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.readTimeout = readTimeout;
    }
}
