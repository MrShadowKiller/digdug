package ir.ac.kntu.logic.Map;

import ir.ac.kntu.modules.characters.Enemy;
import ir.ac.kntu.modules.characters.Player;
import ir.ac.kntu.modules.items.Block;
import ir.ac.kntu.modules.items.Item;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;

public class MapData implements Serializable {
    private Item item;

    private ArrayList<ArrayList<Block>> blocks;

    private final ArrayList<Player> players;

    private Player currentPlayer;

    private final ArrayList<Enemy> enemies;

    private boolean gameFinished = false;

    public MapData() {
        blocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            blocks.add(new ArrayList<>());
        }
        players = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ArrayList<ArrayList<Block>> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<ArrayList<Block>> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
