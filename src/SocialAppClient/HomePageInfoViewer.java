package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import javafx.scene.control.Button;

import java.util.Optional;

/**
 * Created by billy on 2016-11-28.
 */
public class HomePageInfoViewer extends InfoViewer {
    /**navBtn WILL BE A NAVIGATOR BUTTONS TO OTHER PAGES*/
    protected Button navBtn;
    protected Button CreateGroupBtn;
    public HomePageInfoViewer(){

    }

    @Override
    public void setButtons() {
        navBtn = new Button("NAV TO");
        navBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
        navBtn.setOnMouseEntered(event -> navBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
        navBtn.setOnMouseExited(event -> navBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

        CreateGroupBtn = new Button("Create Group");
        CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;");
        CreateGroupBtn.setOnMouseEntered(event -> CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #ffffff;"));
        CreateGroupBtn.setOnMouseExited(event -> CreateGroupBtn.setStyle("-fx-font: 19 arial; -fx-background-color: #eeeeee;"));

        getChildren().addAll(navBtn, CreateGroupBtn);
    }
}
