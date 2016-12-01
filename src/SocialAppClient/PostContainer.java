package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-26.
 */
public class PostContainer extends VBox {

    public PostContainer(){
        setAlignment(Pos.TOP_CENTER);
        setSpacing(30);
        setPadding(new Insets(0,0,20,0));

        PostViewer postViewer = new PostViewer();

        getChildren().addAll(postViewer);
    }
}
