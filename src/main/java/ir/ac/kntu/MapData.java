package ir.ac.kntu;

import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import ir.ac.kntu.items.Block;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class MapData {
    private ArrayList<Item> items;

    private ArrayList<ArrayList<Block>> blocks;

    private ArrayList<Player> players;

    private Player currentPlayer;

    private ArrayList<Enemy> enemies;

    HashMap<Integer,Integer> emptyBlocks;


    public MapData() {
        items = new ArrayList<>();
        blocks = new ArrayList<>();
        for (int i = 0;i<10;i++){
            blocks.add(new ArrayList<>());
        }
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        emptyBlocks = new HashMap<>();
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
}
