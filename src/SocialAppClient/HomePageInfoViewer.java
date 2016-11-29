package SocialAppClient;

import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-28.
 */
public class HomePageInfoViewer extends InfoViewer {
    protected Button Edit;
    protected Button CreateGroupBtn;
    public HomePageInfoViewer(){

    }

    @Override
    public void setButtons() {
        Edit = new Button("Edit");
        Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));

        CreateGroupBtn = new Button("Create Group");
        CreateGroupBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        CreateGroupBtn.setOnMouseEntered(event -> CreateGroupBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        CreateGroupBtn.setOnMouseExited(event -> CreateGroupBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));

        getChildren().addAll(Edit, CreateGroupBtn);
    }
}
