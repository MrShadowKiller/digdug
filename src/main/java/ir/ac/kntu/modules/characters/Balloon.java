package ir.ac.kntu.modules.characters;

import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapData;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class Balloon extends Enemy implements Serializable {
    public Balloon(double speed, int id, int hp, MapData mapData) {
        super(speed, speed, id, hp, 5, mapData);
        applyImages();
    }

    public void applyImages() {
        if (FXDatabase.getInstance().getBallonImages().isEmpty()) {
            for (int i = 1; i <= 6; i++) {
                Image image = new Image("assets\\enemy\\balloon\\p" + i + ".png");
                getImages().add(image);
            }
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
            System.out.println("Enemy is dead !");
            deadAnimation();
        } else {
            getCurrentImageView().setImage(getImages().get(4));
        }
    }

    public ArrayList<Image> getImages() {
        return FXDatabase.getInstance().getBallonImages();
    }

    public void deadAnimation() {
        getCurrentImageView().setImage(getImages().get(5));
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> FXDatabase.getInstance().getGridPane().getChildren().remove(this.getCurrentImageView()));
        pause.play();
    }
}
