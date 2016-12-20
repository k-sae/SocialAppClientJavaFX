package SocialAppClient;

import SocialAppGeneral.Post;
import SocialAppGeneral.Relations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-12-07.
 */
class PostDetails extends VBox{
    private PostViewer postViewer;
    private CommentContainer CommentContainer;
    private static Post post;
    private static String relation;
    PostDetails(String relation, Post post){
        PostDetails.post = post;
        PostDetails.relation = relation;
        postViewer = new PostViewer(relation, PostDetails.post);
        postViewer.comment.setOnMouseClicked(null);

        CommentContainer = new CommentContainer(PostDetails.post.getComments());
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(30, 0, 30, 0));
        getChildren().addAll(postViewer,CommentContainer);
    }
    static void setCommentCommend(Relations show, String text, long id){
        if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
            MainWindow.clientLoggedUser.setCommentCommendUser(show, text, id, post.getId(), post.getPostPos());
        }else if(relation.equals(Relations.GROUP.toString())){
            MainWindow.clientLoggedUser.setCommentCommendGroup(show, text, id, post.getId(), post.getPostPos());
        }

    }

}
