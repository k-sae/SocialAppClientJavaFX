package SocialAppClient;

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
    public ProfilePage()
    {
        setBackground(new Background(new BackgroundFill(Color.web("#dddddd"), CornerRadii.EMPTY, Insets.EMPTY)));

        setGridLinesVisible(true);
        setConstraint();
        setPanels();

    }

    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(20);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(80);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }

    private void setPanels(){

        VBox Info = new VBox();

        Image im = new Image("file:Resources/btatsya.png");
        ImageView img = new ImageView(im);
        img.setFitWidth(100);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
        img.setClip(new Circle(50,50,50));

        Text Name = new Text("#NAME");
        Name.setFont(Font.font("ARIAL",20));

        Button FriendsBTN = new Button("Friends");
        FriendsBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        FriendsBTN.setOnMouseEntered(event -> FriendsBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        FriendsBTN.setOnMouseExited(event -> FriendsBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));


        Info.alignmentProperty().setValue(Pos.TOP_CENTER);
        Info.setSpacing(20);
        Info.paddingProperty().setValue(new Insets(30,0,30,0));
        Info.getChildren().addAll(img, Name, FriendsBTN);
        add(Info,0,0);

        GridPane Content = new GridPane();
        add(Content,1,0);
    }

}
