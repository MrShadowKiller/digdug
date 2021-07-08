package ir.ac.kntu.items;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item implements ItemEffect {
    private ImageView imageView;

    public Item(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }


}
