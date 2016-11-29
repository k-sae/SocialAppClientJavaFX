package SocialAppClient;

import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer {
    protected Button RelationBTN;
    protected Button Edit;
    public GroupInfoViewer(){

    }

    @Override
    public void setButtons() {
        /**CHECK IF THE LOGGED USER IS IN THE GROUP OR NOT
         * IF HE IS IN THE GROUP, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "LEAVE GROUP"
         * IF HE IS NOT IN THE GROUP, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "JOIN GROUP"
         */

        RelationBTN = new Button("JOIN");
        RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));

        Edit = new Button("Edit");
        Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));

        getChildren().addAll(RelationBTN, Edit);
    }
}