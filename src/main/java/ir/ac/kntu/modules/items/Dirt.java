package ir.ac.kntu.modules.items;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Dirt extends Block implements Serializable {

    public Dirt() {
        super(new ImageView("assets/dirt.png"));
    }

}
