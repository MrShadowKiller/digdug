package ir.ac.kntu;

import ir.ac.kntu.items.Block;
import ir.ac.kntu.items.Dirt;
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

        creatRandomDirts();

    }

    public void creatRandomDirts() {
        ArrayList<ArrayList<Block>> dirts = mapData.getBlocks();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                dirts.get(i).add(new Dirt());
                gridPane.add(dirts.get(i).get(j).getImageView(), i, j);
            }
        }
        mapData.setBlocks(dirts);
        creatRandomEmptyRows();
        creatRandomEmptyColumns();

    }

    public void creatRandomEmptyRows() {
        for (int i = 0; i < 2; i++) {
            int f = (int) (Math.random() * 100) % 10;
            int m = (int) (Math.random() * 100) % 12;
            System.out.println(f + "  " + m);
            if (m <= 9 - 3) {
                Block dirt = mapData.getBlocks().get(m).get(f);
                dirt.setUsed(true);
                mapData.getBlocks().get(m + 1).get(f).setUsed(true);
                mapData.getBlocks().get(m + 2).get(f).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m + 1).get(f).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m + 2).get(f).getImageView());

            } else {
                Block dirt = mapData.getBlocks().get(m).get(f);
                dirt.setUsed(true);
                mapData.getBlocks().get(m - 1).get(f).setUsed(true);
                mapData.getBlocks().get(m - 2).get(f).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m - 1).get(f).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m - 2).get(f).getImageView());

            }
        }

    }

    public void creatRandomEmptyColumns() {
        for (int i = 0; i < 2; i++) {
            int f = (int) (Math.random() * 100) % 10;
            int m = (int) (Math.random() * 100) % 12;
            System.out.println(f + "  " + m);
            if (f <= 9 - 3) {

                Block dirt = mapData.getBlocks().get(m).get(f);
                dirt.setUsed(true);
                mapData.getBlocks().get(m).get(f + 1).setUsed(true);
                mapData.getBlocks().get(m).get(f + 2).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m).get(f + 1).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m).get(f + 2).getImageView());

            } else {
                Block dirt = mapData.getBlocks().get(m).get(f);
                dirt.setUsed(true);
                mapData.getBlocks().get(m).get(f - 1).setUsed(true);
                mapData.getBlocks().get(m).get(f -   2).setUsed(true);
                gridPane.getChildren().remove(dirt.getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m).get(f - 1).getImageView());
                gridPane.getChildren().remove(mapData.getBlocks().get(m).get(f - 2).getImageView());

            }
        }
    }
}

