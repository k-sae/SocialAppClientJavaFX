package SocialAppClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane GP=new RegisterPage();
        primaryStage.setTitle("btats Network");
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

}
