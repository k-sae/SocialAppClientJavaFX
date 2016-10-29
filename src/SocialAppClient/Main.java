package SocialAppClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       GridPane GP=mainWindowCreater();



        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(GP,800,600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        //this will close all active threads with error code of 0
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
public GridPane mainWindowCreater(){
    GridPane Gp=new GridPane();
    Gp.setPadding(new Insets(10,10,10,10));
    ColumnConstraints col1=new ColumnConstraints();
    col1.setPercentWidth(10);
    ColumnConstraints col2=new ColumnConstraints();
    col2.setPercentWidth(80);
    ColumnConstraints col3=new ColumnConstraints();
    col2.setPercentWidth(10);
    BackgroundImage bi=new BackgroundImage(new Image("file:C:\\Users\\mosta\\OneDrive\\documents\\GitHub\\SocialAppClientJavaFX\\src\\resource\\background.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    Gp.setBackground(new Background(bi));

    Gp.getChildren().add(navbarCreator());
    Gp.setColumnSpan(navbarCreator(),1);
    return Gp;
}
    public HBox navbarCreator(){
        HBox Hb=new HBox();
        Hb.setMinWidth();
        Hb.setMinHeight();
        Hb.setStyle("-fx-background-color: #fff");
        return Hb;
    }
    public Button createRoundButtons(){
        Button b=new Button();
       b.setStyle("-fx-background-radius: 50%;");
        return b;
    }
    public TextField textFieldCreator(){
        TextField Tf=new TextField();
        Tf.setMinWidth(200);
        Tf.setMinHeight(100);
        return Tf;

    }

}
