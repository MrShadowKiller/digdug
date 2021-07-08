package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Dirt extends Node {
    private ImageView imageView;

    private boolean isUsed = false;

    public Dirt() {
        this.imageView = new ImageView(new Image("assets/dirt.png"));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
