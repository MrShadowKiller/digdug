package ir.ac.kntu.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Balloon extends Enemy{
    public Balloon(double speed, int hp) {
        super(speed, speed, hp);
        applyImages();
    }

    public void applyImages(){
        ArrayList<ImageView> imageViews = getImageViews();
        for (int i = 1;i<=6;i++){
            ImageView imageView = new ImageView("assets\\enemy\\balloon\\p" + i + ".png");
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageViews.add(imageView);
        }

        setCurrentImageView(imageViews.get(0));
    }
}
