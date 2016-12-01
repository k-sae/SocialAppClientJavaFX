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
public abstract class InfoViewer extends VBox{
    ImageView img;
    public InfoViewer(){

        setLayout();
        setPicture("file:Resources/avatar.jpg");
    }

    private void setLayout(){

        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30,0,30,0));

    }
    public void setPicture(String Path){
        getChildren().remove(img);
        Image im = new Image(Path);
        img = new ImageView(im);
        img.setFitWidth(100);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
        img.setClip(new Circle(img.getFitWidth()/2,img.getFitWidth()/2,img.getFitWidth()/2));

        getChildren().add(img);
    }
    public void setLabel(String... LabelName){
        for(String name : LabelName){
            Label Info = new Label(name);
            Info.setFont(Font.font(16));
            getChildren().add(Info);
        }
    }

    public abstract void setButtons();
}
