package SocialAppClient;

import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-11-26.
 */
/**CONTENT IS A CONTAINER OF POSTWRITER AND POSTCONTAINER*/
public class Content extends VBox implements CallBack{
    protected PostWriter postWriter;
    protected PostContainer postContainer;

    public Content(String relation){

        postWriter = new PostWriter();
        postContainer = new PostContainer(relation);
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(postWriter, postContainer);
    }
    @Override
    public void removePostWriter(){
        getChildren().remove(postWriter);
    }

}
