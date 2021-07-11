package ir.ac.kntu.modules.characters;

import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapData;
import ir.ac.kntu.modules.characters.navigation.EnemyAI;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.Serializable;

public abstract class Enemy implements Alive, Serializable {
    private final MapData mapData;

    private int row;

    private int col;

    private final int id;

    private double xSpeed;

    private double ySpeed;

    private int hp;

    private final int point;

    private boolean isActive = true;

    private final EnemyAI enemyAI;

    private Thread enemyThread;


    public Enemy(double xSpeed, double ySpeed, int id, int hp, int point, MapData mapData) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
        this.id = id;
        this.point = point;
        this.mapData = mapData;
        enemyAI = new EnemyAI(this, mapData);

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
        this.row = row;
        this.col = col;
        GridPane.setRowIndex(getCurrentImageView(), row);
        GridPane.setColumnIndex(getCurrentImageView(), col);
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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

    public ImageView getCurrentImageView() {
        return FXDatabase.getInstance().getEnemyViewImages().get(id);
    }

    public void setCurrentImageView(ImageView currentImageView) {
        FXDatabase.getInstance().getEnemyViewImages().add(currentImageView);
    }

    public MapData getMapData() {
        return mapData;
    }


    public int getPoint() {
        return point;
    }
}
