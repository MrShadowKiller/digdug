package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

public abstract class Enemy implements Alive {
    private MapData mapData;
    private GridPane gridPane;

    private double xSpeed;

    private double ySpeed;

    private int hp;

    private boolean isShooting = false;

    private ArrayList<Image> images;

    private ImageView currentImageView;


    public Enemy(double xSpeed, double ySpeed, int hp,GridPane gridPane,MapData mapData) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
        currentImageView = new ImageView();
        images = new ArrayList<>();
        this.gridPane = gridPane;
        this.mapData = mapData;
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

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
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

    @Override
    public void move(int x, int y) {
        GridPane.setRowIndex(currentImageView, GridPane.getRowIndex(currentImageView) + y);
        GridPane.setColumnIndex(currentImageView, GridPane.getColumnIndex(currentImageView) + x);
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
