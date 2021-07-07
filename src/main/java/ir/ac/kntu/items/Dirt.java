package ir.ac.kntu.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class Dirt extends Pane {
    private ImageView imageView;

    public Dirt() {
        this.imageView = new ImageView(new Image("assets/dirt.png"));
        this.getChildren().add(imageView);
    }
}
