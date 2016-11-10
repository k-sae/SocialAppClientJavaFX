package SocialAppClient;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import sun.plugin.dom.core.Text;
import sun.security.util.Password;

import javax.swing.*;
import javax.xml.validation.Validator;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Created by mosta on 30-Oct-16.
 */
public class RegisterPage extends StackPane {
 public RegisterPage(){
     this.setBackground(new Background(new BackgroundImage(new Image("file:Resources/background.jpg", true),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT)));
    GridPane gp=new GridPane();
     setRow(gp);
     setCol(gp);
     gp.setStyle("-fx-background-color:rgba(9,9,9,0.4)");
     this.getChildren().add(gp);
     layoutEditor(gp);
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

        // TODO # karim

         BorderPane  Bp=new BorderPane ();
         HBox Hb=new HBox(5);
         Label email_label=createTxt("Email:",20,true);
         TextField email=textFieldCreator(true);
         Label passwordlabel=createTxt("Password:",20,true);
         PasswordField pass=new PasswordField();
         pass.setMaxWidth(250);
         pass.setMinHeight(25);
         Button B=new Button("login");
         //Hb.setBackground(new Background(new BackgroundFill(Color.AQUA,null,null)));
         Hb.getChildren().addAll(email_label,email,passwordlabel,pass,B);
         Bp.setRight(Hb);
           return Bp;
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
            boolean name= Pattern.matches("[a-zA-Z]{1,10}",FRname_verify.getText());
            boolean lastname= Pattern.matches("[a-zA-Z]{1,10}",LRname_verify.getText());
            boolean password=Pattern.matches("[a-zA-Z1-9]{8,18}",password_verify.getText());
            boolean email =Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",email_verify.getText());
            boolean datechecker=datecheck(dp.getValue().toString());
            if(name==false) {
               nameverify.setVisible(true);
                nameverify.setTextFill(Color.RED);
            }else{
                nameverify.setVisible(false);
            }
            if(lastname==false){
                nameverify.setVisible(true);
                nameverify.setTextFill(Color.RED);
            }else{
                nameverify.setVisible(false);
            }

            if(password==false){
                passverify.setVisible(true);
                passverify.setTextFill(Color.RED);

            }else{
                passverify.setVisible(false);
            }
            if(email==false){
                emailverfiy.setVisible(true);
                emailverfiy.setTextFill(Color.RED);
            }else {
                emailverfiy.setVisible(false);
            }
            if(datechecker==true){
                dateverify.setVisible(false);
            }else{
                dateverify.setVisible(true);
                dateverify.setTextFill(Color.RED);
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
