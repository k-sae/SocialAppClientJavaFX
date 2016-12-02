package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-11-26.
 */
/**CONTENT IS A CONTAINER OF POSTWRITER AND POSTCONTAINER*/
public class Content extends VBox {
    protected PostWriter postWriter;
    protected PostContainer postContainer;
    public Content(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        postWriter = new PostWriter();
        postContainer = new PostContainer();
        getChildren().addAll(postWriter, postContainer, new PostContainer());
    }
}
