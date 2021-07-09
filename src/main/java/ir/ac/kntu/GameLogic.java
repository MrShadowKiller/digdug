package ir.ac.kntu;

import ir.ac.kntu.characters.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameLogic {
    private MapData mapData;

    private MapBuilder mapBuilder;

    private GridPane gridPane;

    private Scene scene;

    private Stage stage;

    public GameLogic(GridPane gridPane, Scene scene, Stage stage) {
        this.gridPane = gridPane;
        this.scene = scene;
        this.stage = stage;
        mapData = new MapData();
        mapBuilder = new MapBuilder(gridPane, mapData);
    }

    public void start() {
        Player player = new Player("mamad");
        mapBuilder.startBuild(player);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W :
                    player.move(0,-1,mapData,gridPane);
                    break;
                case S :
                    player.move(0,1,mapData,gridPane);
                    break;
                case D :
                    player.move(1,0,mapData,gridPane);
                    break;
                case A :
                    player.move(-1,0,mapData,gridPane);
                    break;

            }
        });
    }
}
