package SocialAppClient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane GP=new MainWindow();
        Pane mainPane = new StackPane();
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
