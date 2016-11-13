package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class ProfilePage extends GridPane {
    public ProfilePage()
    {
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
