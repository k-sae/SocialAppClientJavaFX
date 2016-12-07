package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
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

/**
 * Created by billy on 2016-11-30.
 */
public class PostViewer extends VBox {
    protected Label postText;
    protected Button thumbsUp;
    protected Button thumbsDown;
    protected Button comment;
    protected Button share;
    private Post post;
    public PostViewer(Post post){
        this.post = post;
        System.out.println(post.convertToJsonString());
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,0,20,0));
        setStyle("-fx-background-color: #ffffff;");

        postText = new Label();
        postText.setText(post.getContent());
        postText.setFont(Font.font(16));
        postText.setWrapText(true);
        postText.setPadding(new Insets(0,0,10,0));
/*
        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(350);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);
*/
        ImageView TUicon = new ImageView("file:Resources/TU.png");
        TUicon.setFitWidth(15);
        TUicon.setPreserveRatio(true);

        thumbsUp = new Button("Thumb UP", TUicon);

        /*
        int finalI = i;
        thumbsUp.setOnMouseClicked(event -> {
            if( finalI ==0 ||post.getLike().get(finalI).getLike()==0  ) {
                thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #0000ff");
                thumbsUp.setOnMouseEntered(event1 -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #0000ff"));
                thumbsUp.setOnMouseExited(event1 -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #0000ff"));
                thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                thumbsDown.setOnMouseEntered(event1 -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
                thumbsDown.setOnMouseExited(event1 -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

            }else if (post.getLike().get(finalI).getLike()== 1){
                thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                thumbsUp.setOnMouseEntered(event1 -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
                thumbsUp.setOnMouseExited(event1 -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
            }
        });*/
        ImageView TDicon = new ImageView("file:Resources/TD.png");
        TDicon.setFitWidth(15);
        TDicon.setPreserveRatio(true);

        thumbsDown = new Button("Thumb Down", TDicon);

        /*
        thumbsDown.setOnMouseClicked(event -> {
            if(finalI ==0 ||post.getLike().get(finalI).getLike()==0) {
                thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ff0000");
                thumbsDown.setOnMouseEntered(event1 -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #ff0000"));
                thumbsDown.setOnMouseExited(event1 -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ff0000"));
                thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                thumbsUp.setOnMouseEntered(event1 -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
                thumbsUp.setOnMouseExited(event1 -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

            }else if(post.getLike().get(finalI).getLike()==-1){
                thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                thumbsDown.setOnMouseEntered(event1 -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
                thumbsDown.setOnMouseExited(event1 -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
            }
        });
*/

        int i = 0;
        int check = -1;
        if(post.getLike().size() !=0) {
            do {
                if (post.getLike().get(i).getOwnerID() == Long.parseLong(MainWindow.id)) {
                    check = i;
                }
                i++;
            } while (i < post.getLike().size() && post.getLike().get(i).getOwnerID() != Long.parseLong(MainWindow.id));
        }
        if(check == -1) {
            setLikeStyle(check);
            thumbsUp.setOnMouseClicked(event -> {
                setLikeStyle(1);
               setLikecommend(1);
            });
            thumbsDown.setOnMouseClicked(event -> {
                setLikeStyle(0);
                setLikecommend(0);
            });

        }else if(post.getLike().get(check).getLike() == 1){
            setLikeStyle(1);
            thumbsUp.setOnMouseClicked(event -> {
                setLikeStyle(-1);
                setLikecommend(-1);
            });
            thumbsDown.setOnMouseClicked(event -> {
                setLikeStyle(0);
                setLikecommend(0);
                    }
            );
        }else{
            setLikeStyle(0);
            thumbsUp.setOnMouseClicked(event -> {
                setLikeStyle(1);
                setLikecommend(1);
            });
            thumbsDown.setOnMouseClicked(event -> {
                setLikeStyle(-1);
                setLikecommend(-1);
            });
        }

        ImageView commenticon = new ImageView("file:Resources/comment.png");
        commenticon.setFitWidth(15);
        commenticon.setPreserveRatio(true);

        comment = new Button("Comment", commenticon);
        comment.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        comment.setOnMouseEntered(event -> comment.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        comment.setOnMouseExited(event -> comment.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
          comment.setOnMouseClicked(event -> {
              setCommentCommend(true);
          });
        ImageView shareicon = new ImageView("file:Resources/share.png");
        shareicon.setFitWidth(15);
        shareicon.setPreserveRatio(true);

        share = new Button("Repost" ,shareicon);
        share.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        share.setOnMouseEntered(event -> share.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        share.setOnMouseExited(event -> share.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
        share.setOnMouseClicked(event -> {
            Post post1=new Post();
            post1.setOwnerId(Long.parseLong(MainWindow.id));
            post1.setContent(post.getContent());
            post1.setPostPos(Long.parseLong(MainWindow.id));
            Command command = new Command();
            command.setKeyWord(Post.SAVE_POST_USER);
            command.setSharableObject(post1.convertToJsonString());
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    if (cmd.getKeyWord().equals(Post.SAVE_POST_USER)) {

                    }
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });

        HBox buttons = new HBox(thumbsUp, thumbsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        setMaxWidth(400);
        setMargin(postText, new Insets(0,30,0,30));
        //setMargin(img, new Insets(10,0,20,0));
        getChildren().addAll(new FriendView(""+post.getOwnerId()), postText, /*img,*/ new Separator(), buttons);

    }
    private void setLikeStyle(int i){
        if(i == -1) {
            thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
            thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));


        }else if(i == 1){
            thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #0000ff");
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #0000ff"));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #0000ff"));
            thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));
        }else{
            thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ff0000");
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #ff0000"));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ff0000"));
            thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        }
    }
    private  void setLikecommend(int i){
        Like like = new Like();
        like.setLike(i);
        like.setOwnerID(Long.parseLong(MainWindow.id));
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.addlike(like);
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
    private void setCommentCommend(boolean show){
        Comment comment=new Comment();
        comment.setCommentcontent("khaled");
        comment.setOwnerID(Long.parseLong(MainWindow.id));
        comment.setShow(show);
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

}
