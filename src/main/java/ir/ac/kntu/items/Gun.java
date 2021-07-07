package ir.ac.kntu.items;

import ir.ac.kntu.characters.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gun extends Item{

    public Gun() {
        super(new ImageView(new Image("assets/gun.png")));
    }

    @Override
    public void doEffect(Player player) {
        player.setWeapon(new SniperGun());
    }
}
