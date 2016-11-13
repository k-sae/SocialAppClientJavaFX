package SocialAppClient;

import javafx.application.Application;
import javafx.application.Platform;
<<<<<<< HEAD
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
=======
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
>>>>>>> 9e4642b80bbf23d99f4e3f586dd5f770ff0227a8
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
<<<<<<< HEAD
    Stage window;
    Button button;


    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("thenewboston - JavaFX");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        window.setOnCloseRequest(e->{
            e.consume();
            closeprogram();
        } );

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel,0,0);

        //Name Input
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput, 1, 0);
           nameInput.setPrefSize(200,20);
        //Password Label
        Label passLabel = new Label("Password:");

        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        Button loginButton = new Button("Log In");
        loginButton.setPrefSize(120,60);
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e-> isint(passInput,passInput.getText()));

        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
    }
     public  void closeprogram (){
         Boolean answer =Confirmbox.display("close window","are you shure close window?");
                 if(answer)
                     window.close();


     }
     public  Boolean isint (TextField input,String  mes){
         try {
             int age=Integer.parseInt(input.getText());
             Confirmbox.display("your age",mes);
             return  true;

         }
         catch (NumberFormatException e){
             Confirmbox.display("inger","this is not intger");
             return false;
         }
     }
=======
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

>>>>>>> 9e4642b80bbf23d99f4e3f586dd5f770ff0227a8
}

