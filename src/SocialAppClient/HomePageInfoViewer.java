package SocialAppClient;

import SocialAppGeneral.Group;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by billy on 2016-11-28.
 */
class HomePageInfoViewer extends InfoViewer{
    Button CreateGroupBtn;
    private VBox groupBtns;
    private ScrollPane scrollPane;
    HomePageInfoViewer(){
        setPadding(new Insets(30,0,0,0));
        groupBtns = new VBox(20);
        groupBtns.setAlignment(Pos.TOP_CENTER);

        scrollPane = new ScrollPane(groupBtns);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle(Styles.SCROLLBAR_BACKGROUND);

    }
    void setGroupsBtn(ArrayList<Group> groups){
        groups.forEach(this::GroupButtons);

    }

    private void GroupButtons(Group group){
        Button groupBtn = new Button(group.getName());
        groupBtn.setStyle(Styles.NAV_BUTTON);
        groupBtn.setOnMouseEntered(event -> groupBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        groupBtn.setOnMouseExited(event -> groupBtn.setStyle(Styles.NAV_BUTTON));
        groupBtn.setOnMouseClicked(event ->
                Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group))));
        groupBtns.getChildren().add(groupBtn);
    }

    @Override
    public void setButtons() {
        CreateGroupBtn = new Button("Create Group");
        CreateGroupBtn.setStyle(Styles.NAV_BUTTON);
        CreateGroupBtn.setOnMouseEntered(event -> CreateGroupBtn.setStyle(Styles.NAV_BUTTON_HOVER));
        CreateGroupBtn.setOnMouseExited(event -> CreateGroupBtn.setStyle(Styles.NAV_BUTTON));

        getChildren().addAll(CreateGroupBtn, scrollPane);
    }
}
