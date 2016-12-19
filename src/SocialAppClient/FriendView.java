package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-12-03.
 */
public class FriendView extends Label {

    String id;
    private double size;
    private UserInfo userInfo;
    FriendView(String id)
    {
        this.size=20;
        this.id = id;
        new UserPicker().new InfoPicker(id){
            @Override
            void pick(UserInfo userInfo) {
                FriendView.this.userInfo = userInfo;
                Platform.runLater(() -> setAttributes(userInfo));
            }
        };
        /*
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
        */
    }
    FriendView(String id, double size)
    {
        this.id = id;
        this.size = size;
        new UserPicker().new InfoPicker(id){
            @Override
            void pick(UserInfo userInfo) {
                FriendView.this.userInfo = userInfo;
                Platform.runLater(() -> setAttributes(userInfo));
            }
        };
        /*
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
        CommandsExecutor.getInstance().add(commandRequest);*/
    }
    private void setAttributes(UserInfo userInfo){
        setWrapText(true);

        setPadding(new Insets(8,11,8,11));

        setStyle(Styles.USER_VIEW);
        setOnMouseEntered(event -> setStyle(Styles.USER_VIEW_HOVER));
        setOnMouseExited(event -> setStyle(Styles.USER_VIEW));
        setOnMouseClicked(event -> Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id))));
        setFont(Font.font(size));
        setText(userInfo.getFullName());
        Platform.runLater(() -> setGraphic(Utility.getCircularImage(userInfo.getProfileImage(),size)));
        onFinishSettingLayout(this);
    }
    protected void onFinishSettingLayout(FriendView view)
    {

    }
}
