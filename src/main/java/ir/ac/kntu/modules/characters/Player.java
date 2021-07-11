package ir.ac.kntu.modules.characters;

import ir.ac.kntu.FXDatabase;
import ir.ac.kntu.logic.Map.MapData;
import ir.ac.kntu.modules.characters.navigation.Direction;
import ir.ac.kntu.modules.items.*;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Alive, Serializable {
    private final MapData mapData;

    private String name;
    private int totalGames = 0;
    private int row;
    private int col;
    private int xSpeed = 1;
    private int ySpeed = 1;
    private int hp = 3;
    private Weapon weapon;
    private Direction direction;
    private boolean isShooting = false;
    private int playerScore = 0;
    private int playerHighScore = 0;

    public Player(MapData mapData, String name) {
        this.name = name;
        weapon = new AirGun();
        this.mapData = mapData;
        applyImages();
        row = 0;
        col = 5;
    }

    public void applyImages() {
        for (int i = 1; i <= 8; i++) {
            Image image = new Image("assets\\player\\p" + i + ".png");
            FXDatabase.getInstance().getPlayerImages().add(image);
        }
        ImageView playerImageView = new ImageView(getImages().get(0));
        playerImageView.setFitHeight(40);
        playerImageView.setFitWidth(40);
        FXDatabase.getInstance().setPlayerCurrent(playerImageView);
        ImageView playerImageViewAttack = new ImageView("assets\\player\\w.png");
        playerImageViewAttack.setFitWidth(40);
        playerImageViewAttack.setFitHeight(40);
        FXDatabase.getInstance().setPlayerAttack(playerImageViewAttack);
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
            if (!mapData.getBlocks().get(row - 1).get(col).isUsed()) {
                ((Stone) mapData.getBlocks().get(row - 1).get(col)).fallStone();
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
        int newRow = getRow() + y;
        int newCol = getCol() + x;
        try {
            if (!checkCollide(newRow, newCol)) {
                System.out.println("Player Location : " + newRow + " " + newCol);
                mapData.getBlocks().get(newRow).get(newCol).setUsed(true);
                FXDatabase.getInstance().getGridPane().getChildren().remove(mapData.getBlocks().get(newRow).get(newCol).getImageView());
                GridPane.setRowIndex(getCurrentImageView(), newRow);
                row = newRow;
                GridPane.setColumnIndex(getCurrentImageView(), newCol);
                col = newCol;
                collisionWithStone(newRow, newCol);
                if (mapData.getItem() != null) {
                    if (mapData.getItem().getRow() == newRow && mapData.getItem().getCol() == newCol) {
                        System.out.println("ON ITEM");
                        mapData.getItem().doEffect(this);
                        FXDatabase.getInstance().getGridPane().getChildren().remove(mapData.getItem().getImageView());
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
        if (!isAlive()) {
            deadAnimation();

        }
    }

    @Override
    public void deadAnimation() {
        getCurrentImageView().setImage(getImages().get(5));
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            FXDatabase.getInstance().getGridPane().getChildren().remove(this.getCurrentImageView());
            getCurrentImageView().setImage(getImages().get(6));
            PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(c -> FXDatabase.getInstance().getGridPane().getChildren().remove(this.getCurrentImageView()));
            pause1.play();
        });
        pause.play();

    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                getCurrentImageView().setRotationAxis(Rotate.Z_AXIS);
                getAttackImageView().setRotationAxis(Rotate.Z_AXIS);
                getCurrentImageView().setRotate(-90);
                getAttackImageView().setRotate(-90);
                break;
            case DOWN:
                getCurrentImageView().setRotationAxis(Rotate.Z_AXIS);
                getAttackImageView().setRotationAxis(Rotate.Z_AXIS);
                getCurrentImageView().setRotate(90);
                getAttackImageView().setRotate(90);
                break;
            case RIGHT:
                getCurrentImageView().setRotationAxis(Rotate.Y_AXIS);
                getAttackImageView().setRotationAxis(Rotate.Y_AXIS);
                getCurrentImageView().setRotate(360);
                getAttackImageView().setRotate(360);
                break;
            case LEFT:
                getCurrentImageView().setRotationAxis(Rotate.Y_AXIS);
                getAttackImageView().setRotationAxis(Rotate.Y_AXIS);
                getCurrentImageView().setRotate(180);
                getAttackImageView().setRotate(180);
                break;
            default:
                break;
        }
    }

    public void attack() {
        System.out.println("Player is attacking!");
        int playerX = getCol();
        int playerY = getRow();
        int col = playerX, row = playerY;
        FXDatabase.getInstance().getGridPane().add(FXDatabase.getInstance().getPlayerAttack(), playerX + direction.getX(), playerY + direction.getY());
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
                    if (!enemy.isAlive()) {
                        playerScore += enemy.getPoint();
                    }
                }
            }
        }
    }

    public void attackAnimation() {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.3));
        pauseTransition.setOnFinished(e -> FXDatabase.getInstance().getGridPane().getChildren().remove(FXDatabase.getInstance().getPlayerAttack()));
        pauseTransition.play();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
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
        return FXDatabase.getInstance().getPlayerCurrent();
    }

    public ImageView getAttackImageView() {
        return FXDatabase.getInstance().getPlayerAttack();
    }

    public void setCurrentImageView(int num) {
        FXDatabase.getInstance().getPlayerCurrent()
                .setImage(getImages().get(num - 1));
    }

    public ArrayList<Image> getImages() {
        return FXDatabase.getInstance().getPlayerImages();
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
