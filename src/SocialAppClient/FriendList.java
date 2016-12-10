package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-01.
 */
class FriendList extends VBox {
    ArrayList<String> ids;
    FriendList( ArrayList<String> ids){
        this.ids = ids;
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        Label friendLabel = new Label("FRIENDS");
        friendLabel.setFont(Font.font(36));
        friendLabel.setPadding(new Insets(30,0,10,0));
        getChildren().addAll(friendLabel, new Separator());
        for (String id:ids) {
            getChildren().add(new HBox(new FriendView(id)));
        }
    }
}
