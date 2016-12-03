package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

/**
 * Created by billy on 2016-12-03.
 */
public class FriendView extends Button {
    String id;
    FriendView(String id)
    {
        this.id = id;
        //send id to server userinfo
        Command command = new Command();
        command.setKeyWord(UserInfo.PICK_INFO);
        command.setSharableObject(id);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                UserInfo userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                Platform.runLater(() ->
                        setAttributes(userInfo)
                );

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    private void setAttributes(UserInfo userInfo)
    {
        ImageViewer friendImg = new ImageViewer("123");
        friendImg.setFitWidth(40);
        friendImg.setFitHeight(40);
        friendImg.setSmooth(true);
        friendImg.setCache(true);
        friendImg.setClip(new Circle(friendImg.getFitWidth()/2,friendImg.getFitWidth()/2,friendImg.getFitWidth()/2));

        setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;");
        setOnMouseEntered(event -> setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
        setOnMouseExited(event -> setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;"));
        setOnMouseClicked(event -> Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id))));
        setText(userInfo.getFullName());
        setGraphic(friendImg);
    }
}
