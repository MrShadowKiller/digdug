package ir.ac.kntu.modules.items;

import ir.ac.kntu.modules.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Heart extends Item implements Serializable {

    public Heart() {
        super(new ImageView(new Image("assets/heart.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setHp(player.getHp() + 1);
    }
}
