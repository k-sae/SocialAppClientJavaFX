package SocialAppClient;

/**
 * Created by kemo on 08/11/2016.
 */
public class NotificationConnection extends ServerConnection {

    public static Thread runningConnection;
    public NotificationConnection()
    {
        //connect to server port 6061 which responsible for notifications
        super("TITO",6080);
    }
    @Override
    public void startConnection() {
        //start listening to server commands in another Thread
        ReceiveServerNotification receiveServerCommand = new ReceiveServerNotification(connectionSocket);
        receiveServerCommand.start();
    }
}
