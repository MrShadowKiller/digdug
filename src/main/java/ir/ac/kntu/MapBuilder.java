package ir.ac.kntu;

import ir.ac.kntu.characters.Balloon;
import ir.ac.kntu.characters.Dragon;
import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import ir.ac.kntu.items.Block;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Stone;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class MapBuilder {
    private GridPane gridPane;

    private MapData mapData;

    public MapBuilder(GridPane gridPane, MapData mapData) {
        this.gridPane = gridPane;
        this.mapData = mapData;
    }

    public void startBuild() {
        Player player = new Player("Mehdi");

        creatRandomEnemies(2);
        creatRandomDirts();
        creatRandEmptyRowsEnemies();
        creatRandEmptyColsEnemies();
        creatRandomStones();
        gridPane.getChildren().remove(mapData.getBlocks().get(0).get(5).getImageView());
        gridPane.add(player.getCurrentImageView(), 5,0);

    }

    public void creatRandomDirts() {
        ArrayList<ArrayList<Block>> dirts = mapData.getBlocks();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                dirts.get(i).add(new Dirt());
                gridPane.add(dirts.get(i).get(j).getImageView(), j, i);
            }
        }
        mapData.setBlocks(dirts);
    }

    public void creatRandomStones() {
        ArrayList<ArrayList<Block>> blocks = mapData.getBlocks();
        for (int i = 0; i < 4; i++) {
            int f = randomIntExcept(0,10);
            int m = randomIntExcept(-1,12);
            if (blocks.get(f).get(m).isUsed()) {
                i--;
                continue;
            }
            if (f + 1 <= 9 && blocks.get(f + 1).get(m).isUsed()) {
                i--;
                continue;
            }
            gridPane.getChildren().remove(blocks.get(f).get(m).getImageView());
            blocks.get(f).set(m, new Stone());
            gridPane.add(blocks.get(f).get(m).getImageView(), m, f);
        }
    }

    public void creatRandEmptyColsEnemies() {
        for (int i = 0; i < 2; i++) {
            int f = randomIntExcept(0,10);
            int m = randomIntExcept(-1,12);
            System.out.println(f + "  " + m);
            if (m <= 11 - 3) {
                Block dirt = mapData.getBlocks().get(f).get(m);
                dirt.setUsed(true);
                mapData.getBlocks().get(f).get(m + 1).setUsed(true);
                mapData.getBlocks().get(f).get(m + 2).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f).get(m + 1).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f).get(m + 2).getImageView());
            } else {
                Block dirt = mapData.getBlocks().get(f).get(m);
                dirt.setUsed(true);
                mapData.getBlocks().get(f).get(m - 1).setUsed(true);
                mapData.getBlocks().get(f).get(m - 2).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f).get(m - 1).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f).get(m - 2).getImageView());
            }
            if (i==0) {
                gridPane.add(mapData.getEnemies().get(i + 2).getCurrentImageView(), m, f);
            }
        }

    }

    public void creatRandEmptyRowsEnemies() {
        for (int i = 0; i < 2; i++) {
            int f = randomIntExcept(0,10);
            int m = randomIntExcept(-1,12);
            System.out.println(f + "  " + m);
            if (f <= 9 - 3) {
                Block dirt = mapData.getBlocks().get(f).get(m);
                dirt.setUsed(true);
                mapData.getBlocks().get(f + 1).get(m).setUsed(true);
                mapData.getBlocks().get(f + 2).get(m).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f + 1).get(m).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f + 2).get(m).getImageView());

            } else {
                Block dirt = mapData.getBlocks().get(f).get(m);
                dirt.setUsed(true);
                mapData.getBlocks().get(f - 1).get(m).setUsed(true);
                mapData.getBlocks().get(f - 2).get(m).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f - 1).get(m).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(f - 2).get(m).getImageView());
            }
            gridPane.add(mapData.getEnemies().get(i).getCurrentImageView(),m,f);
        }
    }

//    public void putPlayerInRandom(Player player) {
//        ArrayList<ArrayList<Block>> blocks = mapData.getBlocks();
//        for (int i = 0; i < blocks.size(); i++) {
//            for (int j = 0; j < blocks.get(i).size(); j++) {
//                if (blocks.get(i).get(j).isUsed()) {
//
//                    gridPane.getChildren().remove(blocks.get(i).get(j).getImageView());
//                    gridPane.add(player.getCurrentImageView(), j, i);
//                    return;
//                }
//            }
//        }
//    }

    public void creatRandomEnemies(int ballons) {
        ArrayList<Enemy> enemies = mapData.getEnemies();
        for (int i = 0 ;i<ballons;i++){
            mapData.getEnemies().add(new Balloon(1,2));
        }
        mapData.getEnemies().add(new Dragon(1,3));

    }

    public int randomIntExcept(int exception,int range){
        int x = exception;
        while (x == exception){
            x = (int) (Math.random() * 100) % range;
        }
        return x;
    }
}

