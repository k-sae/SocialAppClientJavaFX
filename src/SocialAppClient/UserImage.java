package SocialAppClient;

import javafx.scene.shape.Circle;

/**
 * Created by billy on 2016-12-11.
 */
public class UserImage{
    public UserImage(){

    }
    public static ImageViewer getCircularImage(String imgid){

        ImageViewer img = new ImageViewer(imgid);
        img.setFitWidth(40);
        img.setFitHeight(40);
        img.setSmooth(true);
        img.setCache(true);
        img.setClip(new Circle(img.getFitWidth()/2,img.getFitHeight()/2,img.getFitWidth()/2));

        return img;
    }
}
