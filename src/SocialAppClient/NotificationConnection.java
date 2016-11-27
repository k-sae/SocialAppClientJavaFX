package SocialAppClient;

/**
 * Created by kemo on 08/11/2016.
 */
public class NotificationConnection extends ServerConnection {

    public static Thread runningConnection;
    public NotificationConnection() throws Exception {
        //connect to server port 6100 which responsible for notifications
        super("127.0.0.1",6100);
    }
    @Override
    public void startConnection() {
        //start listening to server commands in another Thread
        ReceiveServerNotification receiveServerCommand = new ReceiveServerNotification(connectionSocket);
        receiveServerCommand.start();
    }
}
