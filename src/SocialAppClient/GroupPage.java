package SocialAppClient;

import SocialAppGeneral.Command;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class GroupPage extends GridPane {
   public GroupPage()
    {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        updateColor(this);
    }
    synchronized static void updateColor(Pane pane)
    {
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
//        commandRequest.run();
       CommandsExecutor.getInstance().add(commandRequest);
    }
}
