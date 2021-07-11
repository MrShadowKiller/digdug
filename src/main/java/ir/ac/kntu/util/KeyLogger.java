package ir.ac.kntu.util;

import ir.ac.kntu.logic.GameLogic;
import ir.ac.kntu.logic.Map.MapData;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static ir.ac.kntu.modules.characters.navigation.Direction.*;
import static ir.ac.kntu.modules.characters.navigation.Direction.LEFT;

public class KeyLogger implements EventHandler<KeyEvent> {
    private final MapData mapData;

    private Thread delayKey;

    private final Runnable runDelay;

    private int speed = 250;

    private GameLogic gameLogic;

    public KeyLogger(MapData mapData, GameLogic gameLogic) {
        this.mapData = mapData;
        this.gameLogic = gameLogic;
        runDelay = () -> {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

    }

    @Override
    public void handle(KeyEvent keyEvent) {
        speed /= mapData.getCurrentPlayer().getxSpeed();

        if (mapData.isGameFinished()) {
            return;
        }
        if (delayKey != null && delayKey.isAlive()) {
            return;
        }

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
            case C:
//                if (!mapData.isGameFinished()) {
//                    gameLogic.saveMapData();
//                }
                break;
            default:
                return;
        }
        delayKey = new Thread(runDelay);
        delayKey.start();
    }
}
