package SocialAppClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.ParameterMetaData;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
        Pane GP=new RegisterPage();
=======
        Pane mainPane = new StackPane();
        GridPane GP=new RegisterPage();
        mainPane.getChildren().add(GP);
>>>>>>> 37e88fd14b2021f71bbde0117893c85e29919d13
        primaryStage.setTitle("btats Network");
        primaryStage.setScene(new Scene(mainPane,800,600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        //this will close all active threads with error code of 0
        System.exit(0);
    }

}
