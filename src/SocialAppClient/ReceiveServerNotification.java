package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.ReceiveCommand;

import java.net.Socket;
import java.net.SocketException;

/**
 * Created by kemo on 28/10/2016.
 */
public class ReceiveServerNotification extends ReceiveCommand {
    public ReceiveServerNotification(Socket remote)
    {
        super(remote);
        try {
            remote.setSoTimeout(0);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void Analyze(Command command) {
        //TODO #kareem
        //our code starts here HF :)
        System.out.println("Notification says:"+ command.toString());
    }
}
