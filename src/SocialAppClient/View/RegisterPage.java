package SocialAppClient.View;

import SocialAppClient.Connections.*;
import SocialAppClient.Control.Utility;
import SocialAppClient.Control.validator;
import SocialAppClient.SocialAppGeneral.Command;
import SocialAppClient.SocialAppGeneral.LoginInfo;
import SocialAppClient.SocialAppGeneral.RegisterInfo;
import SocialAppClient.SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDate;

/**
 * Created by mosta on 30-Oct-16.
 */
public class RegisterPage extends StackPane {
    private Pane parent;
 public RegisterPage(Pane parent){
     this.parent = parent;
     this.setBackground(new Background(
             new BackgroundImage(
             new Image("file:Resources/background.jpg", true),
             BackgroundRepeat.NO_REPEAT,
             BackgroundRepeat.NO_REPEAT,
             BackgroundPosition.DEFAULT,
             new BackgroundSize(100,100,true,true,true,true))));
    GridPane gp=new GridPane();
     setRow(gp);
     setCol(gp);
     gp.setStyle("-fx-background-color:rgba(9,9,9,0.4)");
     this.getChildren().add(gp);
     layoutEditor(gp);
     //initialize the connection up here
     new Thread(() -> {
         try {
           MainServerConnection mainServerConnection = new MainServerConnection();
             mainServerConnection.connect();
             mainServerConnection.setConnectionListener(new ConnectionListener() {
                 @Override
                 public void onStart() {
                     //TODO #belal
                 }

                 @Override
                 public void onConnectionSuccess() {
                    //TODO #belal
                 }
             });
             CommandsExecutor.getInstance().setOnTransmissionFailure(() -> { // this trigger whenever client try to send package and fail
                 //TODO #belal
             });
         }catch (ServerNotFound e)
         {
             Utility.cantConnectMessage();

         }
         catch (Exception e) {
             e.printStackTrace();
         }
     }).start();


 }
    private void setCol(GridPane gridPane)
    {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(70);
        ColumnConstraints col3=new ColumnConstraints();
        col3.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(columnConstraints,columnConstraints1,col3);

    }
    private void setRow(GridPane gridPane)
    {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(15);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(75);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setPercentHeight(10);
        gridPane.getRowConstraints().addAll(rowConstraints,rowConstraints1,rowConstraints2);
    }

     private BorderPane  navbarCreator(){
         BorderPane  Bp=new BorderPane ();
         HBox Hb=new HBox(5);
         Label email_label=createTxt("Email:",20,true);
         TextField email=textFieldCreator(true);
         Label passwordlabel=createTxt("Password:",20,true);
         PasswordField pass=new PasswordField();
         pass.setMaxWidth(250);
         pass.setMinHeight(25);
         Button loginButton=new Button("login");
         //Hb.setBackground(new Background(new BackgroundFill(Color.AQUA,null,null)));
         Hb.getChildren().addAll(email_label,email,passwordlabel,pass,loginButton);
         loginButton.setOnAction(event -> {
             //TODO #hazem
             //check for input
             LoginInfo log=new LoginInfo();
             log.setEmail(email.getText());
             log.setPassword(pass.getText());
             Command command = new Command();
             command.setKeyWord(LoginInfo.NEW_LOGIN);
             command.setSharableObject(log);
             CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                 @Override
                 public void analyze(Command commandFromServer) {
                     Platform.runLater(() -> {
                         System.out.println(commandFromServer.getObjectStr());

                         if (!commandFromServer.getObjectStr().equals("-1")) {
                             parent.getChildren().add(new MainWindow(commandFromServer.getObjectStr()));
                             parent.getChildren().remove(RegisterPage.this);
                         }
                         else
                         {
                             Utility.alertWindow(" Login","wrong user name or password");
                         }
                     });

                 }
             };
             CommandsExecutor.getInstance().add(commandRequest);
         });
         Bp.setRight(Hb);
           return Bp;
       }
       //TODO #hazem
    //check for user input return user id if user exists
    //else return -1
       private TextField textFieldCreator(boolean visbility){
        TextField Tf=new TextField();
        Tf.setMaxWidth(200);
        Tf.setMinHeight(25);
        Tf.setVisible(visbility);
        return Tf;

    }
    private void layoutEditor(GridPane gridPane){
      BorderPane pane = navbarCreator();
      VBox vb= textFieldArea();
        GridPane.setMargin(vb,new Insets(40,10,10,10));
        GridPane.setConstraints(vb,1,1);
        GridPane.setConstraints(pane,0,0);
        GridPane.setColumnSpan(pane,3);

       gridPane.getChildren().addAll(vb,pane);
    }
    private Label createTxt(String text, int fontSize, boolean Visbility){
        Label lb=new Label();
        lb.setText(text);
        lb.setTextFill(Color.WHITE);
        lb.setFont(new Font("Yu Gothic UI",fontSize));
        lb.setMinWidth(60);
        lb.setVisible(Visbility);
        return lb;
    }

    private VBox textFieldArea(){
        VBox vb=new VBox();
        Label FRname=createTxt("First name:",20,true);
        TextField FRname_verify=textFieldCreator(true);
        Label LRname=createTxt("Last name:",20,true);
        TextField LRname_verify=textFieldCreator(true);
        HBox name_Hbox=createnamebox(FRname,FRname_verify,LRname,LRname_verify);
        Label nameverify = createTxt("name must contain just letters", 14, false);
        FRname_verify.setOnMouseClicked(e-> nameverify.setVisible(true));
        LRname_verify.setOnMouseClicked(e-> nameverify.setVisible(true));
        Label passwordlabel=createTxt("Password : ",20,true);
        PasswordField password_verify = new PasswordField();
        password_verify.setMaxWidth(250);
        password_verify.setMinHeight(25);
        HBox password_HBox=createHbox(passwordlabel,password_verify);
        Label passverify=createTxt("password from 1 to 9 or characters and contain from 8 to 18 characters",14,false);
        password_verify.setOnMouseClicked(e-> passverify.setVisible(true));
        Label email_label=createTxt("Email :",20,true);
        Label emailverfiy=createTxt("Email must be an valid email",14,false);
        TextField email_verify=textFieldCreator(true);
        email_verify.setMinWidth(250);
        email_verify.setOnMouseClicked(e-> emailverfiy.setVisible(true));
        HBox email_Hbox=createHbox(email_label,email_verify);
        Label date=createTxt("Dateof birth:",20,true);
        DatePicker dp=new DatePicker();
        HBox hdate=new HBox(10);
        hdate.getChildren().addAll(date,dp);
        Label dateverify=createTxt("U must be at least 1 year old and younger than  100 year",14,false);
        dp.setValue(LocalDate.now());
        Label Gender=createTxt("Gender :",20,true);
        ToggleGroup group = new ToggleGroup();
        RadioButton Male = new RadioButton("Male");
        Male.setToggleGroup(group);
        Male.setSelected(true);
        Male.setTextFill(Color.WHITE);
        RadioButton FeMale = new RadioButton("Female");
        FeMale.setToggleGroup(group);
        FeMale.setTextFill(Color.WHITE);
        Label request=createTxt("Request Adminship:",20,true);
        RadioButton RequestAdminShip = new RadioButton("Request");
        RequestAdminShip.setTextFill(Color.WHITE);
        HBox Admin_Hbox =new HBox(10);
        Admin_Hbox.setAlignment(Pos.CENTER_LEFT);
        Admin_Hbox.getChildren().addAll(request,RequestAdminShip);
        HBox Gender_Hbox=new HBox(20);
        Gender_Hbox.setAlignment(Pos.CENTER_LEFT);
        Gender_Hbox.getChildren().addAll(Gender,Male,FeMale);
        VBox.setMargin(Admin_Hbox,(new Insets(0,0,10,0)));
        Button B=new Button("Register");
        B.setOnAction(e->{
            int exceptions=0;
            if(!validator.valdiateName(FRname_verify.getText()) || !validator.valdiateName(LRname_verify.getText())) {
               nameverify.setVisible(true);
                nameverify.setTextFill(Color.RED);
                exceptions++;
            }else {
                nameverify.setVisible(false);
            }
            if(!validator.valdiatePass(password_verify.getText())){
                passverify.setVisible(true);
                passverify.setTextFill(Color.RED);
             exceptions++;
            }else{
                passverify.setVisible(false);
            }
            if(!validator.valdiateEmail(email_verify.getText())){
                emailverfiy.setVisible(true);
                emailverfiy.setTextFill(Color.RED);
                exceptions++;
            }else {
                emailverfiy.setVisible(false);
            }
            if(validator.datecheck(dp.getValue().toString())){
                dateverify.setVisible(false);
            }else{
                exceptions++;
                dateverify.setVisible(true);
                dateverify.setTextFill(Color.RED);
            }
            if(exceptions==0)
            {
                RegisterInfo send=new RegisterInfo();
                LoginInfo log =new LoginInfo();
                UserInfo user =new UserInfo();
                log.setEmail(email_verify.getText());
                log.setPassword(password_verify.getText());
                send.setLoginInfo(log);
                user.setFullName(FRname_verify.getText()+" "+LRname_verify.getText());
                user.setBirthDate(dp.getValue().toString());
                user.setAdminShip(RequestAdminShip.isSelected());
                if(Male.isSelected())
                    user.setGender(Male.getText());
                else
                    user.setGender(FeMale.getText());
                send.setUserInfo(user);
                //hna hb3t el command
                Command command = new Command();
                command.setKeyWord(RegisterInfo.KEYWORD);
                command.setSharableObject(send);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    public void analyze(Command commandFromServer) {

                        if(commandFromServer.getObjectStr().equals("true"))
                        Platform.runLater(()-> Utility.alertWindow(" Registration","Thanks for signing up, We will send you an E-mail informing you the admin choice!"));
                    else
                        {
                            Platform.runLater(()-> Utility.alertWindow(" Registration","please use a valid unique mail"));
                        }
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);

            }
        });



        vb.getChildren().addAll(name_Hbox,nameverify,password_HBox,passverify,email_Hbox,emailverfiy,hdate,dateverify,Gender_Hbox,Admin_Hbox,B);
        return vb;
    }

    private HBox createHbox(Label l, TextField t){
        HBox hb= new HBox();
        hb.getChildren().addAll(l,t);

        return hb;
    }
    private String Encrypt(String pass) { //useless and buggy
        StringBuilder sb = new StringBuilder();
        for (char c:pass.toCharArray()) {
            sb.append(++c);
        }

        return sb.reverse().toString();
    }
    private HBox createnamebox(Label firstname, TextField t, Label lastname, TextField t1){
        HBox hb= new HBox(10);
        hb.getChildren().addAll(firstname,t,lastname,t1);

        return hb;
    }

}
