package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Log;
import SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.ArrayList;

import static SocialAppClient.MainWindow.clientLoggedUser;

/**
 * Created by billy on 2016-11-26.
 */
class ProfileInfoViewer extends InfoViewer{
    private String id;
    private Button RelationBTN;
    Button Edit;

    ProfileInfoViewer(String id){

        Edit = new Button("Edit");
        this.id = id;
        if (clientLoggedUser instanceof ClientAdmin && !MainWindow.id.equals(id)) {
            Platform.runLater(this::logButton);
        }

    }
    @Override
    public void setButtons() {

        if (MainWindow.id.equals(id)) {

            Edit.setStyle(Styles.NAV_BUTTON);
            Edit.setOnMouseEntered(event -> Edit.setStyle(Styles.NAV_BUTTON_HOVER));
            Edit.setOnMouseExited(event -> Edit.setStyle(Styles.NAV_BUTTON));

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
            startChatButton();
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
        RelationBTN.setStyle(Styles.NAV_BUTTON);
        RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
        RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
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
                Platform.runLater(() ->  Main.refresh(MainWindow.id,(new ProfilePage(id))));
            }
        });
        getChildren().add(RelationBTN);
    }
    private void addDeclineButton()
    {
        Button declineBTN = new Button("Decline");
        declineBTN.setStyle(Styles.NAV_BUTTON);
        declineBTN.setOnMouseEntered(event -> declineBTN.setStyle(Styles.NAV_BUTTON_HOVER));
        declineBTN.setOnMouseExited(event -> declineBTN.setStyle(Styles.NAV_BUTTON));
        declineBTN.setOnAction(e-> MainWindow.clientLoggedUser.new DeclineFriendReq(id) {
            @Override
            void onFinish(Command cmd) {
                Platform.runLater(() ->  Main.refresh(MainWindow.id,(new ProfilePage(id))));
            }
        });
        getChildren().add(declineBTN);
    }
    private void startChatButton()
    {
        Button chatBtn = new Button("Send Message");
        chatBtn.setStyle(Styles.NAV_BUTTON);
        chatBtn.setOnMouseEntered(event -> chatBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        chatBtn.setOnMouseExited(event -> chatBtn.setStyle(Styles.NAV_BUTTON));
        chatBtn.setOnMouseClicked(event -> new ChatWindow(id));
        getChildren().add(chatBtn);
    }private void logButton(){
        Button logBtn = new Button("Logs");
        logBtn.setStyle(Styles.NAV_BUTTON);
        logBtn.setOnMouseEntered(event -> logBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        logBtn.setOnMouseExited(event -> logBtn.setStyle(Styles.NAV_BUTTON));
        logBtn.setOnMouseClicked(event -> MainWindow.clientLoggedUser.new GetLogs() {
            @Override
            void onFinish(ArrayList<Log> logs) {
                Platform.runLater(() -> Content.navigateTo(new LogHistory(id, logs)));

            }
        });
        getChildren().add(logBtn);
    }

}
