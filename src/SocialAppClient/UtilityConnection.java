package SocialAppClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public class UtilityConnection extends ServerConnection {
    public UtilityConnection(String id, int port) throws Exception {
        //TODO #config
        super("127.0.0.1",port);
        sendId(id);
    }
    public UtilityConnection(String senderId, int port, String receiverId) throws Exception
    {
        //TODO #config
        super("127.0.0.1",port);
        sendId(senderId);
        sendId(receiverId);
    }
    @Override
    public void startConnection() {
        //start listening to server commands in another Thread
    }
    private void sendId(String id)
    {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            dataOutputStream.writeUTF(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Socket getConnectionSocket()
    {
        return connectionSocket;
    }
}
