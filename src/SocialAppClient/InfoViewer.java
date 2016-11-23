package SocialAppClient;

import SocialAppGeneral.AppUser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by billy on 2016-11-22.
 */
public class InfoViewer extends VBox{
    private String ID;
    public InfoViewer(String ID){
        this.ID = ID;
        setLayout();
        setButton();
    }

    private void setLayout(){

        Image im = new Image("file:Resources/btatsya.png");
        //Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(100);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
        img.setClip(new Circle(50,50,50));

        /**INFO WILL BE HERE*/
        Label Info = new Label("INFO WILL BE HERE");
        Info.setFont(Font.font(16));

        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30,0,30,0));
        getChildren().addAll(img, Info);
    }

    private void setButton(){
        /**CHECK IF THE LOGGED USER IS A FRIEND OR VISITOR OR THE OWNER
         * IF IS A FRIEND, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "REMOVE FRIEND"
         * IF IS A VISITOR, RELATION BUTTON WILL BE VISIBLE AND IT'S TEXT "ADD FRIEND"
         * IF IS THE OWNER, RELATION BUTTON WILL NOT BE VISIBLE
         * if(ID.equals(new AppUser().getID()))
         */


        Button RelationBTN = new Button("Add Friend");
        RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;");
        RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee;"));
        RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle("-fx-font: 20 arial; -fx-background-color: #dddddd;"));

        getChildren().addAll(RelationBTN);
    }
}
