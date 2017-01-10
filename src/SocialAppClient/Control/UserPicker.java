package SocialAppClient.Control;

import SocialAppClient.Connections.CommandRequest;
import SocialAppClient.Connections.CommandsExecutor;
import SocialAppClient.Connections.MainServerConnection;
import SocialAppClient.SocialAppGeneral.Command;
import SocialAppClient.SocialAppGeneral.UserInfo;


/**
 * Created by kemo on 04/12/2016.
 */
public class UserPicker {
    public abstract class InfoPicker
    {
        protected InfoPicker(String id)
        {
            Command command = new Command();
            command.setKeyWord(UserInfo.PICK_INFO);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    UserInfo userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                    pick(userInfo);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void pick(UserInfo userInfo);
    }

}
