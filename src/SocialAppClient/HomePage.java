package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Optional;

/**
 * Created by kemo on 10/11/2016.
 */
public class HomePage extends GridPane {
    public HomePage()
    {
        //setBackground(new Background(new BackgroundFill(Color.web(ClientTheme.BackGround, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));

        setGridLinesVisible(true);
        setConstraint();
        setPanels();
    }

    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(25);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(75);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }

    private void setPanels(){

        HomePageInfoViewer Info = new HomePageInfoViewer();

        /**PUT THE PICTURE PATH*/
        //Info.setPicture();
        /**PUT SOME INFO AS STRING*/
        //Info.setLabel();
        Info.setButtons();


        add(Info,0,0);

           Info.CreateGroupBtn.setOnMouseClicked(event -> {
               /*
               Optional<String> check= Utility.createWindow("Group Name","Create Group");
               if(!check.equals(Optional.empty())){
                   if (check.get().equals("")) {
                       Utility.errorWindow("No name you enter");
                   } else {
                       Command command = new Command();
                       command.setKeyWord(Group.CREATE_GROUP);
                       command.setSharableObject(check.get());
                       CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                           @Override
                           void analyze(Command cmd) {
                               if (cmd.getKeyWord().equals(Group.CREATE_GROUP)){
                                   Group group  = Group.fromJsonString(cmd.getObjectStr());
                                   //TODO #Fix
                                   //fix error on threading
                                   Platform.runLater(() -> MainWindow.navigateTo(new GroupPage()));


                               }
                           }
                       };
                       CommandsExecutor.getInstance().add(commandRequest);

                   }


               }
               */
               /*to add post
               Post post=new Post();
               post.setOwnerId(2);
               post.setContent("khaled");
               post.setPostPos(1);
               Command command = new Command();
               command.setKeyWord(Post.SAVE_POST);
               command.setSharableObject(post.convertToJsonString());
               CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                   @Override
                   void analyze(Command cmd) {
                       if (cmd.getKeyWord().equals(Post.SAVE_POST)){
                         post.equals(Post.fromJsonString(cmd.getObjectStr())) ;
                         System.out.println(cmd.getObjectStr());
                       }
                   }
               };
               CommandsExecutor.getInstance().add(commandRequest);





           });*/
               /*
               Post post=new Post();
               post.setOwnerId(2);
               post.setPostPos(1);
               post.setId(1);
               Command command = new Command();
               command.setKeyWord(Post.LOAD_POST);
               command.setSharableObject(post.convertToJsonString());
               CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                   @Override
                   void analyze(Command cmd) {
                       if (cmd.getKeyWord().equals(Post.LOAD_POST)){
                           post.equals(Post.fromJsonString(cmd.getObjectStr())) ;
                           System.out.println(cmd.getObjectStr());
                       }
                   }
               };
               CommandsExecutor.getInstance().add(commandRequest);





           });
           */
               Post post=new Post();
               post.setOwnerId(2);
               post.setPostPos(1);
               post.setId(1);
               Like like =new Like();
               like.setLike(1);
               like.setOwnerID(2);
               System.out.println(like);
               post.addlike(like);
               Command command = new Command();
               command.setKeyWord(Post.Add_COMMENT);
               command.setSharableObject(post.convertToJsonString());
               CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                   @Override
                   void analyze(Command cmd) {
                       if (cmd.getKeyWord().equals(Post.Add_COMMENT)){
                           post.equals(Post.fromJsonString(cmd.getObjectStr())) ;
                           System.out.println(cmd.getObjectStr());
                       }
                   }
               };
               CommandsExecutor.getInstance().add(commandRequest);





           });

        Content content = new Content();
        content.postContainer.addPosts();

        add(content,1,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}
