package ir.ac.kntu.characters;

import javafx.scene.layout.GridPane;

public class DeadAnimation implements Runnable{
    GridPane gridPane;
    Enemy enemy;

    public DeadAnimation(GridPane gridPane, Enemy enemy) {
        this.gridPane = gridPane;
        this.enemy = enemy;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            enemy.getCurrentImageView().setImage(enemy.getImages().get(5));
            System.out.println("Ssssss");
        } catch (InterruptedException e) {

        }
    }
}
