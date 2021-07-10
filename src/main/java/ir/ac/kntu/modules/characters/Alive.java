package ir.ac.kntu.modules.characters;

public interface Alive {
    void move(int x, int y) throws InterruptedException;

    boolean isAlive();

    void getHit(int damage);

    void deadAnimation();
}
