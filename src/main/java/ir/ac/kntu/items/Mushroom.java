package ir.ac.kntu.items;

import ir.ac.kntu.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Mushroom extends Item implements Serializable {

    public Mushroom() {
        super(new ImageView(new Image("assets/mushroom.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setxSpeed(player.getxSpeed() * 2);
        player.setySpeed(player.getySpeed() * 2);
    }
}
