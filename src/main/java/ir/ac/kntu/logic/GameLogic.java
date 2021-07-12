package ir.ac.kntu.logic;

import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapBuilder;
import ir.ac.kntu.logic.Map.MapData;
import ir.ac.kntu.modules.characters.Enemy;
import ir.ac.kntu.modules.characters.Player;
import ir.ac.kntu.util.KeyLogger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameLogic {
    private final MapData mapData;

    private final MapBuilder mapBuilder;

    private final BorderPane borderPane;

    private final Scene scene;

    private final Stage stage;

    private StatusBar statusBar;

    public GameLogic(BorderPane borderPane, Scene scene, Stage stage, MapData mapData) {
        this.scene = scene;
        this.stage = stage;
        this.mapData = mapData;
        this.borderPane = borderPane;
        mapBuilder = new MapBuilder(FXDatabase.getInstance().getGridPane(), mapData);
        statusBar = new StatusBar(borderPane,mapData);
    }

    public void start() {
        if (mapData.getCurrentPlayer() == null) {
            Player player = new Player(mapData, "mamad");
            mapData.setCurrentPlayer(player);
        }
        scene.setOnKeyPressed(new KeyLogger(mapData, this));
        statusBar.creatStatusBar();
        statusBar.creatPlayerStatus();


        mapBuilder.startBuild(mapData.getCurrentPlayer());

        for (Enemy enemy : mapData.getEnemies()) {
            enemy.startEnemyAI();
        }
        mapBuilder.createRandomItem();


        checkGameStatus();
    }

    public void checkGameStatus() {
        Thread mainGameThread = new Thread(() -> {
            Runnable mainRunnable = () -> {
                if (!mapData.getCurrentPlayer().isAlive()) {
                    gameLost();
                }
                if (statusBar.getMinuteTimer() == 0 && statusBar.getSecondTimer() == 0) {
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
                Platform.runLater(mainRunnable);
            }
        });
        mainGameThread.start();
    }

    public void gameLost() {
//        savePlayers();
        mapData.getCurrentPlayer().setTotalGames(mapData.getCurrentPlayer().getTotalGames() + 1);
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
                Runnable timerRunner = () -> label.setText("in " + finalCount);
                Platform.runLater(timerRunner);
                count -= 1;
            }
        });
        timerThread.start();

        scene.setRoot(pane);
    }

    public void gameWon() {
//        savePlayers();
        mapData.getCurrentPlayer().setTotalGames(mapData.getCurrentPlayer().getTotalGames() + 1);
        mapData.setGameFinished(true);
        for (Enemy enemy : mapData.getEnemies()) {
            enemy.stopEnemyAI();
        }
        if (mapData.getCurrentPlayer().getPlayerHighScore() < mapData.getCurrentPlayer().getPlayerScore()) {
            mapData.getCurrentPlayer().setPlayerHighScore(mapData.getCurrentPlayer().getPlayerScore());
        }
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
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

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
                Runnable timerRunner = () -> label.setText("in " + finalCount);
                Platform.runLater(timerRunner);
                count -= 1;
            }
        });
        timerThread.start();
        scene.setRoot(pane);
    }


    public void saveMapData() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/assets/save/lastgame/mapdata.txt", false);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            try {
                objectOutputStream.writeObject(mapData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void savePlayers() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/assets/save/players.txt", false);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (Player player : mapData.getPlayers()) {
                try {
                    objectOutputStream.writeObject(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
