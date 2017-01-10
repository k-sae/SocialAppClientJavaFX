package SocialAppClient.Control;

import SocialAppClient.Connections.CommandRequest;
import SocialAppClient.Connections.CommandsExecutor;
import SocialAppClient.Connections.MainServerConnection;
import SocialAppClient.SocialAppGeneral.Command;
import SocialAppClient.SocialAppGeneral.Group;

/**
 * Created by kemo on 21/12/2016.
 */
public class GroupPicker {
    public abstract class InfoPicker {
        public InfoPicker(long id) {
            Command command = new Command();
            command.setKeyWord(Group.LOAD_GROUP);
            command.setSharableObject("" + id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                public void analyze(Command cmd) {
                    Group group = Group.fromJsonString(cmd.getObjectStr());
                    pick(group);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }

        public abstract void pick(Group group);

    }
}
