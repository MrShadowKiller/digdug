package ir.ac.kntu.items;

import javafx.scene.image.ImageView;

public abstract class Block {
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
}
