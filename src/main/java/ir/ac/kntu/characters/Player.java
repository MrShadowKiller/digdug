package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import ir.ac.kntu.items.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Player implements Alive {
    private String name = "ALEX";

    private int totalGames = 0;

    private int xSpeed = 1;

    private int ySpeed = 1;

    private int hp = 3;

    private Weapon weapon;

    private boolean isShooting = false;

    private int playerHighScore = 0;

    private ArrayList<Image> images;

    private ImageView currentImageView;

    public Player(String name) {
        this.name = name;
        weapon = new AirGun();
        images = new ArrayList<>();
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

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
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

    public void setCurrentImageView(int num) {
        currentImageView.setImage(images.get(num-1));
    }

    public void applyImages() {
        for (int i = 1; i <= 8; i++) {
            Image image = new Image("assets\\player\\p" + i + ".png");

            images.add(image);
        }
        currentImageView = new ImageView(images.get(0));
        currentImageView.setFitHeight(40);
        currentImageView.setFitWidth(40);
    }

    public boolean checkCollide(int row, int col, MapData mapData) {
        System.out.println(row + " sss " + col);
        if (!mapData.getBlocks().get(row).get(col).isUsed()) {
            if (mapData.getBlocks().get(row).get(col) instanceof Dirt) {
                collisionWithStone(row,col, mapData.getBlocks());
                return false;
            } else {

                return true;
            }
        } else {
            for (Enemy enemy : mapData.getEnemies()){
                if (GridPane.getRowIndex(enemy.getCurrentImageView()) == row &&
                        GridPane.getColumnIndex(enemy.getCurrentImageView()) == col){
                    return true;
                }
            }
        }
        return false;
    }

    public void collisionWithStone(int row, int col, ArrayList<ArrayList<Block>>blocks){
        if (row > 1 && blocks.get(row-1).get(col) instanceof Stone){
            setCurrentImageView(8);
        }

    }

    @Override
    public void move(int x, int y, MapData mapData,GridPane gridPane) {
        int newRow = GridPane.getRowIndex(currentImageView) + y * ySpeed;
        int newCol = GridPane.getColumnIndex(currentImageView) + x * xSpeed;

        if (!checkCollide(newRow, newCol, mapData)) {
            mapData.getBlocks().get(newRow).get(newCol).setUsed(true);
            gridPane.getChildren().remove(mapData.getBlocks().get(newRow).get(newCol).getImageView());

            GridPane.setRowIndex(currentImageView, newRow);
            GridPane.setColumnIndex(currentImageView, newCol);
        }

    }

    @Override
    public boolean isAlive() {
        return hp >= 0;
    }
}
