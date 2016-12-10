package SocialAppClient;

import SocialAppGeneral.Group;
import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer{
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
        for(int i : group.getMember()) {
            if (MainWindow.id.equals(""+i)){
                RelationBTN = new Button("LEAVE");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));

            }else{
                RelationBTN = new Button("JOIN");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));

            }
        }
        /*
        Edit = new Button("Edit");
        Edit.setStyle(Styles.NAV_BUTTON);
        Edit.setOnMouseEntered(event -> Edit.setStyle(Styles.NAV_BUTTON_HOVER));
        Edit.setOnMouseExited(event -> Edit.setStyle(Styles.NAV_BUTTON));
*/
        getChildren().addAll(RelationBTN);
    }
}