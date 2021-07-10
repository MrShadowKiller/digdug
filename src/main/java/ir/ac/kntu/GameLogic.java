package ir.ac.kntu;

import ir.ac.kntu.characters.Direction;
import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

import static ir.ac.kntu.characters.Direction.*;

public class GameLogic {
    private final MapData mapData;

    private final MapBuilder mapBuilder;

    private final GridPane gridPane;

    private final Scene scene;

    private final Stage stage;

    public GameLogic(GridPane gridPane, Scene scene, Stage stage, MapData mapData) {
        this.gridPane = gridPane;
        this.scene = scene;
        this.stage = stage;
        this.mapData = mapData;
        mapBuilder = new MapBuilder(gridPane, mapData);

    }

    public void start() {
        if (mapData.getCurrentPlayer() == null){
            Player player = new Player(mapData, "mamad");
            mapData.setCurrentPlayer(player);

        }
        mapBuilder.startBuild(mapData.getCurrentPlayer());
        scene.setOnKeyPressed(new KeyLogger(mapData));

        for (Enemy enemy : mapData.getEnemies()) {
            enemy.startEnemyAI();
        }
        RandomItem randomItem = new RandomItem(mapData);
        Thread threadRandom = new Thread(() -> {
            while (!mapData.isGameFinished()) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(randomItem);
            }
        });
        threadRandom.start();

        Thread mainThread = new Thread(() -> {
            Runnable runnable = () -> {
                if (!mapData.getCurrentPlayer().isAlive()) {
                    gameLost();
                }
                if (!checkAliveEnemies()) {
                    gameWon();
                }
            };
            while (!mapData.isGameFinished()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(runnable);
            }
        });
        mainThread.start();
    }

    public void gameLost() {
        mapData.setGameFinished(true);
        for (Enemy enemy : mapData.getEnemies()) {
            enemy.stopEnemyAI();
        }

        Pane pane = new Pane();
        pane.setMinSize(600, 700);
        Label label = new Label("Going Back to Menu");
        label.setMinSize(600, 700);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria", 35));
        BackgroundImage myBI = new BackgroundImage(new Image("assets/youDied.png", 600, 700, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        label.setAlignment(Pos.BOTTOM_CENTER);
        pane.getChildren().add(label);

        pane.setBackground(new Background(myBI));
        Thread timerThread = new Thread(() -> {
            int count = 5;
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalCount = count;
                Runnable timerRunner = () -> {
                    label.setText("in " + finalCount);

                };
                Platform.runLater(timerRunner);
                count -= 1;
            }
        });
        timerThread.start();

        scene.setRoot(pane);
    }

    public void gameWon() {
        mapData.setGameFinished(true);
        for (Enemy enemy : mapData.getEnemies()) {
            enemy.stopEnemyAI();
        }
        if (mapData.getCurrentPlayer().getPlayerHighScore() < mapData.getCurrentPlayer().getPlayerScore()) {
            mapData.getCurrentPlayer().setPlayerHighScore(mapData.getCurrentPlayer().getPlayerScore());
        }

//        saveMapData();

        Pane pane = new Pane();
        pane.setMinSize(600, 700);

        Label score = new Label("Your Score : " + mapData.getCurrentPlayer().getPlayerScore());
        score.setMinSize(600, 700);
        score.setTextFill(Color.WHITE);
        score.setFont(new Font("Cambria", 35));
        score.setAlignment(Pos.TOP_LEFT);

        Label label = new Label("Going Back to Menu");
        label.setMinSize(600, 700);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria", 35));
        label.setAlignment(Pos.BOTTOM_CENTER);
        pane.getChildren().addAll(score, label);

        BackgroundImage myBI = new BackgroundImage(new Image("assets/win.png", 600, 700, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));
        Thread timerThread = new Thread(() -> {
            int count = 5;
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalCount = count;
                Runnable timerRunner = () -> {
                    label.setText("in " + finalCount);

                };
                Platform.runLater(timerRunner);
                count -= 1;
            }
        });
        timerThread.start();

        scene.setRoot(pane);
    }

    public void saveMapData(){
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\MrShadowKiller\\Desktop\\University\\Advanced Programming\\projects\\p4-digdug\\src\\main\\resources\\save\\lastgame" +
                "\\mapdata.txt",false);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            try {
                objectOutputStream.writeObject(mapData);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkAliveEnemies() {
        for (Enemy enemy : mapData.getEnemies()) {
            if (enemy.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
