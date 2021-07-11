package ir.ac.kntu.logic.Map;

import ir.ac.kntu.fxDatabase;
import ir.ac.kntu.modules.characters.Enemy;
import ir.ac.kntu.modules.items.*;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class RandomItem implements Runnable {
    private final MapData mapData;

    public RandomItem(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public void run() {
        int randomInt = (int) (Math.random() * 100) % 3;

        ImageView imageView = null;

        try {
            imageView = mapData.getItem().getImageView();
        } catch (NullPointerException e){
            System.out.println("No recent Item.");
        }
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
            default:
                break;
        }
        ArrayList<Block> emptyBlocks = findEmptyBlocks();
        int randomInt2 = (int) (Math.random() * 100) % emptyBlocks.size();

        if (imageView != null) {
            fxDatabase.getInstance().getGridPane().getChildren().remove(imageView);
        }
        mapData.setItem(item);
        fxDatabase.getInstance().getGridPane().add(item.getImageView(), emptyBlocks.get(randomInt2).getCol(), emptyBlocks.get(randomInt2).getRow());
        item.setRow(emptyBlocks.get(randomInt2).getRow());
        item.setCol(emptyBlocks.get(randomInt2).getCol());
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


