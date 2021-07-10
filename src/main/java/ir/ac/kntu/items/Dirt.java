package ir.ac.kntu.items;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Dirt extends Block implements Serializable {

    public Dirt() {
        super(new ImageView("assets/dirt.png"));
    }

}
