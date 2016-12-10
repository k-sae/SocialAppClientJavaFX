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

public class PostViewer extends VBox{
    protected TextArea postText;
    protected Button thumbsUp;
    protected Button thumbsDown;
    protected Button comment;
    protected Button share;
    private Post post;

    public PostViewer(Post post) {
        this.post = post;
        System.out.println(post.convertToJsonString());
        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10, 0, 10, 0));
        setStyle(Styles.WHITE_BACKGROUND);

        postText = new TextArea();
        postText.setText(post.getContent());
        postText.setFont(Font.font(18));
        postText.setEditable(false);
        postText.setWrapText(true);
        postText.setPrefHeight(postText.getText().length());
        postText.setPadding(new Insets(0,0,10,0));
        postText.setOnKeyTyped(event -> postText.setPrefHeight(postText.getText().length()));
        postText.setStyle(Styles.WHITE_BACKGROUND);

        ChoiceBox<String> edit = new ChoiceBox<>();
        edit.setStyle(Styles.TRANSPARENT_BACKGROUND);
        edit.setPrefWidth(1);
        edit.getItems().addAll("Edit", "Delete");

        edit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Edit")) {
                postText.setEditable(true);
                postText.requestFocus();
                postText.setStyle(null);
                postText.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        editpost(postText.getText());
                        postText.setEditable(false);
                        postText.setStyle(Styles.WHITE_BACKGROUND);
                    }
                });
            } else if (newValue.equals("Delete")) {

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
        if(!MainWindow.id.equals(""+post.getOwnerId()))
            edit.setVisible(false);

/*
        Image im = new Image(POST_IMAGE_PATH);
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

        ImageView TDicon = new ImageView("file:Resources/TD.png");
        TDicon.setFitWidth(15);
        TDicon.setPreserveRatio(true);

        thumbsDown = new Button("Thumb Down", TDicon);

        int check = checkID();
        if (check == -1 || post.getLike().get(check).getLike() == -1) {
            setLikeStyle(check);

        } else if (post.getLike().get(check).getLike() == 1) {
            setLikeStyle(1);

        } else if (post.getLike().get(check).getLike() == 0) {
            setLikeStyle(0);

        }
        final int[] finalCheck = new int[1];
        thumbsUp.setOnAction(event -> {
            finalCheck[0] = checkID();
            if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                setLikeStyle(1);
                setLikecommend(1);
            } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {

                setLikeStyle(-1);
                setLikecommend(-1);

            } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {

                setLikeStyle(1);
                setLikecommend(1);
            }
        });

        thumbsDown.setOnAction(event -> {
            finalCheck[0] = checkID();
            if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                setLikeStyle(0);
                setLikecommend(0);
            } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {
                setLikeStyle(0);
                setLikecommend(0);
            } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {
                setLikeStyle(-1);
                setLikecommend(-1);

            }
        });

        ImageView commenticon = new ImageView("file:Resources/comment.png");
        commenticon.setFitWidth(15);
        commenticon.setPreserveRatio(true);

        comment = new Button("Comment", commenticon);
        comment.setStyle(Styles.POST_BUTTONS);
        comment.setOnMouseEntered(event -> comment.setStyle(Styles.POST_BUTTONS_HOVER));
        comment.setOnMouseExited(event -> comment.setStyle(Styles.POST_BUTTONS));

        comment.setOnMouseClicked(event -> ((CallBack) getParent()).showPostDetails(post));

        ImageView shareicon = new ImageView("file:Resources/share.png");
        shareicon.setFitWidth(15);
        shareicon.setPreserveRatio(true);

        share = new Button("Repost", shareicon);
        share.setStyle(Styles.POST_BUTTONS);
        share.setOnMouseEntered(event -> share.setStyle(Styles.POST_BUTTONS_HOVER));
        share.setOnMouseExited(event -> share.setStyle(Styles.POST_BUTTONS));
        share.setOnMouseClicked(event -> {
            Post post1 = new Post();
            post1.setOwnerId(Long.parseLong(MainWindow.id));
            post1.setContent(post.getContent());
            post1.setPostPos(Long.parseLong(MainWindow.id));
            Command command = new Command();
            command.setKeyWord(Post.SAVE_POST_USER);
            command.setSharableObject(post1.convertToJsonString());
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
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
        setMargin(postText, new Insets(0, 30, 0, 30));
        //setMargin(img, new Insets(10,0,20,0));
        getChildren().addAll(new HBox(new FriendView("" + post.getOwnerId()), new Label(post.getDate().toString()), edit), postText, /*img,*/ new Separator(), buttons);

    }

    private void setLikeStyle(int i) {
        if (i == -1) {
            thumbsUp.setStyle(Styles.POST_BUTTONS);
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle(Styles.POST_BUTTONS_HOVER));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle(Styles.POST_BUTTONS));
            thumbsDown.setStyle(Styles.POST_BUTTONS);
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle(Styles.POST_BUTTONS_HOVER));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle(Styles.POST_BUTTONS));


        } else if (i == 1) {
            thumbsUp.setStyle(Styles.THUMBUP_BUTTON);
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle(Styles.THUMBUP_BUTTON_HOVER));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle(Styles.THUMBUP_BUTTON));
            thumbsDown.setStyle(Styles.POST_BUTTONS);
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle(Styles.POST_BUTTONS_HOVER));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle(Styles.POST_BUTTONS));
        } else {
            thumbsDown.setStyle(Styles.THUMBDOWN_BUTTON);
            thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle(Styles.THUMBDOWN_BUTTON_HOVER));
            thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle(Styles.THUMBDOWN_BUTTON));
            thumbsUp.setStyle(Styles.POST_BUTTONS);
            thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle(Styles.POST_BUTTONS_HOVER));
            thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle(Styles.POST_BUTTONS));

        }
    }

    private void setLikecommend(int i) {
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
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDIT_POST_USERS)) {
                    int check = checkID();
                       boolean b= Boolean.parseBoolean(cmd.getObjectStr());
                    if (b) {
                        if (check == -1) {
                            post.getLike().add(like);
                        } else {
                            post.getLike().get(check).setLike(i);
                        }

                    } else {
                       Utility.errorWindow("please refresh window");
                    }

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    private int checkID() {
        int i = 0;
        int check = -1;
        if (post.getLike().size() != 0) {
            do {
                if (post.getLike().get(i).getOwnerID() == Long.parseLong(MainWindow.id)) {
                    check = i;
                }
                i++;
            }
            while (i < post.getLike().size() && post.getLike().get(i).getOwnerID() != Long.parseLong(MainWindow.id));
        }
        return check;
    }

    private void editpost(String text) {
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.setContent(text);
        Command command = new Command();
        command.setKeyWord(Post.EDIT_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDIT_POST_USERS)) {
                    System.out.println(cmd.getObjectStr());
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

}
