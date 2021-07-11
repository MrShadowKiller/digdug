package ir.ac.kntu.modules.items;

import ir.ac.kntu.fxDatabase;
import ir.ac.kntu.logic.MapData;
import ir.ac.kntu.modules.characters.Enemy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.Serializable;

public class Stone extends Block implements Serializable {
    private final MapData mapData;

    public Stone(int row,int col,MapData mapData) {
        super(row,col,new ImageView("assets/gravel.png"));
        this.mapData = mapData;
    }

    public void fallStone() {
        int stoneY = GridPane.getRowIndex(getImageView());
        int stoneX = GridPane.getColumnIndex(getImageView());

        for (int i = stoneY + 1; i < 10; i++) {
            if (!mapData.getBlocks().get(i).get(stoneX).isUsed()) {
                break;
            }
            if (mapData.getCurrentPlayer().getRow() == i && mapData.getCurrentPlayer().getCol() == stoneX) {
                mapData.getCurrentPlayer().getHit(1);
            }
            for (Enemy enemy : mapData.getEnemies()) {
                if (enemy.getRow() == i && enemy.getCol() == stoneX) {
                    enemy.getHit(1);
                }
            }
        }
        mapData.getBlocks().get(stoneY).get(stoneX).setUsed(true);
        fxDatabase.getInstance().getGridPane().getChildren().remove(getImageView());

    }
}
