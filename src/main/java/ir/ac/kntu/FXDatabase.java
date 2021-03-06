package ir.ac.kntu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FXDatabase {
    private static final FXDatabase INSTANCE = new FXDatabase();

    private ArrayList<Image> ballonImages;

    private ArrayList<Image> dragonImages;

    private ImageView dragonAttack;

    private ArrayList<ImageView> enemyViewImages;

    private ArrayList<Image> playerImages;

    private ImageView playerCurrent;

    private ImageView playerAttack;

    private ArrayList<ArrayList<ImageView>> blockViewImages;

    private ImageView itemImageView;

    private GridPane gridPane;

    public FXDatabase() {
        resetData();
    }

    public void resetData() {
        ballonImages = new ArrayList<>();
        dragonImages = new ArrayList<>();
        enemyViewImages = new ArrayList<>();
        playerImages = new ArrayList<>();
        blockViewImages = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            blockViewImages.add(new ArrayList<>(12));
            for (int j = 0; j < 12; j++) {
                blockViewImages.get(i).add(new ImageView());
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public static FXDatabase getInstance() {
        return INSTANCE;
    }

    public ArrayList<Image> getBallonImages() {
        return ballonImages;
    }

    public ArrayList<Image> getDragonImages() {
        return dragonImages;
    }


    public ArrayList<ImageView> getEnemyViewImages() {
        return enemyViewImages;
    }

    public ArrayList<Image> getPlayerImages() {
        return playerImages;
    }

    public ImageView getPlayerCurrent() {
        return playerCurrent;
    }

    public void setPlayerCurrent(ImageView playerCurrent) {
        this.playerCurrent = playerCurrent;
    }

    public ArrayList<ArrayList<ImageView>> getBlockViewImages() {
        return blockViewImages;
    }

    public ImageView getItemImageView() {
        return itemImageView;
    }

    public void setItemImageView(ImageView itemImageView) {
        this.itemImageView = itemImageView;
    }

    public ImageView getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(ImageView playerAttack) {
        this.playerAttack = playerAttack;
    }

    public ImageView getDragonAttack() {
        return dragonAttack;
    }

    public void setDragonAttack(ImageView dragonAttack) {
        this.dragonAttack = dragonAttack;
    }
}
