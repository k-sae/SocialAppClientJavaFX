package SocialAppClient;

import SocialAppGeneral.Log;
import SocialAppGeneral.Relations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-17.
 */
public class LogHistory extends VBox {

    private ArrayList<Log> logs;
    String id;
    public LogHistory(String id, ArrayList<Log> logs){
        this.logs = logs;
        this.id = id;
        setLayout();
        set();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,10,10,10));
        setSpacing(2);
        Label title = new Label("LOG HISTORY");
        title.setFont(Font.font(30));

        getChildren().addAll(title, new Separator());
    }

    private void set(){
        for (Log log: logs) {
            if(id.equals(log.getSenderId())) {
                HBox container = new HBox();
                container.setAlignment(Pos.CENTER);
                container.setStyle(Styles.WHITE_BACKGROUND);
                Label keyword1 = new Label();
                if(log.getKeyword().equals(Relations.THUMP_UP) || log.getKeyword().equals(Relations.THUMP_DOWN))
                    keyword1.setText(log.getKeyword().toString().toLowerCase() + " on ");
                else
                    keyword1.setText(log.getKeyword().toString().toLowerCase() + "ed on ");
                keyword1.setFont(Font.font(15));
                Label keyword2 = new Label("post");
                keyword2.setFont(Font.font(15));

                if(!log.getKeyword().equals(Relations.POST))
                    container.getChildren().addAll(new FriendView(log.getSenderId(), 15), keyword1, new FriendView(log.getOwnerId(), 15), keyword2);
                else{
                    keyword2.setText("his own profile");
                    container.getChildren().addAll(new FriendView(log.getSenderId(), 15), keyword1, keyword2);
                }
                container.setPadding(new Insets(5, 5, 5, 0));
                getChildren().add(container);
            }
        }
    }
}
