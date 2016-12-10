package SocialAppClient;

import SocialAppGeneral.Group;
import javafx.scene.control.Button;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer{
    protected Button RelationBTN;
    protected Button Edit;
    private long id;
    private Group group;
    public GroupInfoViewer(Group group){
        this.group = group;
        RelationBTN = new Button();
    }

    @Override
    public void setButtons() {
        for(long i : group.getMember()) {
            if (MainWindow.id.equals(""+i)){
                RelationBTN.setText("LEAVE");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                getChildren().add(RelationBTN);

            }else{
                RelationBTN.setText("JOIN");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                getChildren().add(RelationBTN);
            }
        }
        /*
        Edit = new Button("Edit");
        Edit.setStyle(Styles.NAV_BUTTON);
        Edit.setOnMouseEntered(event -> Edit.setStyle(Styles.NAV_BUTTON_HOVER));
        Edit.setOnMouseExited(event -> Edit.setStyle(Styles.NAV_BUTTON));
*/
    }
}