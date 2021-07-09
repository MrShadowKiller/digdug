package ir.ac.kntu;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import static ir.ac.kntu.characters.Direction.*;
import static ir.ac.kntu.characters.Direction.LEFT;

public class KeyLogger implements EventHandler<KeyEvent> {
    private MapData mapData;

    public KeyLogger(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                mapData.getCurrentPlayer().move(0, -1);
                mapData.getCurrentPlayer().changeDirection(UP);
                break;
            case S:
                mapData.getCurrentPlayer().move(0, 1);
                mapData.getCurrentPlayer().changeDirection(DOWN);
                break;
            case D:
                mapData.getCurrentPlayer().move(1, 0);
                mapData.getCurrentPlayer().changeDirection(RIGHT);
                break;
            case A:
                mapData.getCurrentPlayer().move(-1, 0);
                mapData.getCurrentPlayer().changeDirection(LEFT);
                break;
            case SHIFT:
                mapData.getCurrentPlayer().attack();
                break;
        }
    }
}
