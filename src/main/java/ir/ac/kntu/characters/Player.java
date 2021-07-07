package ir.ac.kntu.characters;

import ir.ac.kntu.items.AirGun;
import ir.ac.kntu.items.Weapon;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Player extends Pane implements Alive{
    private String name = "ALEX";

    private int totalGames = 0;

    private double xSpeed = 1;

    private double ySpeed = 1;

    private int hp = 3;

    private Weapon weapon;

    private boolean isShooting = false;

    private int playerHighScore = 0;

    private ArrayList<ImageView> imageViews;

    private ImageView currentImageView;

    public Player(String name) {
        this.name = name;
        weapon = new AirGun();
        applyImages();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public int getPlayerHighScore() {
        return playerHighScore;
    }

    public void setPlayerHighScore(int playerHighScore) {
        this.playerHighScore = playerHighScore;
    }

    public ImageView getCurrentImageView() {
        return currentImageView;
    }

    public void setCurrentImageView(ImageView currentImageView) {
        this.currentImageView = currentImageView;
    }

    public void applyImages(){
        for (int i = 1;i<=8;i++){
            Image image = new Image("assets\\player\\p" + i + ".png");
            imageViews.add(new ImageView(image));
        }

        currentImageView = imageViews.get(0);
        this.getChildren().add(currentImageView);
    }


    @Override
    public void move(int x, int y) {
        GridPane.setRowIndex(this,GridPane.getRowIndex(this) + y);
        GridPane.setColumnIndex(this,GridPane.getColumnIndex(this) + x);
    }

    @Override
    public boolean isAlive() {
        return hp >= 0;
    }
}
