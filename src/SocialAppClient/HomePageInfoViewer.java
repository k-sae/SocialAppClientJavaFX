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
public class HomePageInfoViewer extends InfoViewer {
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
        groupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;");
        groupBtn.setOnMouseEntered(event -> groupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #ffffff;"));
        groupBtn.setOnMouseExited(event -> groupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;"));
        groupBtn.setOnMouseClicked(event ->
                Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group))));
    }

    @Override
    public void setButtons() {

        CreateGroupBtn = new Button("Create Group");
        CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;");
        CreateGroupBtn.setOnMouseEntered(event -> CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #ffffff;"));
        CreateGroupBtn.setOnMouseExited(event -> CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;"));

        getChildren().addAll(CreateGroupBtn);
    }
}
