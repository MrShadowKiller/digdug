package ir.ac.kntu;

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
        ArrayList<Dirt> dirts = mapData.getDirts();
        for (int i = 0; i < 120; i++) {
            dirts.add(new Dirt());
        }
        int count = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                gridPane.add(dirts.get(count++).getImageView(), i, j);
            }
        }
        mapData.setDirts(dirts);
        creatRandomEmptyRows();
        creatRandomEmptyColumns();

    }

    public void creatRandomEmptyRows() {
        for (int i = 0; i < 2; i++) {
            int f = (int) (Math.random() * 100) % 10;
            int m = (int) (Math.random() * 100) % 12;
            System.out.println(f + "  " + m);
            if (m <= 9 - 3) {

                for (int j = 0; j < mapData.getDirts().size(); j++) {
                    Dirt dirt = mapData.getDirts().get(j);
                    if (GridPane.getRowIndex(dirt.getImageView()) == f && GridPane.getColumnIndex(dirt.getImageView()) == m) {

                        gridPane.getChildren().remove(dirt.getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j + 10).getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j + 20).getImageView());
                    }
                }
            } else {

                for (int j = 0; j < mapData.getDirts().size(); j++) {


                    Dirt dirt = mapData.getDirts().get(j);
                    if (GridPane.getRowIndex(dirt.getImageView()) == f && GridPane.getColumnIndex(dirt.getImageView()) == m) {
                        gridPane.getChildren().remove(dirt.getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j - 10).getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j - 20).getImageView());
                    }
                }
            }
        }

    }
    public void creatRandomEmptyColumns(){
        for (int i = 0; i < 2; i++) {
            int f = (int) (Math.random() * 100) % 10;
            int m = (int) (Math.random() * 100) % 12;
            System.out.println(f + "  " + m);
            if (f <= 9 - 3) {
                for (int j = 0; j < mapData.getDirts().size(); j++) {
                    Dirt dirt = mapData.getDirts().get(j);
                    if (GridPane.getRowIndex(dirt.getImageView()) == f && GridPane.getColumnIndex(dirt.getImageView()) == m) {

                        gridPane.getChildren().remove(dirt.getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j + 1).getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j + 2).getImageView());
                    }
                }
            } else {
                for (int j = 0; j < mapData.getDirts().size(); j++) {
                    Dirt dirt = mapData.getDirts().get(j);
                    if (GridPane.getRowIndex(dirt.getImageView()) == f && GridPane.getColumnIndex(dirt.getImageView()) == m) {
                        gridPane.getChildren().remove(dirt.getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j - 1).getImageView());
                        gridPane.getChildren().remove(mapData.getDirts().get(j - 2).getImageView());
                    }
                }
            }
        }
    }
}
