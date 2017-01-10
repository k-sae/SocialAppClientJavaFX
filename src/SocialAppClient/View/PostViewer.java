package SocialAppClient.View;

import SocialAppClient.Control.Utility;
import SocialAppGeneral.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;

/**
 * Created by billy on 2016-11-30.
 */

class PostViewer extends VBox{
    private TextArea postText;
    private Button thumbsUp;
    private Button thumbsDown;
    Button comment;
    private Button share;
    private Post post;
    private String relation;
    private int likeNum;
    private int dislikeNum;
    private int commentNum;

    PostViewer(String relation, Post post) {
        this.post = post;
        this.relation = relation;
        likeNum = 0;
        dislikeNum = 0;
        commentNum = 0;
        setLayout();
    }

    private void setLayout() {
        setPadding(new Insets(10, 0, 10, 0));
        setStyle(Styles.WHITE_BACKGROUND);

        for(Like like: post.getLike()){
            if(like.getLike().equals(Relations.THUMP_UP))
                likeNum++;
            else
                dislikeNum++;
        }
        for(Comment ignored :post.getComments()){
            commentNum++;
        }

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
                                if(relation.equals(Relations.HOME_PAGE.toString()))
                                    MainWindow.navigateTo(new HomePage(MainWindow.id));
                                else
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
                        if(relation.equals(Relations.HOME_PAGE.toString()))
                            MainWindow.navigateTo(new HomePage(MainWindow.id));
                        else
                            MainWindow.navigateTo(new ProfilePage(""+post.getPostPos()));
                    }else if(relation.equals(Relations.GROUP.toString())){
                        MainWindow.clientLoggedUser.deletePostGroup(post);
                        MainWindow.navigateTo(new GroupPage(post.getPostPos()));
                    }
                    edit.setValue(null);
                }
            }catch (NullPointerException ignored){

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

        Label likeNumLBL = new Label("\tThumbs up " + likeNum + "\tThumbs down " + dislikeNum);

        ImageView TUicon = new ImageView("file:Resources/TU.png");
        TUicon.setFitWidth(15);
        TUicon.setPreserveRatio(true);

        thumbsUp = new Button("Thumb UP", TUicon);

        ImageView TDicon = new ImageView("file:Resources/TD.png");
        TDicon.setFitWidth(15);
        TDicon.setPreserveRatio(true);

        thumbsDown = new Button("Thumb Down", TDicon);

        int check = Utility.checkID(post);
        if (check == -1 || post.getLike().get(check).getLike().equals(Relations.DELETE)) {
            setLikeStyle(check);

        } else if (post.getLike().get(check).getLike().equals(Relations.THUMP_UP)) {
            setLikeStyle(1);

        } else if (post.getLike().get(check).getLike().equals(Relations.THUMP_DOWN)) {
            setLikeStyle(0);

        }
        final int[] finalCheck = new int[1];
        thumbsUp.setOnAction(event -> {
            //TODO ???
            //  3 errors
            // seems Useless Check
            if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                finalCheck[0] = Utility.checkID(post);
                //whats is -1 supposed to refer to ?
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike().equals(Relations.DELETE)) {
                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.THUMP_UP, post);
                    likeNumLBL.setText("\tThumbs up " + ++likeNum + "\tThumbs down " + dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_UP)) { //CRITICAL ISSUE

                    setLikeStyle(-1); // ???
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.DELETE, post);
                    likeNumLBL.setText("\tThumbs up " + --likeNum + "\tThumbs down " + dislikeNum);

                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_DOWN)) {

                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.THUMP_UP, post);
                    likeNumLBL.setText("\tThumbs up " + ++likeNum + "\tThumbs down " + --dislikeNum);
                }
            }else if(relation.equals(Relations.GROUP.toString())){
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike().equals(Relations.DELETE)) {
                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.THUMP_UP, post);
                    likeNumLBL.setText("\tThumbs up " + ++likeNum + "\tThumbs down " + dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_UP)) {

                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.DELETE, post);
                    likeNumLBL.setText("\tThumbs up " + --likeNum + "\tThumbs down " + dislikeNum);

                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_DOWN)) {

                    setLikeStyle(1);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.THUMP_UP, post);
                    likeNumLBL.setText("\tThumbs up " + ++likeNum + "\tThumbs down " + --dislikeNum);
                }
            }
        });

        thumbsDown.setOnAction(event -> {
            if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike().equals(Relations.DELETE)) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.THUMP_DOWN, post);
                    likeNumLBL.setText("\tThumbs up " + likeNum + "\tThumbs down " + ++dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_UP)) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.THUMP_DOWN, post);
                    likeNumLBL.setText("\tThumbs up " + --likeNum + "\tThumbs down " + ++dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_DOWN)) {
                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikeCommandUsers(Relations.DELETE, post);
                    likeNumLBL.setText("\tThumbs up " + likeNum + "\tThumbs down " + --dislikeNum);

                }
            }else if(relation.equals(Relations.GROUP.toString())){
                finalCheck[0] = Utility.checkID(post);
                if (finalCheck[0] == -1 || post.getLike().get(finalCheck[0]).getLike().equals(Relations.DELETE)) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.THUMP_DOWN, post);
                    likeNumLBL.setText("\tThumbs up " + likeNum + "\tThumbs down " + ++dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_UP)) {
                    setLikeStyle(0);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.THUMP_DOWN, post);
                    likeNumLBL.setText("\tThumbs up " + --likeNum + "\tThumbs down " + ++dislikeNum);
                } else if (post.getLike().get(finalCheck[0]).getLike().equals(Relations.THUMP_DOWN)) {
                    setLikeStyle(-1);
                    MainWindow.clientLoggedUser.setLikeCommandGroup(Relations.DELETE, post);
                    likeNumLBL.setText("\tThumbs up " + likeNum + "\tThumbs down " + --dislikeNum);

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

        comment.setOnMouseClicked(event -> Content.showPostDetails(post));

        ImageView shareicon = new ImageView("file:Resources/share.png");
        shareicon.setFitWidth(15);
        shareicon.setPreserveRatio(true);

        share = new Button("Repost", shareicon);
        share.setStyle(Styles.POST_BUTTONS);
        share.setOnMouseEntered(event -> share.setStyle(Styles.POST_BUTTONS_HOVER));
        share.setOnMouseExited(event -> share.setStyle(Styles.POST_BUTTONS));
        share.setOnMouseClicked(event -> MainWindow.clientLoggedUser.savePostUser(relation, postText.getText()));

        HBox buttons = new HBox(thumbsUp, thumbsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        setMaxWidth(450);
        setMargin(postText, new Insets(0, 30, 0, 30));
        //setMargin(img, new Insets(10,0,20,0));
        String date = new SimpleDateFormat("yyyy MMMMM dd hh:mm aaa").format(post.getDate());
        HBox h = new HBox(new Label(date), edit);
        h.setAlignment(Pos.TOP_RIGHT);
        HBox hBox = new HBox(new FriendView("" + post.getOwnerId()), h);
        HBox.setHgrow(h, Priority.ALWAYS);
        getChildren().addAll(hBox, postText, /*img,*/new HBox(5,likeNumLBL,new Label("Comments "+commentNum)), new Separator(), buttons);

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
