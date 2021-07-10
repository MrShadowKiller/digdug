package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import ir.ac.kntu.items.*;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Player implements Alive {
    private MapData mapData;

    private String name = "ALEX";
    private int totalGames = 0;
    private int xSpeed = 1;
    private int ySpeed = 1;
    private int hp = 3;
    private Weapon weapon;
    private Direction direction;
    private boolean isShooting = false;
    private int playerScore = 0;
    private int playerHighScore = 0;
    private ArrayList<Image> images;
    private ImageView currentImageView;
    private ImageView attackImageView;

    public Player(MapData mapData, String name) {
        this.name = name;
        weapon = new AirGun();
        images = new ArrayList<>();
        this.mapData = mapData;
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
        attackImageView = new ImageView("assets\\player\\w.png");
        attackImageView.setFitWidth(40);
        attackImageView.setFitHeight(40);
    }

    public boolean checkCollide(int row, int col) {
        if (!mapData.getBlocks().get(row).get(col).isUsed()) {
            if (mapData.getBlocks().get(row).get(col) instanceof Dirt) {
                return false;
            } else {
                System.out.println("Collided STONE!");
                return true;
            }
        } else {
            for (Enemy enemy : mapData.getEnemies()) {
                if (!enemy.isAlive()) {
                    continue;
                }
                if (enemy.getRow() == row && enemy.getCol() == col) {
                    System.out.println("Collided ENEMY!");
                    return true;
                }
            }
        }
        setCurrentImageView(1);
        return false;
    }

    public void collisionWithStone(int row, int col) {

        if (row > 1 && mapData.getBlocks().get(row - 1).get(col) instanceof Stone) {
            if (!mapData.getBlocks().get(row - 1).get(col).isUsed()){
                ((Stone)mapData.getBlocks().get(row - 1).get(col)).fallStone();
                setCurrentImageView(8);
            } else {
                setCurrentImageView(4);
            }
        } else {
            setCurrentImageView(4);
        }
    }

    @Override
    public void move(int x, int y) {
        int newRow = getRow() + y * ySpeed;
        int newCol = getCol() + x * xSpeed;
        try {
            if (!checkCollide(newRow, newCol)) {
                System.out.println("Player Location : " + newRow + " " + newCol);
                mapData.getBlocks().get(newRow).get(newCol).setUsed(true);
                mapData.getGridPane().getChildren().remove(mapData.getBlocks().get(newRow).get(newCol).getImageView());
                GridPane.setRowIndex(currentImageView, newRow);
                GridPane.setColumnIndex(currentImageView, newCol);
                collisionWithStone(newRow, newCol);
                if (mapData.getItem() != null) {
                    if (mapData.getItem().getRow() == newRow && mapData.getItem().getCol() == newCol) {
                        System.out.println("ON ITEM");
                        mapData.getItem().doEffect(this);
                        mapData.getGridPane().getChildren().remove(mapData.getItem().getImageView());
                        mapData.setItem(null);
                        System.out.println("Health : " + hp);
                        System.out.println("Speed : " + xSpeed);
                        System.out.println("Range : " + weapon.getHitRange());
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out Of Map!");
        }


    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void getHit(int damage) {
        System.out.println("Player got hit!\nCurrent HP : " + hp);

        hp -= damage;
        if (!isAlive()){
            deadAnimation();
        }
    }

    @Override
    public void deadAnimation() {
        getCurrentImageView().setImage(images.get(5));
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            mapData.getGridPane().getChildren().remove(this.getCurrentImageView());
            getCurrentImageView().setImage(images.get(6));
            PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(c -> mapData.getGridPane().getChildren().remove(this.getCurrentImageView()));
            pause1.play();
        });
        pause.play();

    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                currentImageView.setRotationAxis(Rotate.Z_AXIS);
                attackImageView.setRotationAxis(Rotate.Z_AXIS);
                currentImageView.setRotate(-90);
                attackImageView.setRotate(-90);
                break;
            case DOWN:
                currentImageView.setRotationAxis(Rotate.Z_AXIS);
                attackImageView.setRotationAxis(Rotate.Z_AXIS);
                currentImageView.setRotate(90);
                attackImageView.setRotate(90);
                break;
            case RIGHT:
                currentImageView.setRotationAxis(Rotate.Y_AXIS);
                attackImageView.setRotationAxis(Rotate.Y_AXIS);
                currentImageView.setRotate(360);
                attackImageView.setRotate(360);
                break;
            case LEFT:
                currentImageView.setRotationAxis(Rotate.Y_AXIS);
                attackImageView.setRotationAxis(Rotate.Y_AXIS);
                currentImageView.setRotate(180);
                attackImageView.setRotate(180);
                break;
        }
    }

    public void attack() {
        System.out.println("Player is attacking!");
        int playerX = getCol();
        int playerY = getRow();
        int col = playerX, row = playerY;
        mapData.getGridPane().add(attackImageView, playerX + direction.getX(), playerY + direction.getY());
        attackAnimation();

        while (col - playerX < weapon.getHitRange() &&
                row - playerY < weapon.getHitRange()) {

            col += direction.getX();
            row += direction.getY();
            try {
                if (!mapData.getBlocks().get(row).get(col).isUsed()) {
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                return;
            }

            for (Enemy enemy : mapData.getEnemies()) {
                if (enemy.getRow() == row && enemy.getCol() == col) {
                    enemy.getHit(weapon.getDamage());
                    System.out.println("Enemy got hit at : " + row + " " + col);
                    if (!enemy.isAlive()){
                        playerScore += enemy.getPoint();
                    }
                }
            }
        }
    }

    public void attackAnimation(){
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.3));
        pauseTransition.setOnFinished(e -> mapData.getGridPane().getChildren().remove(attackImageView));
        pauseTransition.play();
    }

    public int getRow(){
        return GridPane.getRowIndex(currentImageView);
    }

    public int getCol(){
        return GridPane.getColumnIndex(currentImageView);
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

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
