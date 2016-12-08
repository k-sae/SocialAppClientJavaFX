package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-30.
 */

public class PostViewer extends VBox {
    protected TextField postText;
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
        setPadding(new Insets(10,0,10,0));
        setStyle("-fx-background-color: #ffffff;");

        ChoiceBox<String> edit = new ChoiceBox<>();
        edit.setStyle("-fx-background-color: transparent");
        edit.setPrefWidth(1);
        edit.getItems().addAll("Edit","Delete");

        edit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Edit")){
                postText.setEditable(true);
                postText.requestFocus();
                postText.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        editpost(postText.getText());
                        postText.setEditable(false);
                    }
                });
            }else if(newValue.equals("Delete")){

                 Command command = new Command();
                 command.setKeyWord(Post.DELETE_POST_USERS);
                 command.setSharableObject(post.convertToJsonString());
                 CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.DELETE_POST_USERS)) {
                }
                }
                };
                 CommandsExecutor.getInstance().add(commandRequest);

            }
    });

        postText = new TextField();
        postText.setText(post.getContent());
        postText.setFont(Font.font(18));
        postText.setEditable(false);
        postText.setPadding(new Insets(0,0,10,0));
/*
        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(400);
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

        int check=checkID();
        if(check == -1||post.getLike().get(check).getLike() == -1) {
            setLikeStyle(check);


        }else if(post.getLike().get(check).getLike() == 1){
            setLikeStyle(1
            );

        }else if(post.getLike().get(check).getLike() == 0){
            setLikeStyle(0);

        }
        final int[] finalCheck = new int[1];
        thumbsUp.setOnAction(event -> {
             finalCheck[0] =checkID();
             if(finalCheck[0] == -1||post.getLike().get(finalCheck[0]).getLike() == -1) {
                 setLikeStyle(1);
               setLikecommend(1);
             }else if(post.getLike().get(finalCheck[0]).getLike() == 1){

                setLikeStyle(-1);
                setLikecommend(-1);

             }else if(post.getLike().get(finalCheck[0]).getLike() == 0){

                setLikeStyle(1);
                setLikecommend(1);
             }
            });


        thumbsDown.setOnAction(event -> {
             finalCheck[0] =checkID();
                if(finalCheck[0] == -1||post.getLike().get(finalCheck[0]).getLike() == -1) {
                    setLikeStyle(0);
                setLikecommend(0);
                }else if(post.getLike().get(finalCheck[0]).getLike() == 1){
                    setLikeStyle(0);
                setLikecommend(0);
                }else if(post.getLike().get(finalCheck[0]).getLike() == 0){
                    setLikeStyle(-1);
                setLikecommend(-1);

                }
            });

        ImageView commenticon = new ImageView("file:Resources/comment.png");
        commenticon.setFitWidth(15);
        commenticon.setPreserveRatio(true);

        comment = new Button("Comment", commenticon);
        comment.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        comment.setOnMouseEntered(event -> comment.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        comment.setOnMouseExited(event -> comment.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        comment.setOnMouseClicked(event -> {

            ((CallBack) getParent()).showPostDetails(post);

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

        setMaxWidth(450);
        setMargin(postText, new Insets(0,30,0,30));
        //setMargin(img, new Insets(10,0,20,0));
        getChildren().addAll(new HBox(new FriendView(""+post.getOwnerId()),new Label(post.getDate().toString()),edit), postText, /*img,*/ new Separator(), buttons);

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
                    int check=checkID();
                    if(check ==-1){
                        post.getLike().add(like);
                    }
                    else {
                        post.getLike().get(check).setLike(i);
                    }
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }



   private int checkID(){
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
       return check;
   }



    private void editpost (String text){
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.setContent(text);
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
