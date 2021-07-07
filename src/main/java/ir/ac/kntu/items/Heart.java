package ir.ac.kntu.items;

import ir.ac.kntu.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heart extends Item{

    public Heart() {
        super(new ImageView(new Image("assets/heart.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setHp(player.getHp()+1);
    }
}
