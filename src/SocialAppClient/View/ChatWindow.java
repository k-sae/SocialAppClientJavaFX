package SocialAppClient.View;

import SocialAppClient.Connections.ReceiveServerNotification;
import SocialAppClient.Connections.UtilityConnection;
import SocialAppClient.Control.UserPicker;
import SocialAppClient.Control.Utility;
import SocialAppClient.SocialAppGeneral.Command;
import SocialAppClient.SocialAppGeneral.Message;
import SocialAppClient.SocialAppGeneral.SocialArrayList;
import SocialAppClient.SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by billy on 2016-12-11.
 */
class ChatWindow {
    private Stage window = new Stage();
    private String id;
    private VBox msgs;
    private HBox container;
    private TextArea message;
    private Button sendBtn;
    private UserInfo loggedUser;
    private UserInfo chatUser;
    private Socket connectionSocket;
    private ReceiveServerNotification runningThread;
    ChatWindow(String id){
        this.id = id;
        window.setTitle("Messenger");
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20,0,20,0));
        layout.setAlignment(Pos.TOP_CENTER);

        msgs = new VBox(20);
        container = new HBox(50);
        window.setOnCloseRequest(event -> {
            try {
                runningThread.kill();
            }catch (Exception ignored)
            {
                //entering up here means that the thread is already dead
            }

        });
        ScrollPane scrollPane = new ScrollPane(msgs);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(msgs.heightProperty());

        layout.getChildren().addAll(new FriendView(id,30), scrollPane, container);
        window.setScene(new Scene(layout, 500,600));
        window.show();

        new UserPicker().new InfoPicker(MainWindow.id) {
            @Override
            public void pick(UserInfo userInfo) {
                loggedUser = userInfo;
                Platform.runLater(() ->
                        new UserPicker().new InfoPicker(id) {
                    @Override
                    public void pick(UserInfo userInfo) {
                        ChatWindow.this.chatUser = userInfo;

                        //>>>>>>>>>>>>>>>>>>>>> start of connection #kareem
                        try {
                            connectionSocket = new UtilityConnection(MainWindow.id, 6020, id).getConnectionSocket();
                            runningThread = new ReceiveServerNotification(connectionSocket) {
                                @Override
                                public void Analyze(Command command) {
                                    messengerReceiver(command);
                                }
                            };
                            runningThread.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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
                messengerSender(message.getText());
                message.setText("");
            }
        });

        container.getChildren().addAll(message, sendBtn);
    }

    private void sender(String text){
        HBox sender = new HBox(10);
        sender.setAlignment(Pos.CENTER_RIGHT);
        Label senderMsg = new Label(text);
        senderMsg.setStyle(Styles.MSG_SENDER);
        senderMsg.setWrapText(true);
        senderMsg.setMinHeight(Region.USE_PREF_SIZE);
        senderMsg.setPadding(new Insets(8,13,8,13));
        sender.getChildren().addAll(senderMsg, Utility.getCircularImage(loggedUser.getProfileImage(),20));
        Platform.runLater(() -> msgs.getChildren().add(sender));

    }
    private void receiver(String message){
        HBox receiver = new HBox(10);
        receiver.setAlignment(Pos.CENTER_LEFT);

        Label receiverMsg = new Label(message);
        receiverMsg.setStyle(Styles.MSG_RECEIVER);
        receiverMsg.setWrapText(true);
        receiverMsg.setMinHeight(Region.USE_PREF_SIZE);
        receiverMsg.setPadding(new Insets(8,13,8,13));
        receiver.getChildren().addAll(Utility.getCircularImage(chatUser.getProfileImage(),20), receiverMsg);
        Platform.runLater(() -> msgs.getChildren().add(receiver));

    }
    //check whether to make messenger class or just on function is enough
    private void messengerReceiver(Command cmd)
    {
        if (cmd.getKeyWord().equals(Message.FETCH_MESSAGES))
        {
            SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(cmd.getObjectStr());
            for (String o: socialArrayList.getItems()
                 ) {
                Message message = Message.FromJson(o);
                if (message.getSender().equals(MainWindow.id))
                {
                    sender(message.getMessage());
                }
                else
                receiver(message.getMessage());
            }
        }else if(cmd.getKeyWord().equals(Message.RECEIVE_MESSAGE))
        {
            receiver(Message.FromJson(cmd.getObjectStr()).getMessage());
        }

    }
    private void messengerSender(String text)
    {
        sender(text);

        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            Command command = new Command();
            command.setKeyWord(Message.SEND_MESSAGE);
            Message message = new Message();
            message.setSender(MainWindow.id);
            message.setReceiver(id);
            message.setMessage(text);
            command.setSharableObject(message);
            dataOutputStream.writeUTF(command.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

