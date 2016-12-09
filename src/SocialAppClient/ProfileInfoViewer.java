package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.LoggedUser;
import SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class ProfileInfoViewer extends InfoViewer {
    private String id;
    protected Button RelationBTN;
    protected Button Edit;

    public ProfileInfoViewer(String id){

        Edit = new Button("Edit");
        this.id = id;


    }
    @Override
    public void setButtons() {

        if(MainWindow.id.equals(id)){

            Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
            Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
            Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

            getChildren().add(Edit);
        }else {
            Command command = new Command();
            command.setKeyWord(LoggedUser.GET_RELATION_STATUS);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    String s = cmd.getObjectStr();
                    System.out.println(s);
                    Platform.runLater(() -> {
                        if(s.equals(Relations.NOT_FRIEND.toString()))
                        {
                            addAddFriendButton();
                        }
                        else if(s.equals(Relations.FRIEND_REQ.toString()))
                        {

                            addConfirmationButton();
                            addDeclineButton();
                        }
                        else if(s.equals(Relations.FRIEND.toString()))
                        {
                            addRemoveFriendButton();
                        }
                        else if(s.equals(Relations.PENDING.toString()))
                        {
                            addCancelRequestButton();
                        }

                    });

                }
            };
            CommandsExecutor.getInstance().add(commandRequest);

        }
    }
    private void addAddFriendButton()
    {
        createButton("Add Friend");
        RelationBTN.setOnAction(e->{
            Command command = new Command();
            command.setKeyWord(LoggedUser.ADD_FRIEND);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id)));

                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        getChildren().add(RelationBTN);
    }
    private void createButton(String content)
    {
        RelationBTN = new Button(content);
        RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
        RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
        RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
    }
    private void addConfirmationButton()
    {
        createButton("Accept");
        RelationBTN.setOnAction(e->{
            Command command = new Command();
            command.setKeyWord(LoggedUser.ACCEPT_FRIEND);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id)));
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        getChildren().add(RelationBTN);
    }
    private void addRemoveFriendButton()
    {
        createButton("Remove Friend");
        RelationBTN.setOnAction(e->{
            Command command = new Command();
            command.setKeyWord(LoggedUser.REMOVE_FRIEND);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id)));
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        getChildren().add(RelationBTN);
    }
    private void addCancelRequestButton()
    {
        createButton("Cancel Request");
        RelationBTN.setOnAction(e->{
            Command command = new Command();
            command.setKeyWord(LoggedUser.CANCEL_FRIEND_REQ);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id)));
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        getChildren().add(RelationBTN);
    }
    private void addDeclineButton()
    {
        createButton("Decline");
        RelationBTN.setOnAction(e->{
            Command command = new Command();
            command.setKeyWord(LoggedUser.DECLINE_FRIEND);
            command.setSharableObject(id);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                    Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id)));
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        getChildren().add(RelationBTN);
    }
}
