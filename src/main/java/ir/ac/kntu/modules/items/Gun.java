package ir.ac.kntu.modules.items;

import ir.ac.kntu.modules.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Gun extends Item implements Serializable {

    public Gun() {
        super(new ImageView(new Image("assets/gun.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setWeapon(new SniperGun());
    }
}
