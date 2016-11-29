package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class ProfilePage extends GridPane {
    private String ID;
    public ProfilePage()
    {
        /**IT WILL TAKE AN ID IN THE CONSTRUCTOR*/
        setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));

        setGridLinesVisible(true);
        setConstraint();
        setPanels();

    }

    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(25);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(75);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }

    private void setPanels(){

        ProfileInfoViewer Info = new ProfileInfoViewer();
        /**ADD PICTURE*/
        //Info.setPicture("");
        /**ADD INFO*/
        Info.setLabel("Name: 7amada", "Age: 19", "blab blah");
        Info.setButtons();

        add(Info,0,0);

        Content content = new Content();

        add(content,1,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

        Info.Edit.setOnAction(event -> {
            getChildren().remove(content);
            /**AFTER CLICK ON EDIT IT WILL GO TO EDIT PAGE*/
            add(new EditInfo(),1,0);
            sp.setContent(null);
        });
    }

}
