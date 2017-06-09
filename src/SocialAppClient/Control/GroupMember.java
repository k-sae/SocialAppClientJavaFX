package SocialAppClient.Control;

import Connections.Client.CommandRequest;
import Connections.Client.CommandsExecutor;
import Connections.Command;
import SocialAppClient.Control.Connections.MainServerConnection;
import SocialAppClient.SocialAppGeneral.AppUser;
import SocialAppClient.SocialAppGeneral.Group;

/**
 * Created by mosta on 18-Dec-16.
 */
public class GroupMember extends AppUser {
    public static abstract class GetStatus
    {
        public GetStatus(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_relation);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
    public static abstract class LeaveGroup
    {
        public LeaveGroup(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_Leave);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
    public static abstract class FetchData
    {
        public FetchData(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.FETCH_DATA);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
    public static abstract class AddMemberGroup
    {
        public AddMemberGroup(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_ADD);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    System.out.print(cmd.getObjectStr());
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
    public static abstract class AcceptRequest
    {
        public AcceptRequest(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.Group_Accept);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
    public static abstract class cancelRequest
    {
        public cancelRequest(String id)
        {
            Command command = new Command();
            command.setKeyWord(Group.GROUP_CANCEL_REQ);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                public void analyze(Command cmd) {
                    onFinish(cmd.getObjectStr());
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        public abstract void onFinish(String s);
    }
}
