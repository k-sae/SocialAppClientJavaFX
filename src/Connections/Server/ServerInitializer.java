package Connections.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static Connections.Connection.VERIFICATION;

/**
 * Created by kemo on 12/12/2016.
 */
public abstract class ServerInitializer {
    private String verification;
    private ServerSocket serverSocket;
    public ArrayList<Integer> getCustomPorts() {
        return customPorts;
    }
    private ArrayList<Integer> customPorts;
    //set the max due port
    public void setDuePort(int duePort) {
        if (duePort < 0) throw new NumberFormatException("Invalid negative number duePort = " + duePort);
        for (int i = customPorts.get(0); i < duePort; i++) {
            customPorts.add(i);
        }
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public ServerInitializer(int startPort)
    {
        customPorts = new ArrayList<>();
        customPorts.add(startPort);
        verification = VERIFICATION;
    }
    public void startMainConnection() {
        for (Integer port: customPorts
             ) {
            try {
                //create a server socket where the client will connect on the specified startPort
                 serverSocket = new ServerSocket(port);
                //noinspection InfiniteLoopStatement
                while (true) { // loop where the server wait for client to start his connection may need to make these process in another thread
                    Socket client = serverSocket.accept();
                    sendVerificationCode(client);
                    onClientConnection(client);

                }
            } catch (IOException e) {
                //Error reporting 4 Debugging later will use log class
            }
        }
    }
    private void sendVerificationCode(Socket client)
    {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.write(verification.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public abstract void onClientConnection(Socket client);

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
