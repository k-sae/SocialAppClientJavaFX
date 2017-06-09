package Connections.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static Connections.Connection.VERIFICATION;

/**
 * Created by kemo on 02/02/2017.
 */
class BasicConnection {
    private ArrayList<ConnectionListener> connectionListeners;
    int port;
    String serverName;
    private String verification;


    Socket connectionSocket;
    ArrayList<Integer> customPorts;
    int timeout;
    BasicConnection() {
        connectionListeners = new ArrayList<>();
        verification = VERIFICATION;
        customPorts = new ArrayList<>();
        timeout = 1500;
    }
    void triggerStartingConnection()
    {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < connectionListeners.size(); i++) {
            connectionListeners.get(i).onStart();
        }
    }
    void triggerConnectionStarted()
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
    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
    void verifyConnection() throws IOException {
        try {
            connectionSocket.setSoTimeout(200); //set time out for reading input
            DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
            byte[] b = new byte[verification.length()];
            dataInputStream.readFully(b);//reading in bytes format because i cant make sure of the data coming from other sockets
            if (!(new String(b).equals(verification))) throw new IOException("wrong verification code"); //throw exception if code is wrong
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public void setDuePort(int duePort) {
        if (duePort < 0) throw new NumberFormatException("Invalid negative number duePort = " + duePort);
        for (int i = customPorts.get(0); i < duePort; i++) {
            customPorts.add(i);
        }
    }
    public Socket getConnectionSocket() {
        return connectionSocket;
    }

}
