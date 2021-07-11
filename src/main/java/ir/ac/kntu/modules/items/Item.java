package ir.ac.kntu.modules.items;

import ir.ac.kntu.fxDatabase;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Item implements ItemEffect, Serializable {
    private int row;
    private int col;

    public Item(ImageView imageView) {
        fxDatabase.getInstance().setItemImageView(imageView);
    }

    public ImageView getImageView() {
        return fxDatabase.getInstance().getItemImageView();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
