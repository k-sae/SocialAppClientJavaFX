package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by billy on 2016-12-11.
 */
public class ChatWindow {
    Stage window = new Stage();
    private String id;
    private VBox msgs;
    private HBox container;
    private TextArea message;
    private Button sendBtn;
    private UserInfo loggedUser;
    private UserInfo chatUser;
    private Button testBtn;
    public ChatWindow(String id){
        this.id = id;
        window.setTitle("Messenger");
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20,0,20,0));
        layout.setAlignment(Pos.TOP_CENTER);

        msgs = new VBox(20);
        container = new HBox(50);

        ScrollPane scrollPane = new ScrollPane(msgs);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(msgs.heightProperty());

        testBtn = new Button("Testing");
        layout.getChildren().addAll(new FriendView(id,30), scrollPane, container, testBtn);
        window.setScene(new Scene(layout, 500,600));
        window.show();

        new UserPicker().new InfoPicker(MainWindow.id) {
            @Override
            void pick(UserInfo userInfo) {
                loggedUser = userInfo;
                Platform.runLater(() -> {
                    new UserPicker().new InfoPicker(id) {
                        @Override
                        void pick(UserInfo userInfo) {
                            ChatWindow.this.chatUser = userInfo;

                            //>>>>>>>>>>>>>>>>>>>>> start of connection #kareem
                            try {
                                new ReceiveServerNotification(new UtilityConnection(MainWindow.id, 6020, id).getConnectionSocket()) {
                                    @Override
                                    public void Analyze(Command command) {
                                        receiver(command.getObjectStr());
                                    }
                                }.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                });
            }
        };
        //<<<<<<<<<<<<<<<<<<<<<
        setLayout();
    }
    private void setLayout(){
        msgs.setStyle(Styles.WHITE_BACKGROUND);
        msgs.setPrefHeight(window.getHeight());
        msgs.setMaxHeight(window.getMaxHeight());
        msgs.setPadding(new Insets(20,20,20,20));

        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20,20,20,20));
        
        message = new TextArea();
        message.setPromptText("Type a message...");
        message.setWrapText(true);
        message.setFont(Font.font(18));

        sendBtn = new Button("Send");
        sendBtn.setMinWidth(60);
        sendBtn.setStyle(Styles.BLACK_BUTTON);
        sendBtn.setOnMouseEntered(event -> sendBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
        sendBtn.setOnMouseExited(event -> sendBtn.setStyle(Styles.BLACK_BUTTON));
        sendBtn.setOnMouseClicked(event -> {
            if(!message.getText().equals("")){
                sender(message.getText());
                message.setText("");
            }
        });

        testBtn.setStyle(Styles.BLACK_BUTTON);
        testBtn.setOnMouseEntered(event -> testBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
        testBtn.setOnMouseExited(event -> testBtn.setStyle(Styles.BLACK_BUTTON));
        testBtn.setOnMouseClicked(event -> {
            if(!message.getText().equals("")){
                receiver(message.getText());
                message.setText("");
            }
        });

        container.getChildren().addAll(message, sendBtn);
    }

    public void sender(String text){
        HBox sender = new HBox(10);
        sender.setAlignment(Pos.CENTER_RIGHT);

        Label senderMsg = new Label(text);
        senderMsg.setStyle(Styles.MSG_SENDER);
        senderMsg.setWrapText(true);
        senderMsg.setMinHeight(Region.USE_PREF_SIZE);
        senderMsg.setPadding(new Insets(8,13,8,13));
        sender.getChildren().addAll(senderMsg, UserImage.getCircularImage(loggedUser.getProfileImage(),20));
        msgs.getChildren().add(sender);
    }
    public void receiver(String text){
        HBox receiver = new HBox(10);
        receiver.setAlignment(Pos.CENTER_LEFT);

        Label receiverMsg = new Label(text);
        receiverMsg.setStyle(Styles.MSG_RECEIVER);
        receiverMsg.setWrapText(true);
        receiverMsg.setMinHeight(Region.USE_PREF_SIZE);
        receiverMsg.setPadding(new Insets(8,13,8,13));
        receiver.getChildren().addAll(UserImage.getCircularImage(chatUser.getProfileImage(),20), receiverMsg);
        Platform.runLater(() -> msgs.getChildren().add(receiver));

    }
}
