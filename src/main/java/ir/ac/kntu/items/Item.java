package ir.ac.kntu.items;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Item extends Pane implements ItemEffect {
    private ImageView imageView;

    public Item(ImageView imageView) {
        this.imageView = imageView;
    }
}
