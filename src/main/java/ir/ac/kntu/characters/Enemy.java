package ir.ac.kntu.characters;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class Enemy extends Pane implements Alive{
    private double xSpeed;

    private double ySpeed;

    private int hp;

    private boolean isShooting = false;

    private ArrayList<ImageView> imageViews;

    private ImageView currentViewImage;


    public Enemy(double xSpeed, double ySpeed, int hp) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.hp = hp;
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
        return new ArrayList<>(imageViews);
    }

    public void setImageViews(ArrayList<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    public ImageView getCurrentViewImage() {
        return currentViewImage;
    }

    public void setCurrentViewImage(ImageView currentViewImage) {
        this.currentViewImage = currentViewImage;
    }

    @Override
    public void move(int x, int y){
        GridPane.setRowIndex(this,GridPane.getRowIndex(this) + y);
        GridPane.setColumnIndex(this,GridPane.getColumnIndex(this) + x);
    }

    @Override
    public boolean isAlive() {
        return hp >= 0;
    }
}
