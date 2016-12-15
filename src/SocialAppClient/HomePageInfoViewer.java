package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by billy on 2016-11-28.
 */
public class HomePageInfoViewer extends InfoViewer{
    protected Button CreateGroupBtn;
    public HomePageInfoViewer(){

    }
    public void setGroupsBtn(ArrayList<Group> groups){
        for(Group group: groups){
            createGroupBtns(group);
        }

    }

    private void createGroupBtns(Group group){
        Button groupBtn = new Button(group.getName());
        groupBtn.setStyle(Styles.NAV_BUTTON);
        groupBtn.setOnMouseEntered(event -> groupBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        groupBtn.setOnMouseExited(event -> groupBtn.setStyle(Styles.NAV_BUTTON));
        groupBtn.setOnMouseClicked(event ->
                Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group))));
        getChildren().add(groupBtn);
    }

    @Override
    public void setButtons() {
        CreateGroupBtn = new Button("Create Group");
        CreateGroupBtn.setStyle(Styles.NAV_BUTTON);
        CreateGroupBtn.setOnMouseEntered(event -> CreateGroupBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        CreateGroupBtn.setOnMouseExited(event -> CreateGroupBtn.setStyle(Styles.NAV_BUTTON));

        getChildren().add(CreateGroupBtn);
    }
}
