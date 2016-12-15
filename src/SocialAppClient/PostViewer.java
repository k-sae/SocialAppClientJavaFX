package SocialAppClient;

import SocialAppGeneral.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
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
    private String relation;

    public PostViewer(String relation, Post post) {
        this.post = post;
        this.relation = relation;
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
            try {
                if (newValue.equals("Edit")) {
                    postText.setEditable(true);
                    postText.requestFocus();
                    postText.setStyle(null);
                    postText.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            postText.setEditable(false);
                            postText.setStyle(Styles.WHITE_BACKGROUND);
                            if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                                MainWindow.clientLoggedUser.editPostUser(post.getId(), post.getPostPos(), postText.getText());
                                MainWindow.navigateTo(new ProfilePage(""+post.getPostPos()));
                            }else if(relation.equals(Relations.GROUP.toString())){
                                MainWindow.clientLoggedUser.editPostGroup(post.getId(), post.getPostPos(), postText.getText());
                                MainWindow.navigateTo(new GroupPage(post.getPostPos()));
                            }
                            edit.setValue(null);
                        }
                    });
                } else if (newValue.equals("Delete")) {
                    if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                        MainWindow.clientLoggedUser.deletePostUser(post);
                        MainWindow.navigateTo(new ProfilePage(""+post.getPostPos()));
                    }else if(relation.equals(Relations.GROUP.toString())){
                        MainWindow.clientLoggedUser.deletePostGroup(post);
                        MainWindow.navigateTo(new GroupPage(post.getPostPos()));
                    }
                    edit.setValue(null);
                }
            }catch (NullPointerException e){

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

        int check = Utility.checkID(post);
        if (check == -1 || post.getLike().get(check).getLike() == -1) {
            setLikeStyle(check);

        } else if (post.getLike().get(check).getLike() == 1) {
            setLikeStyle(1);

        } else if (post.getLike().get(check).getLike() == 0) {
            setLikeStyle(0);

        }
        final int[] finalCheck = new int[1];
        thumbsUp.setOnAction(event -> {
            if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikecommendUsers(1, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {

                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikecommendUsers(-1, post);

                } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {

                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikecommendUsers(1, post);
                }
            }else if(relation.equals(Relations.GROUP.toString())){
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikecommendGroup(1, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {

                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikecommendGroup(-1, post);

                } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {

                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikecommendGroup(1, post);
                }
            }
        });

        thumbsDown.setOnAction(event -> {
            if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikecommendUsers(0, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikecommendUsers(0, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {
                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikecommendUsers(-1, post);

                }
            }else if(relation.equals(Relations.GROUP.toString())){
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike() == -1) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikecommendGroup(0, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 1) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikecommendGroup(0, post);
                } else if (post.getLike().get(finalCheck[0]).getLike() == 0) {
                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikecommendGroup(-1, post);

                }
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
            MainWindow.clientLoggedUser.savePostUser(relation, postText.getText());
        });

        HBox buttons = new HBox(thumbsUp, thumbsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        setMaxWidth(450);
        setMargin(postText, new Insets(0, 30, 0, 30));
        //setMargin(img, new Insets(10,0,20,0));
        HBox h = new HBox(new Label(post.getDate().toString()), edit);
        h.setAlignment(Pos.TOP_RIGHT);
        HBox hBox = new HBox(new FriendView("" + post.getOwnerId()), h);
        hBox.setHgrow(h, Priority.ALWAYS);
        getChildren().addAll(hBox, postText, /*img,*/ new Separator(), buttons);

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

}
