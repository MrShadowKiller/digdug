package ir.ac.kntu.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Dragon extends Enemy{
    public Dragon(double speed, int hp) {
        super(speed, speed, hp);
        applyImages();
    }

    public void applyImages(){
        ArrayList<ImageView> imageViews = getImageViews();
        for (int i = 1;i<=2;i++){
            ImageView imageView = new ImageView("assets\\enemy\\dragon\\p" + i + ".jpg");
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageViews.add(imageView);
        }
        setCurrentImageView(imageViews.get(0));
    }

}
