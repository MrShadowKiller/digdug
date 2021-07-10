package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.scene.layout.GridPane;

public interface Alive {
    void move(int x, int y) throws InterruptedException;

    boolean isAlive();

    void getHit(int damage);

    void deadAnimation();
}
