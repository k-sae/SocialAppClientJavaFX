package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setSpacing(30);
        setPadding(new Insets(0,0,20,0));
    }

    public void addPosts(){
        /** IT WILL TAKE ARRAYLIST FROM POSTS*/
        /**
         * int i = 0;
         * for (ArrayList<Posts> posts: posts){
         * PostViewer postViewer = new PostViewer(posts.get(i));
         * getChildren().add(postViewer);
         * i++;
         */

        PostViewer postViewer = new PostViewer();
        getChildren().addAll(postViewer);

        Button load = new Button("LOAD MORE");
        load.setOnMouseClicked(event -> {
            getChildren().remove(load);
            for(int i=0; i<10; i++)
                getChildren().add(new PostViewer());
            getChildren().addAll(load);
        });

        getChildren().addAll(load);
    }


}
