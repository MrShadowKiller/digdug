package ir.ac.kntu;

import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.items.*;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class RandomItem implements Runnable {
    private MapData mapData;

    public RandomItem(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void run() {
        int randomInt = (int) (Math.random() * 100) % 3;
        Item item = new Heart();
        switch (randomInt) {
            case 0:
                item = new Heart();
                break;
            case 1:
                item = new Mushroom();
                break;
            case 2:
                item = new Gun();
                break;
        }
        ArrayList<Block> emptyBlocks = findEmptyBlocks();
        int randomInt2 = (int) (Math.random() * 100) % emptyBlocks.size();

        if (mapData.getItem() != null) {
            mapData.getGridPane().getChildren().remove(mapData.getItem().getImageView());
        }
        mapData.setItem(item);
        mapData.getGridPane().add(item.getImageView(), emptyBlocks.get(randomInt2).getCol(), emptyBlocks.get(randomInt2).getRow());

    }

    public ArrayList<Block> findEmptyBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        boolean status;

        for (int i = 0; i < mapData.getBlocks().size(); i++) {
            for (int j = 0; j < mapData.getBlocks().get(i).size(); j++) {
                if (mapData.getBlocks().get(i).get(j).isUsed()) {
                    status = true;
                    for (Enemy enemy : mapData.getEnemies()) {
                        if (enemy.getRow() == i && enemy.getCol() == j) {
                            status = false;
                            break;
                        }
                    }
                    if (status) {
                        if (mapData.getCurrentPlayer().getRow() != i &&
                                mapData.getCurrentPlayer().getCol() != j) {
                            blocks.add(mapData.getBlocks().get(i).get(j));
                        }
                    }
                }
            }
        }
        return blocks;
    }

}


