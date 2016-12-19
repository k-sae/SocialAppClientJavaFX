package SocialAppClient;

import SocialAppGeneral.Group;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Created by kemo on 19/12/2016.
 */
class GroupView extends Label {
    String id;
    GroupView(String id)
    {
        this.id = id;
        new GroupPicker().new InfoPicker(Long.parseLong(id)) {
            @Override
            void pick(Group group) {
                Platform.runLater(() -> setLayout(group));

            }
        };

    }
    private void setLayout(Group group)
    {
        setWrapText(true);

        setPadding(new Insets(8,11,8,11));

        setStyle(Styles.USER_VIEW);
        setOnMouseEntered(event -> setStyle(Styles.USER_VIEW_HOVER));
        setOnMouseExited(event -> setStyle(Styles.USER_VIEW));
        setOnMouseClicked(event -> Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(id))));
        setFont(Font.font(20));
        setText(group.getName());
        setGraphic(Utility.getCircularImage("Group",20));
        onFinishSettingLayout(GroupView.this);
    }
    protected void onFinishSettingLayout(GroupView view)
    {

    }
}
