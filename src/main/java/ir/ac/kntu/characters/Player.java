package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import ir.ac.kntu.items.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Player implements Alive {
    private MapData mapData;
    private GridPane gridPane;

    private String name = "ALEX";
    private int totalGames = 0;
    private int xSpeed = 1;
    private int ySpeed = 1;
    private int hp = 3;
    private Weapon weapon;
    private Direction direction;
    private boolean isShooting = false;
    private int playerHighScore = 0;
    private ArrayList<Image> images;
    private ImageView currentImageView;

    public Player(GridPane gridPane, MapData mapData, String name) {
        this.name = name;
        weapon = new AirGun();
        images = new ArrayList<>();
        this.mapData = mapData;
        this.gridPane = gridPane;
        applyImages();
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

    public boolean checkCollide(int row, int col) {
        System.out.println(row + " sss " + col);
        if (!mapData.getBlocks().get(row).get(col).isUsed()) {
            if (mapData.getBlocks().get(row).get(col) instanceof Dirt) {
                collisionWithStone(row, col);
                return false;
            } else {
                System.out.println("STONE");
                return true;
            }
        } else {
            for (Enemy enemy : mapData.getEnemies()) {
                if (!enemy.isAlive()){
                    continue;
                }
                if (GridPane.getRowIndex(enemy.getCurrentImageView()) == row &&
                        GridPane.getColumnIndex(enemy.getCurrentImageView()) == col) {
                    System.out.println("ENEMY");
                    return true;
                }
            }
        }
        setCurrentImageView(1);
        return false;
    }

    public void collisionWithStone(int row, int col) {
        if (row > 1 && mapData.getBlocks().get(row - 1).get(col) instanceof Stone) {
            setCurrentImageView(8);
        } else {
            setCurrentImageView(4);
        }
    }

    @Override
    public void move(int x, int y) {
        int newRow = GridPane.getRowIndex(currentImageView) + y * ySpeed;
        int newCol = GridPane.getColumnIndex(currentImageView) + x * xSpeed;

        if (!checkCollide(newRow, newCol)) {

            mapData.getBlocks().get(newRow).get(newCol).setUsed(true);
            gridPane.getChildren().remove(mapData.getBlocks().get(newRow).get(newCol).getImageView());

            GridPane.setRowIndex(currentImageView, newRow);
            GridPane.setColumnIndex(currentImageView, newCol);
        }

    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void getHit(int damage) {

    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                currentImageView.setRotationAxis(Rotate.Z_AXIS);
                ;
                currentImageView.setRotate(-90);
                break;
            case DOWN:
                currentImageView.setRotationAxis(Rotate.Z_AXIS);
                ;
                currentImageView.setRotate(90);
                break;
            case RIGHT:
                currentImageView.setRotationAxis(Rotate.Y_AXIS);
                ;
                currentImageView.setRotate(360);
                break;
            case LEFT:
                currentImageView.setRotationAxis(Rotate.Y_AXIS);
                ;
                currentImageView.setRotate(180);
                break;
        }
    }

    public void attack() {
        System.out.println("Player is attacking!");
        int playerX = GridPane.getColumnIndex(currentImageView);
        int playerY = GridPane.getRowIndex(currentImageView);
        int col = playerX, row = playerY;
        System.out.println(row + " At first " + col);
        while (col - playerX <= weapon.getHitRange() ||
                row - playerY <= weapon.getHitRange()) {

            col += direction.getX();
            row += direction.getY();
            if (!mapData.getBlocks().get(row).get(col).isUsed()) {
                return;
            }
            System.out.println(row + " vvvv " + col);

            for (Enemy enemy : mapData.getEnemies()) {
                if (GridPane.getRowIndex(enemy.getCurrentImageView()) == row &&
                        GridPane.getColumnIndex(enemy.getCurrentImageView()) == col) {
                    enemy.getHit(weapon.getDamage());
                }
            }
        }
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
        currentImageView.setImage(images.get(num - 1));
    }
}
