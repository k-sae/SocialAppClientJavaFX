package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-22.
 */
abstract class InfoViewer extends VBox{
    InfoViewer(){

        setLayout();
    }

    private void setLayout(){

        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30,0,30,0));

    }
    void setPicture(String imgid){

        getChildren().add(Utility.getCircularImage(imgid,60));
    }

    public void setLabel(String... LabelName){
        for(String name : LabelName){
            Label Info = new Label(name);
            Info.setFont(Font.font(16));
            Info.setWrapText(true);
            getChildren().add(Info);
        }
    }

    public abstract void setButtons();
}
