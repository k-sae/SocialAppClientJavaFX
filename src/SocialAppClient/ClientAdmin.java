package SocialAppClient;

import SocialAppGeneral.Admin;
import SocialAppGeneral.Command;
import SocialAppGeneral.SocialArrayList;

/**
 * Created by mosta on 13-Dec-16.
 */
class ClientAdmin extends ClientLoggedUser implements Admin {

    ClientAdmin(String id) {
        super(id);
    }

    @Override
    public void approve(String Email) {
        Command command = new Command();
        command.setKeyWord(Admin.APPROVE);
        command.setSharableObject(Email);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {
                System.out.println(commandFromServer.getObjectStr());

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);

    }

    @Override
    public void reject(String Email) {
        Command command = new Command();
        command.setKeyWord(Admin.REJECT);
        command.setSharableObject(Email);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {
                System.out.println(commandFromServer.getObjectStr());

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    @Override
    public void approveAsAdmin(String Email) {
        Command command = new Command();
        command.setKeyWord(Admin.APPROVEASADMIN);
        command.setSharableObject(Email);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {
                System.out.println(commandFromServer.getObjectStr());

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    abstract class FetchRequests
    {
        FetchRequests()
        {
            Command command = new Command();
            command.setKeyWord(Admin.RetrieveData);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    System.out.println(commandFromServer.getObjectStr());
                    SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(commandFromServer.getObjectStr());
                    onRetrieve(socialArrayList);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);


        }
        public abstract void onRetrieve(SocialArrayList list);
    }
}
