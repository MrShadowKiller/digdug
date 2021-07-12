package ir.ac.kntu.modules.characters;

import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapData;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;


public class Dragon extends Enemy implements Serializable {

    public Dragon(double speed, int id, int hp, MapData mapData) {
        super(speed, speed, id, hp, 10, mapData);
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
        ImageView fireImageView = new ImageView("assets/enemy/dragon/flame.png");
        fireImageView.setFitWidth(40);
        fireImageView.setFitHeight(40);
        FXDatabase.getInstance().setDragonAttack(fireImageView);

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
        pause.setOnFinished(e -> FXDatabase.getInstance().getGridPane().getChildren().remove(this.getCurrentImageView()));
        pause.play();
    }

    @Override
    public void move(int col, int row) {
        if (collisionWithPlayer(col, row)) {
            FXDatabase.getInstance().getGridPane().add(getAttackImage(), getMapData().getCurrentPlayer().getCol(), getMapData().getCurrentPlayer().getRow());
            attackAnimation();
        } else {
            for (Enemy enemy : getMapData().getEnemies()) {
                if (enemy.isAlive()) {
                    if (enemy.getRow() == row && enemy.getCol() == col) {
                        return;
                    }
                }
            }
            setRow(row);
            setCol(col);
            GridPane.setRowIndex(getCurrentImageView(), row);
            GridPane.setColumnIndex(getCurrentImageView(), col);
        }
        checkEscape();
    }


    public void attackAnimation() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> FXDatabase.getInstance().getGridPane().getChildren().remove(getAttackImage()));
        pause.play();
    }

    public ArrayList<Image> getImages() {
        return FXDatabase.getInstance().getDragonImages();
    }

    public ImageView getAttackImage() {
        return FXDatabase.getInstance().getDragonAttack();
    }

}
