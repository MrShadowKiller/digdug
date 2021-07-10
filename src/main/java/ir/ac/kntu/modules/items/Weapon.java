package ir.ac.kntu.modules.items;

import java.io.Serializable;

public abstract class Weapon implements Serializable {
    private final int hitRange;

    private final int damage;

    public Weapon(int hitRange, int damage) {
        this.hitRange = hitRange;
        this.damage = damage;
    }

    public int getHitRange() {
        return hitRange;
    }

    public int getDamage() {
        return damage;
    }
}

