package ir.ac.kntu;

import ir.ac.kntu.characters.Enemy;
import ir.ac.kntu.characters.Player;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class MapData {
    private ArrayList<Item> items;

    private ArrayList<ArrayList<Dirt>> dirts;

    private ArrayList<Player> players;

    private ArrayList<Enemy> enemies;

    HashMap<Integer,Integer> emptyBlocks;

    public MapData() {
        items = new ArrayList<>();
        dirts = new ArrayList<>();
        for (int i = 0;i<12;i++){
            dirts.add(new ArrayList<>());
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

    public ArrayList<ArrayList<Dirt>> getDirts() {
        return dirts;
    }

    public void setDirts(ArrayList<ArrayList<Dirt>> dirts) {
        this.dirts = dirts;
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Enemy> getEnemies() {
        return new ArrayList<>(enemies);
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }


}
