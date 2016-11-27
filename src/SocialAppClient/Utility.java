package SocialAppClient;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

import java.util.Optional;

/**
 * Created by khaled hesham on 11/24/2016.
 */
public class Utility {
    public static Optional<String> createWindow(String  Nameenter,String  titel){/*
    this function return optional.empty if user press cancel otherwise return input
    pre !check.equals(Optional.empty())*/
        TextInputDialog Create = new TextInputDialog();
        Create.setHeaderText(null);
        Create.setTitle(titel);
        Create.setContentText(Nameenter+" : ");
        Create.initStyle(StageStyle.UTILITY);
        Optional<String> result = Create.showAndWait();
        return result;
    }
    public static void errorWindow(String error){//show error window
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(null);
        alert.setHeaderText(error);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
}


