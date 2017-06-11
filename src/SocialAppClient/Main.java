package SocialAppClient;

import Connections.Client.CommandsExecutor;
import Connections.Client.ConnectionListener;
import Connections.Client.ServerNotFound;
import SocialAppClient.Control.ClientLoggedUser;
import SocialAppClient.Control.Connections.MainServerConnection;
import SocialAppClient.Control.Utility;
import SocialAppClient.View.MainWindow;
import SocialAppClient.View.RegisterPage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    private static BorderPane windowPane;
    private static StackPane connectionPane;
    private static Label connectionStatus;
    private static Pane mainPane;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        initMainConnection();
        mainPane = new StackPane();
        Pane GP = new RegisterPage(mainPane);
        mainPane.getChildren().add(GP);

        windowPane = new BorderPane(mainPane,connectionPane,null,null,null);
        primaryStage.setTitle("btats Network");
        /** ADD THE APPLICATION ICON */
        primaryStage.getIcons().add(new Image("file:Resources/btatsya.png"));
        primaryStage.setScene(new Scene(windowPane,960,600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        //this will close all active threads with error code of 0
        System.exit(0);
    }
    //Important sent the logged user iD
    public static void refresh(String loggedUserId, Pane page)
    {
        mainPane.getChildren().clear();
        MainWindow mainWindow = new MainWindow(loggedUserId);
        mainPane.getChildren().add(mainWindow);
        MainWindow.navigateTo(page);
    }
    public static void logout(){
        mainPane.getChildren().clear();
        mainPane.getChildren().add(new RegisterPage(mainPane));
    }
    private void initMainConnection()
    {
        connectionStatus = new Label();
        connectionPane = new StackPane();

        //initialize the connection up here
        new Thread(() -> {
            try {
                MainServerConnection mainServerConnection = new MainServerConnection();

                mainServerConnection.setConnectionListener(new ConnectionListener() {
                    @Override
                    public void onStart() {
                        //TODO #belal
                        System.out.println("Connecting...");

                        Platform.runLater(() -> {
                            connectionStatus.setText("CONNECTING...");
                            connectionStatus.setFont(Font.font(16));
                            connectionPane.getChildren().add(connectionStatus);
                            connectionPane.setStyle("-fx-background-color: #ffbb00");
                            connectionPane.setPadding(new Insets(8));
                            connectionPane.setAlignment(Pos.CENTER);
                        });

                    }

                    @Override
                    public void onConnectionSuccess() {
                        //TODO #belal
                        System.out.println("Connected");

                        Platform.runLater(() -> {
                            connectionStatus.setText("CONNECTED");
                            connectionStatus.setFont(Font.font(16));
                            connectionPane.setStyle("-fx-background-color: #00ff00");
                            connectionPane.setPadding(new Insets(8));
                            connectionPane.setAlignment(Pos.CENTER);

                            //Remove the connection status after 5 seconds
                            new Timer().schedule(
                                    new TimerTask() {
                                        @Override
                                        public void run() {
                                            Platform.runLater(() -> {
                                                connectionPane.getChildren().clear();
                                                connectionPane.setStyle(null);
                                                connectionPane.setPadding(new Insets(0));
                                            });
                                        }
                                    },
                                    5000
                            );

                        });

                    }
                });
                CommandsExecutor.getInstance().setOnTransmissionFailure(() -> { // this trigger whenever client try to send package and fail
                    //TODO #belal
                    System.out.println("connection lost and reconnecting");
                    mainServerConnection.endConnection();
                    mainServerConnection.reconnect();
                    login();
                    CommandsExecutor.getInstance().updateSocket(mainServerConnection.getConnectionSocket());
                });
                mainServerConnection.connect();
            }catch (ServerNotFound e)
            {
                Utility.cantConnectMessage();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void login()
    {
        if (RegisterPage.loginInfo == null) return;
        new ClientLoggedUser.Login(RegisterPage.loginInfo) {
            @Override
            public void onFinish(String id) {
                if (id.equals("-1")){
                    logout();
                }
            }
        };
    }

}

