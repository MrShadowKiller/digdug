package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Dirt extends Block{

    public Dirt() {
        super(new ImageView(new Image("assets/dirt.png")));
    }

}
