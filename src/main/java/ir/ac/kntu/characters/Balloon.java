package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Balloon extends Enemy {
    public Balloon(double speed, int hp, GridPane gridPane, MapData mapData) {
        super(speed, speed, hp, gridPane, mapData);
        applyImages();
    }

    public void applyImages() {
        for (int i = 1; i <= 6; i++) {
            Image image = new Image("assets\\enemy\\balloon\\p" + i + ".png");
            getImages().add(image);
        }
        ImageView imageView = new ImageView(getImages().get(0));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        setCurrentImageView(imageView);
    }

    @Override
    public void getHit(int damage) {
        setHp(getHp() - damage);
        if (!isAlive()) {
            getCurrentImageView().setImage(getImages().get(5));
            System.out.println("salam");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            getGridPane().getChildren().remove(this.getCurrentImageView());
        } else {
            getCurrentImageView().setImage(getImages().get(4));
        }
    }
}
