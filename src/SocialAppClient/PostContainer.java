package SocialAppClient;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-26.
 */
public class PostContainer extends VBox {

    public PostContainer(){
        setAlignment(Pos.TOP_CENTER);
        setSpacing(30);
        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\New Stage 8\\11999835_891645674255266_5988653066941726818_o.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(300);
        img.setPreserveRatio(true);

        Image ime = new Image("file:C:\\Users\\bolla\\Pictures\\New Stage 8\\12032884_887378438015323_6040306901503937529_o.jpg");
        ImageView imge = new ImageView(ime);
        imge.setFitWidth(300);
        imge.setPreserveRatio(true);

        Image imee = new Image("file:C:\\Users\\bolla\\Pictures\\New Stage 8\\10623850_891518070934693_8290799018768311337_o.jpg");
        ImageView imgee = new ImageView(imee);
        imgee.setFitWidth(300);
        imgee.setPreserveRatio(true);


        getChildren().addAll(img,imge,imgee);
    }
}
