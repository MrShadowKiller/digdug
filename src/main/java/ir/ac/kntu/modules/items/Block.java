package ir.ac.kntu.modules.items;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.Serializable;

public abstract class Block implements Serializable {
    private ImageView imageView;

    private boolean isUsed = false;

    public Block(ImageView imageView) {
        this.imageView = imageView;
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getRow() {
        return GridPane.getRowIndex(getImageView());
    }

    public int getCol() {
        return GridPane.getColumnIndex(getImageView());
    }

}
