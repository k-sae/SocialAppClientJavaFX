package SocialAppClient;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {




    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{


        Pane mainPane = new StackPane();
        Pane GP=new RegisterPage(mainPane);
        mainPane.getChildren().add(GP);

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

