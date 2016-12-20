package SocialAppClient.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-01.
 */
class FriendList extends VBox {
    private ArrayList<String> ids;
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
