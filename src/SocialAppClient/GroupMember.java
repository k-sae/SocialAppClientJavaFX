package SocialAppClient;

import SocialAppGeneral.AppUser;
import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import SocialAppGeneral.LoggedUser;

/**
 * Created by mosta on 18-Dec-16.
 */
public class GroupMember extends AppUser {
    static abstract class GetStatus
    {
        GetStatus(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_relation);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
    static abstract class LeaveGroup
    {
        LeaveGroup(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_Leave);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
    static abstract class FetchData
    {
        FetchData(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.FETCH_DATA);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
    static abstract class AddMemberGroup
    {
        AddMemberGroup(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_ADD);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
    static abstract class AcceptRequest
    {
        AcceptRequest(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_Accept);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
    static abstract class cancelRequest
    {
        cancelRequest(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.GROUP_CANCEL_REQ);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(String s);
    }
}
