package ir.ac.kntu;

public abstract class Weapon {
    private int hitRange;

    private int damage;

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

