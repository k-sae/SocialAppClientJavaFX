package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-12-17.
 */
public class LogHistory extends VBox {

    public LogHistory(){
        setLayout();
    }
    public LogHistory(String ownerId, String posId, String keyword){
        setLayout();
    }
    public LogHistory(String ownerId, String posId, String keyword, String extraId){
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,40,40,40));
        setSpacing(2);
        Label title = new Label("LOG HISTORY");
        title.setFont(Font.font(36));
        title.setPadding(new Insets(20,0,0,0));

        getChildren().addAll(title, new Separator());
        set("liked ", "commented on ", "liked ");
    }

    private void set(String... strings){
        for (String s: strings) {
            HBox container = new HBox();
            container.setAlignment(Pos.CENTER_LEFT);
            container.setStyle(Styles.WHITE_BACKGROUND);
            Label keyword1 = new Label(s);
            keyword1.setFont(Font.font(20));
            Label keyword2 = new Label("post");
            keyword2.setFont(Font.font(20));

            container.getChildren().addAll(new FriendView("1", 20), keyword1, new FriendView("4", 20), keyword2);

            container.setPadding(new Insets(5, 20, 5, 20));
            getChildren().add(container);
        }
    }
}
