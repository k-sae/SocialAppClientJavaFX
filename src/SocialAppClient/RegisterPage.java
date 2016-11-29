package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.LoginInfo;
import SocialAppGeneral.RegisterInfo;
import SocialAppGeneral.UserInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by mosta on 30-Oct-16.
 */
public class RegisterPage extends StackPane {
    Pane parent;
 public RegisterPage(Pane parent){
     this.parent = parent;
     this.setBackground(new Background(
             new BackgroundImage(
             new Image("file:Resources/background.jpg", true),
             BackgroundRepeat.REPEAT,
             BackgroundRepeat.NO_REPEAT,
             BackgroundPosition.DEFAULT,
             BackgroundSize.DEFAULT)));
    GridPane gp=new GridPane();
     setRow(gp);
     setCol(gp);
     gp.setStyle("-fx-background-color:rgba(9,9,9,0.4)");
     this.getChildren().add(gp);
     layoutEditor(gp);
     //initialize the connection up here
     try {
         new MainServerConnection();
     } catch (Exception e) {
         e.printStackTrace();
     }

 }
    private void setCol(GridPane gridPane)
    {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(50);
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

     public BorderPane  navbarCreator(){
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
         loginButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 //TODO #hazem
                 //check for input
                 long userId = checkForUserInput();
                 if(userId != -1) {
                     parent.getChildren().add(new MainWindow(userId));
                     parent.getChildren().remove(this);
                 }
             }
         });
         Bp.setRight(Hb);
           return Bp;
       }
       //TODO #hazem
    //check for user input return user id if user exists
    //else return -1
    private long checkForUserInput()
    {
        return 1;
    }
    public TextField textFieldCreator(boolean visbility){
        TextField Tf=new TextField();
        Tf.setMaxWidth(200);
        Tf.setMinHeight(25);
        Tf.setVisible(visbility);
        return Tf;

    }
    public void layoutEditor(GridPane gridPane){
      BorderPane pane = navbarCreator();
      VBox vb= textFieldArea();
        gridPane.setMargin(vb,new Insets(40,10,10,10));
        GridPane.setConstraints(vb,1,1);
        GridPane.setConstraints(pane,0,0);
        gridPane.setColumnSpan(pane,3);

       gridPane.getChildren().addAll(vb,pane);
    }
    public Label createTxt(String text, int fontSize,boolean Visbility){
        Label lb=new Label();
        lb.setText(text);
        lb.setTextFill(Color.WHITE);
        lb.setFont(new Font("Yu Gothic UI",fontSize));
        lb.setMinWidth(60);
        lb.setVisible(Visbility);
        return lb;
    }

    public VBox textFieldArea(){
        VBox vb=new VBox();
        Label FRname=createTxt("First name:",20,true);
        TextField FRname_verify=textFieldCreator(true);
        Label LRname=createTxt("Last name:",20,true);
        TextField LRname_verify=textFieldCreator(true);
        HBox name_Hbox=createnamebox(FRname,FRname_verify,LRname,LRname_verify);
        Label nameverify = createTxt("name must contain just letters", 14, false);
        FRname_verify.setOnMouseClicked(e->{
            nameverify.setVisible(true);
        });
        LRname_verify.setOnMouseClicked(e->{
            nameverify.setVisible(true);
        });
        Label passwordlabel=createTxt("Password : ",20,true);
        PasswordField password_verify = new PasswordField();;
        password_verify.setMaxWidth(250);
        password_verify.setMinHeight(25);
        HBox password_HBox=createHbox(passwordlabel,password_verify);
        Label passverify=createTxt("password from 1 to 9 or characters and contain from 8 to 18 characters",14,false);
        password_verify.setOnMouseClicked(e->{
            passverify.setVisible(true);
        });
        Label email_label=createTxt("Email :",20,true);
        Label emailverfiy=createTxt("Email must be an valid email",14,false);
        TextField email_verify=textFieldCreator(true);
        email_verify.setMinWidth(250);
        email_verify.setOnMouseClicked(e->{
            emailverfiy.setVisible(true);
        });
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
        RadioButton FeMale = new RadioButton("FEMale");
        FeMale.setToggleGroup(group);
        FeMale.setTextFill(Color.WHITE);
        HBox Gender_Hbox=new HBox(20);

        Gender_Hbox.getChildren().addAll(Gender,Male,FeMale);
        vb.setMargin(Gender_Hbox,(new Insets(0,0,10,0)));
        Button B=new Button("Register");
        B.setOnAction(e->{
            int exceptions=0;
            boolean name= Pattern.matches("[a-zA-Z]{1,10}",FRname_verify.getText());
            boolean lastname= Pattern.matches("[a-zA-Z]{1,10}",LRname_verify.getText());
            boolean password=Pattern.matches("[a-zA-Z0-9]{8,18}",password_verify.getText());
            boolean email =Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",email_verify.getText());
            boolean datechecker=datecheck(dp.getValue().toString());
            if(!name) {
               nameverify.setVisible(true);
                nameverify.setTextFill(Color.RED);
                exceptions++;
            }else{
                nameverify.setVisible(false);
            }
            if(!lastname){
                nameverify.setVisible(true);
                nameverify.setTextFill(Color.RED);
                exceptions++;
            }else{
                nameverify.setVisible(false);
            }

            if(!password){
                passverify.setVisible(true);
                passverify.setTextFill(Color.RED);
             exceptions++;
            }else{
                passverify.setVisible(false);
            }
            if(!email){
                emailverfiy.setVisible(true);
                emailverfiy.setTextFill(Color.RED);
                exceptions++;
            }else {
                emailverfiy.setVisible(false);
            }
            if(datechecker){
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
                user.setFullName(FRname_verify.getText()+LRname_verify.getText());
                user.setBirthDate(dp.getValue().toString());
                user.setGender(group.getSelectedToggle().toString());
                send.setUserInfo(user);
                //hna hb3t el command
                Command command = new Command();
                command.setKeyWord("new register");
                command.setSharableObject(send);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        //TODO #prototype GSON
                        //read object from command
                        LoginInfo loginInfo = LoginInfo.fromJsonString(commandFromServer.getObjectStr());
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);

            }
        });



        vb.getChildren().addAll(name_Hbox,nameverify,password_HBox,passverify,email_Hbox,emailverfiy,hdate,dateverify,Gender_Hbox,B);
        return vb;
    }

    public HBox createHbox(Label l,TextField t){
        HBox hb= new HBox();
        hb.getChildren().addAll(l,t);

        return hb;
    }
    public HBox createnamebox(Label firstname,TextField t,Label lastname,TextField t1){
        HBox hb= new HBox(10);
        hb.getChildren().addAll(firstname,t,lastname,t1);

        return hb;
    }
    public boolean datecheck(String date){

    if(Integer.parseInt(date.substring(0,4))<2016&&Integer.parseInt(date.substring(0,4))>1916)
        return true;
    else
        return false;
    }
}
