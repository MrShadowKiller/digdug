package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Enemy implements Alive, Serializable {
    private final MapData mapData;
    private final GridPane gridPane;

    private double xSpeed;

    private double ySpeed;

    private int hp;

    private final int point;

    private boolean isActive = true;

    private ArrayList<Image> images;

    private ImageView currentImageView;

    private final EnemyAI enemyAI;

    private Thread enemyThread;


    public Enemy(double xSpeed, double ySpeed, int hp, int point, GridPane gridPane, MapData mapData) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
        this.point = point;
        currentImageView = new ImageView();
        images = new ArrayList<>();
        this.gridPane = gridPane;
        this.mapData = mapData;
        enemyAI = new EnemyAI(this, gridPane, mapData);

    }

    public void startEnemyAI() {
        enemyThread = new Thread(() -> {
            while (isActive) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(enemyAI);
            }
        });
        enemyThread.start();
    }

    public void stopEnemyAI() {
        isActive = false;
    }

    public boolean collisionWithPlayer(int col, int row) {
        if (mapData.getCurrentPlayer().getCol() == col && mapData.getCurrentPlayer().getRow() == row) {
            mapData.getCurrentPlayer().getHit(1);
            return true;
        }
        return false;
    }

    @Override
    public void move(int col, int row) {
        collisionWithPlayer(col, row);
        GridPane.setRowIndex(currentImageView, row);
        GridPane.setColumnIndex(currentImageView, col);
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getRow() {
        return GridPane.getRowIndex(currentImageView);
    }

    public int getCol() {
        return GridPane.getColumnIndex(currentImageView);
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ImageView getCurrentImageView() {
        return currentImageView;
    }

    public void setCurrentImageView(ImageView currentImageView) {
        this.currentImageView = currentImageView;
    }

    public MapData getMapData() {
        return mapData;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public int getPoint() {
        return point;
    }
}
