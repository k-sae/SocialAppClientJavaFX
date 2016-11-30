package SocialAppClient;

import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class ProfileInfoViewer extends InfoViewer {
    protected Button RelationBTN;
    protected Button Edit;
    public ProfileInfoViewer(){


    }

    @Override
    public void setButtons() {
        /**CHECK IF THE LOGGED USER IS A FRIEND OR VISITOR OR THE OWNER
         * IF IS A FRIEND, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "REMOVE FRIEND"
         * IF IS A VISITOR, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "ADD FRIEND"
         * IF IS THE OWNER, RELATION BUTTON WILL NOT BE VISIBLE
         * if(ID.equals(new AppUser().getID()))
         */

        RelationBTN = new Button("Add Friend");
        RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
        RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
        RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

        Edit = new Button("Edit");
        Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
        Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
        Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

        getChildren().addAll(RelationBTN, Edit);
    }
}
