package SocialAppClient.Connections;

import SocialAppClient.SocialAppGeneral.Command;
import SocialAppClient.SocialAppGeneral.ReceiveCommand;

import java.net.Socket;
import java.net.SocketException;

/**
 * Created by kemo on 28/10/2016.
 */
public abstract class ReceiveServerNotification extends ReceiveCommand {
    protected ReceiveServerNotification(Socket remote)
    {
        super(remote);
        try {
            remote.setSoTimeout(0);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

  abstract   public void Analyze(Command command);

    @Override
    public void setRemote(Socket remote) {
        super.setRemote(remote);
        try {
            remote.setSoTimeout(0);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
