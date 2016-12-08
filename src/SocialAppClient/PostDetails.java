package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-12-07.
 */
public class PostDetails extends VBox implements CallBack{
    protected PostViewer postViewer;
    protected CommentContainer CommentContainer;
    private Post post;
    public PostDetails(Post post){
        this.post = post;
        postViewer = new PostViewer(this.post);
        postViewer.comment.setOnMouseClicked(null);

        CommentContainer = new CommentContainer(this.post.getComments());
        CommentContainer.commentText.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                setCommentCommend(1, CommentContainer.commentText.getText(),0);
                CommentContainer.commentText.setText("");
                ((CallBack)getParent()).showPostDetails(post);
            }
        });
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(postViewer,CommentContainer);
    }
    public void setCommentCommend(int show, String text, long id){
        Comment comment=new Comment();
        comment.setCommentcontent(text);
        comment.setOwnerID(Long.parseLong(MainWindow.id));
        comment.setShow(show);
        comment.setCommentId(id);
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.addcomment(comment);
        Command command = new Command();
        command.setKeyWord(Post.EDIT_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDIT_POST_USERS)) {

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    @Override
    public void showPostDetails(Post post) {

    }

    @Override
    public void removePostWriter() {

    }

}
