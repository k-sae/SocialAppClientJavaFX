package SocialAppClient;

import SocialAppGeneral.WritePost;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by kemo on 10/11/2016.
 */
public class ProfilePage extends GridPane {
    private String ID;
    public ProfilePage()
    {
        /**IT WILL TAKE AN ID IN THE CONSTRUCTOR*/
        setBackground(new Background(new BackgroundFill(Color.web("#dddddd"), CornerRadii.EMPTY, Insets.EMPTY)));

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

        InfoViewer Info = new InfoViewer(ID);

        add(Info,0,0);

        VBox Content = new VBox();
        Content.setAlignment(Pos.TOP_CENTER);
        Content.setPadding(new Insets(20,50,20,50));

        /**POST CONTAINER WILL BE HERE*/
        Label postcontainer = new Label("POST CONTAINER");
        postcontainer.setFont(new Font(40));

        Content.getChildren().addAll(new WritePost() /**POSTCONTAINER*/ , postcontainer);
        add(Content,1,0);
    }

}
