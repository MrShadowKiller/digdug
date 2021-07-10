package ir.ac.kntu;

import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import ir.ac.kntu.items.Block;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Heart;
import ir.ac.kntu.items.Item;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;

public class MapData {
    private Item item;

    private ArrayList<ArrayList<Block>> blocks;

    private ArrayList<Player> players;

    private Player currentPlayer;

    private ArrayList<Enemy> enemies;

    private GridPane gridPane;

    public MapData(GridPane gridPane) {
        blocks = new ArrayList<>();
        for (int i = 0;i<10;i++){
            blocks.add(new ArrayList<>());
        }
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        this.gridPane = gridPane;
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
        return new ArrayList<>(players);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
