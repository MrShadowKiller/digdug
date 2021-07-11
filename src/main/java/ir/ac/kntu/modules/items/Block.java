package ir.ac.kntu.modules.items;

import ir.ac.kntu.FXDatabase;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Block implements Serializable {
    private boolean isUsed = false;
    private final int row;
    private final int col;

    public Block(int row, int col, ImageView imageView) {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        this.row = row;
        this.col = col;
        FXDatabase.getInstance().getBlockViewImages().get(row).set(col, imageView);
    }

    public ImageView getImageView() {
        return FXDatabase.getInstance().getBlockViewImages().get(row).get(col);
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
