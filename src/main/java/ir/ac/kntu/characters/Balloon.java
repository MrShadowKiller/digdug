package ir.ac.kntu.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Balloon extends Enemy{
    public Balloon(double xSpeed, double ySpeed, int hp) {
        super(xSpeed, ySpeed, hp);

    }

    public void applyImages(){
        ArrayList<ImageView> imageViews = getImageViews();
        for (int i = 1;i<=8;i++){
            Image image = new Image("assets\\enemy\\p" + i + ".png");
            imageViews.add(new ImageView(image));
        }

        setCurrentImageView(imageViews.get(0));
    }
}