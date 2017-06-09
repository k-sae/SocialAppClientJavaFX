package Connections.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by kemo on 02/02/2017.
 */
public class ServerScanner extends BasicConnection {
    private ArrayList<ServerFoundListener> serverFoundListeners;
    public ServerScanner(int startPort) {
        customPorts.add(startPort);
        serverFoundListeners = new ArrayList<>();
    }

    public void isAvailable(String serverName) {
        for (int i = 0; i < customPorts.size(); i++) {

            try {
                connectionSocket = new Socket();
                connectionSocket.connect(new InetSocketAddress(serverName, customPorts.get(i)), timeout);
                //verify if the socket found is the desired socket
                verifyConnection();
//                connectionSocket.setSoTimeout(1000);
                triggerServerFoundListener(connectionSocket);
                CommandsExecutor.getInstance().add(new ConnectionRunnable() {
                    @Override
                    public void run() throws IOException {
                        connectionSocket.close();
                    }
                });

            } catch (Exception e) {
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    public void setServerFoundListener(ServerFoundListener serverFoundListener)
    {
        serverFoundListeners.add(serverFoundListener);
    }
    @SuppressWarnings("ForLoopReplaceableByForEach")
    private void triggerServerFoundListener(Socket socket)
    {
        for (int i = 0; i <serverFoundListeners.size() ; i++) {
            serverFoundListeners.get(i).uponConnection(socket);
        }
    }
}
