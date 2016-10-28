package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Connection;
import SocialAppGeneral.ReceiveCommand;

import java.net.Socket;

/**
 * Created by kemo on 28/10/2016.
 */
public class ReceiveServerCommand extends ReceiveCommand {
    public ReceiveServerCommand(Socket remote, Connection connection)
    {
        super(remote, connection);
    }
    @Override
    public void Analyze(Command command) {
        //TODO AllTeam mem
        //our code starts here HF :)
    }
}
