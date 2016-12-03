package SocialAppClient;

import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class ProfileInfoViewer extends InfoViewer {
    private String id;
    protected Button RelationBTN;
    protected Button Edit;
    public ProfileInfoViewer(String id){

        Edit = new Button("Edit");
        this.id = id;

    }

    @Override
    public void setButtons() {

        if(MainWindow.id.equals(id)){

            Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
            Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
            Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

            getChildren().add(Edit);
        }else {

            RelationBTN = new Button("Add Friend");
            RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
            RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
            RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
            getChildren().add(RelationBTN);
        }
    }
}
