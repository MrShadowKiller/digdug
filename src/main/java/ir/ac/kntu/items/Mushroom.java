package ir.ac.kntu.items;

import ir.ac.kntu.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mushroom extends Item{

    public Mushroom() {
        super(new ImageView(new Image("assets/mushroom.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setxSpeed(player.getxSpeed()*1.5);
    }
}
