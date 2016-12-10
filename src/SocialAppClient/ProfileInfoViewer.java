package SocialAppClient;

import SocialAppGeneral.Command;
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

        if (MainWindow.id.equals(id)) {

            Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
            Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
            Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

            getChildren().add(Edit);
        } else {
            MainWindow.clientLoggedUser.new GetStatus(id) {
                @Override
                void onFinish(String s) {
                    Platform.runLater(() -> {
                        if (s.equals(Relations.NOT_FRIEND.toString())) {
                            addAddFriendButton();
                        } else if (s.equals(Relations.FRIEND_REQ.toString())) {

                            addConfirmationButton();
                            addDeclineButton();
                        } else if (s.equals(Relations.FRIEND.toString())) {
                            addRemoveFriendButton();
                        } else if (s.equals(Relations.PENDING.toString())) {
                            addCancelRequestButton();
                        }
                    });
                }

            };
        }
    }
    private void addAddFriendButton()
    {
        createButton("Add Friend");
        RelationBTN.setOnAction(e-> MainWindow.clientLoggedUser.new addFriend(id) {
            @Override
            void onFinish(Command cmd) {
                Platform.runLater(() -> Main.refresh(MainWindow.id,new ProfilePage(id)));
            }
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
        RelationBTN.setOnAction(e-> MainWindow.clientLoggedUser.new AcceptFriendReq(id) {
            @Override
            void onFinish(Command cmd) {
                Platform.runLater(() -> Main.refresh(MainWindow.id,new ProfilePage(id)));
            }
        });
        getChildren().add(RelationBTN);
    }
    private void addRemoveFriendButton()
    {
        createButton("Remove Friend");
            RelationBTN.setOnAction(e-> MainWindow.clientLoggedUser.new RemoveFriend(id) {
                @Override
                void onFinish(Command cmd) {
                    Platform.runLater(() -> Main.refresh(MainWindow.id,new ProfilePage(id)));
                }
            });
        getChildren().add(RelationBTN);
    }
    private void addCancelRequestButton()
    {
        createButton("Cancel Request");
        RelationBTN.setOnAction(e-> MainWindow.clientLoggedUser.new CancelFriendReq(id) {
            @Override
            void onFinish(Command cmd) {
                Platform.runLater(() ->  MainWindow.navigateTo(new ProfilePage(id)));
            }
        });
        getChildren().add(RelationBTN);
    }
    private void addDeclineButton()
    {
        createButton("Decline");
        RelationBTN.setOnAction(e-> MainWindow.clientLoggedUser.new DeclineFriendReq(id) {
            @Override
            void onFinish(Command cmd) {
                Platform.runLater(() ->  MainWindow.navigateTo(new ProfilePage(id)));
            }
        });
        getChildren().add(RelationBTN);
    }

}
