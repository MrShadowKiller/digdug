package ir.ac.kntu.logic;

import ir.ac.kntu.logic.Map.MapData;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatusBar {
    private final BorderPane borderPane;

    private final MapData mapData;

    private int minuteTimer = 0;

    private int secondTimer = 10;

    public StatusBar(BorderPane borderPane, MapData mapData) {
        this.borderPane = borderPane;
        this.mapData = mapData;
    }

    public void creatStatusBar() {
        HBox gameStatus = new HBox();
        gameStatus.setPadding(new Insets(15, 15, 15, 15));
        gameStatus.setMaxWidth(600);
        gameStatus.setMaxHeight(100);
        gameStatus.setMinHeight(100);
        gameStatus.setSpacing(350);
        Label timer = new Label("0:0");
        timer.setTextFill(Color.WHITE);
        timer.setFont(new Font("Cambria", 20));

        Label score = new Label();
        score.setTextFill(Color.WHITE);
        score.setFont(new Font("Cambria", 20));

        gameStatus.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Thread statusBarThread = new Thread(() -> {
            while (minuteTimer != 0 || secondTimer != 0) {
                if (mapData.isGameFinished()) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runnable runnable = () -> {
                    countDownTime(timer);
                    setLabelScore(score);
                };
                Platform.runLater(runnable);
            }
        });
        gameStatus.getChildren().addAll(timer, score);
        borderPane.setTop(gameStatus);
        statusBarThread.start();
    }

    public void creatPlayerStatus() {
        HBox playerStatus = new HBox();
        playerStatus.setPadding(new Insets(15, 15, 15, 15));
        playerStatus.setMaxWidth(600);
        playerStatus.setMaxHeight(100);
        playerStatus.setMinHeight(600);
        playerStatus.setSpacing(350);

        Label health = new Label();
        health.setTextFill(Color.WHITE);
        health.setFont(new Font("Cambria", 20));

        Label speed = new Label();
        speed.setTextFill(Color.WHITE);
        speed.setFont(new Font("Cambria", 20));

        playerStatus.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Thread playerStatusThread = new Thread(() -> {
            while (!mapData.isGameFinished()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runnable runnable = () -> setLabelHealth(health, speed);
                Platform.runLater(runnable);
            }
        });
        playerStatus.getChildren().addAll(health, speed);
        borderPane.setBottom(playerStatus);
        playerStatusThread.start();
    }

    public void countDownTime(Label timer) {
        secondTimer--;
        if (secondTimer == -1) {
            secondTimer = 59;
            minuteTimer--;
        }
        if (secondTimer >= 10) {
            timer.setText("Time : " + minuteTimer + ":" + secondTimer);
        } else {
            timer.setText("Time : " + minuteTimer + ":0" + secondTimer);
        }
    }

    public void setLabelScore(Label score) {
        score.setText("Score : " + mapData.getCurrentPlayer().getPlayerScore());
    }

    public void setLabelHealth(Label health, Label speed) {
        health.setText("Health : " + mapData.getCurrentPlayer().getHp());
        speed.setText("Speed : " + mapData.getCurrentPlayer().getxSpeed());
    }

    public int getMinuteTimer() {
        return minuteTimer;
    }

    public int getSecondTimer() {
        return secondTimer;
    }
}
