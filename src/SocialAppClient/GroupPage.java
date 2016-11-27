package SocialAppClient;

import SocialAppGeneral.Command;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.util.Optional;

/**
 * Created by kemo on 10/11/2016.
 */
public class GroupPage extends GridPane {


    public GroupPage() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        updateColor(this);
    }

    synchronized static void updateColor(Pane pane) {
        //TODO #prototype
        //set ur new command
        Command command = new Command();
        command.setKeyWord("changeColor");
//        command.setSharableObject("null");
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {
                pane.setBackground(new Background(new BackgroundFill(Color.web(commandFromServer.getObjectStr()), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
}
