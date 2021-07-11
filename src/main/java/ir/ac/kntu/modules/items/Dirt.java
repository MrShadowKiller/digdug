package ir.ac.kntu.modules.items;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Dirt extends Block implements Serializable {

    public Dirt(int row, int col) {
        super(row, col, new ImageView("assets/dirt.png"));
    }

}
