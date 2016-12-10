package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import SocialAppGeneral.LoggedUser;
import javafx.application.Platform;

import java.util.Optional;

/**
 * Created by kemo on 10/12/2016.
 */
 public class ClientLoggedUser extends LoggedUser {
    //TODO #khaled
    //>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void createGroup(String check) {
        Command command = new Command();
        command.setKeyWord(Group.CREATE_GROUP);
        Group group=new Group(check);
        group.setAdminId(Long.parseLong(MainWindow.id));
        command.setSharableObject(group.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Group.CREATE_GROUP)) {
                    Group group1 = Group.fromJsonString(cmd.getObjectStr());
                    System.out.println(cmd.getObjectStr());
                    //TODO #Fix
                    //fix error on threading
                    groups.add(""+group1.getId());
                    Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group1)));

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);


    }

    @Override
    public void joinGroup() {

    }

    @Override
    public void exitGroup() {

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<

    //TODO #kareem
    //>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void addFriend() {

    }

    @Override
    public void removeFriend() {

    }

    @Override
    public void acceptFriend() {

    }

    @Override
    public void declineFriend() {

    }

    @Override
    public void cancelFriendReq() {

    }

    @Override
    public void setFriends() {
        
    }

    @Override
    public void getFriends() {

    }

    @Override
    public void getRequests() {

    }

    @Override
    public void getNotfications() {

    }

    @Override
    public void getgroups() {

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<
}
