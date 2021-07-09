package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

public abstract class Enemy implements Alive {
    private double xSpeed;

    private double ySpeed;

    private int hp;

    private boolean isShooting = false;

    private ArrayList<ImageView> imageViews;

    private ImageView currentImageView;


    public Enemy(double xSpeed, double ySpeed, int hp) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
        imageViews = new ArrayList<>();
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

    public ArrayList<ImageView> getImageViews() {
        return imageViews;
    }

    public void setImageViews(ArrayList<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    public ImageView getCurrentImageView() {
        return currentImageView;
    }

    public void setCurrentImageView(ImageView currentImageView) {
        this.currentImageView = currentImageView;
    }

    @Override
    public void move(int x, int y, MapData mapData,GridPane gridPane) {
        GridPane.setRowIndex(currentImageView, GridPane.getRowIndex(currentImageView) + y);
        GridPane.setColumnIndex(currentImageView, GridPane.getColumnIndex(currentImageView) + x);
    }

    @Override
    public boolean isAlive() {
        return hp >= 0;
    }
}
