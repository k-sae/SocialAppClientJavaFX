package SocialAppClient.View;

import SocialAppClient.Control.UserPicker;
import SocialAppClient.Control.Utility;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/*
 * Created by billy on 2016-12-03.
 */
public class FriendView extends Label {

    String id;
    private double size;

    FriendView(String id)
    {
        this.size=20;
        this.id = id;
        new UserPicker().new InfoPicker(id){
            @Override
            public void pick(UserInfo userInfo) {
                Platform.runLater(() -> setAttributes(userInfo));
            }
        };

    }
    public FriendView(String id, double size)
    {
        this.id = id;
        this.size = size;
        new UserPicker().new InfoPicker(id){
            @Override
            public void pick(UserInfo userInfo) {
                Platform.runLater(() -> setAttributes(userInfo));
            }
        };
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
