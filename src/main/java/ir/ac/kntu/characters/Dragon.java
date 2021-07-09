package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Dragon extends Enemy{
    public Dragon(double speed, int hp, GridPane gridPane, MapData mapData) {
        super(speed, speed, hp,gridPane,mapData);
        applyImages();
    }

    public void applyImages(){
        for (int i = 1;i<=4;i++){
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
        setHp(getHp() - damage);
        if (!isAlive()) {
            System.out.println("Enemy is dead !");
            deadAnimation();
        } else {
            getCurrentImageView().setImage(getImages().get(2));
        }
    }

    @Override
    public void deadAnimation() {
        getCurrentImageView().setImage(getImages().get(3));
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> getGridPane().getChildren().remove(this.getCurrentImageView()));
        pause.play();
    }

}
