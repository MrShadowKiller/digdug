package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Dragon extends Enemy{
    public Dragon(double speed, int hp, GridPane gridPane, MapData mapData) {
        super(speed, speed, hp,gridPane,mapData);
        applyImages();
    }

    public void applyImages(){
        for (int i = 1;i<=2;i++){
            Image image = new Image("assets\\enemy\\dragon\\p" + i + ".jpg");
            getImages().add(image);
        }
        ImageView imageView = new ImageView(getImages().get(0));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        setCurrentImageView(imageView);
    }

    @Override
    public void getHit(int damage) {

    }
}
