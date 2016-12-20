package SocialAppClient.Connections;

import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public class MainServerConnection extends ServerConnection {
    public static Socket mainConnectionSocket;

    public MainServerConnection() throws Exception {
        //TODO #config

        super(/*change this to match ur pc name*/"127.0.0.1", 6000);

        mainConnectionSocket = connectionSocket;
    }

    //if no parameters passed set default connection
    //TODO #kareem
    //after creating users levels accept user of type registeredUser or login user
    @Override
    public void startConnection() {
        //here i will check for user info and choose whether to continue the connection or to end it
    }
}
