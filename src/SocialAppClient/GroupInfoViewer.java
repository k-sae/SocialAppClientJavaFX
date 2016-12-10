package SocialAppClient;

import SocialAppGeneral.Group;
import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer {
    protected Button RelationBTN;
    protected Button Edit;
    private int id;
    private Group group;
    public GroupInfoViewer(int id){
        this.id = id;
        group.setId(this.id);
    }

    @Override
    public void setButtons() {
        for(long i : group.getMember()) {
            if (MainWindow.id.equals(""+i)){
                RelationBTN = new Button("LEAVE");
                RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

            }else{
                RelationBTN = new Button("JOIN");
                RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));

            }
        }
        /*
        Edit = new Button("Edit");
        Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;");
        Edit.setOnMouseEntered(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff;"));
        Edit.setOnMouseExited(event -> Edit.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
*/
        getChildren().addAll(RelationBTN);
    }
}