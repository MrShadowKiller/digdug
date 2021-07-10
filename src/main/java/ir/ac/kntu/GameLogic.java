package ir.ac.kntu;

import ir.ac.kntu.characters.Direction;
import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Map;

import static ir.ac.kntu.characters.Direction.*;

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
        mapData = new MapData(gridPane);
        mapBuilder = new MapBuilder(gridPane, mapData);

    }

    public void start() {
        Player player = new Player(mapData,"mamad");
        mapData.setCurrentPlayer(player);
        mapBuilder.startBuild(player);
        scene.setOnKeyPressed(new KeyLogger(mapData));

        for (Enemy enemy : mapData.getEnemies()){
            enemy.startEnemyAI();
        }
        RandomItem randomItem = new RandomItem(mapData);
        Thread threadRandom = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(randomItem);
            }
        });
        threadRandom.start();

        Thread mainThread = new Thread(()->{
            Runnable runnable = () -> {
                if (!mapData.getCurrentPlayer().isAlive()) {
                    gameLost();
                }
            };
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(runnable);
            }
        });
        mainThread.start();
    }

    public void gameLost(){
        Pane pane = new Pane();
        Label label = new Label("WASTED");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria",13));
        BackgroundImage myBI= new BackgroundImage(new Image("assets/playerSignUp.png",600,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

        scene.setRoot(pane);

    }
}
