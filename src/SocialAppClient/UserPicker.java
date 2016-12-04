package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;

/**
 * Created by kemo on 04/12/2016.
 */
class UserPicker {
    abstract class InfoPicker
    {
        InfoPicker(String id)
        {
            Command command = new Command();
            command.setKeyWord(UserInfo.PICK_INFO);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    UserInfo userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                    pick(userInfo);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void pick(UserInfo userInfo);
    }


}
