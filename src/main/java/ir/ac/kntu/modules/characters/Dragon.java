package ir.ac.kntu.modules.characters;

import ir.ac.kntu.logic.MapData;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.Serializable;


public class Dragon extends Enemy implements Serializable {
    private ImageView fireAttack;

    public Dragon(double speed, int hp, GridPane gridPane, MapData mapData) {
        super(speed, speed, hp, 10, gridPane, mapData);
        applyImages();
    }

    public void applyImages() {
        for (int i = 1; i <= 4; i++) {
            Image image = new Image("assets\\enemy\\dragon\\p" + i + ".jpg");
            getImages().add(image);
        }
        ImageView imageView = new ImageView(getImages().get(0));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        fireAttack = new ImageView("assets/enemy/dragon/flame.png");
        fireAttack.setFitWidth(40);
        fireAttack.setFitHeight(40);

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

    @Override
    public void move(int col, int row) {
        try {
            if (collisionWithPlayer(col, row)) {
                for (Enemy enemy : getMapData().getEnemies()) {
                    if (enemy != this) {
                        if (enemy.getRow() == row && enemy.getCol() == col) {
                            return;
                        }
                    }
                }
                getGridPane().add(fireAttack, getMapData().getCurrentPlayer().getCol(), getMapData().getCurrentPlayer().getRow());
                attackAnimation();

            } else {
                GridPane.setRowIndex(getCurrentImageView(), row);
                GridPane.setColumnIndex(getCurrentImageView(), col);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("DRAGON MAY NOT WORK WELL");
        }
    }


    public void attackAnimation() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> getGridPane().getChildren().remove(fireAttack));
        pause.play();
    }

}
