package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Stone;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EnemyAI implements Runnable , Serializable {
    private final Enemy enemy;
    private final GridPane gridPane;
    private final MapData mapData;

    public EnemyAI(Enemy enemy, GridPane gridPane, MapData mapData) {
        this.enemy = enemy;
        this.gridPane = gridPane;
        this.mapData = mapData;
    }

    @Override
    public void run() {
        int playerX = mapData.getCurrentPlayer().getCol();
        int playerY = mapData.getCurrentPlayer().getRow();
        int enemyX = enemy.getCol();
        int enemyY = enemy.getRow();

        Way[][] pred = new Way[10][12];
        if (findWayToPlayer(enemyY, enemyX, playerY, playerX, pred)) {
            LinkedList<Way> path = new LinkedList<>();

            int crawlY = playerY;
            int crawlX = playerX;
            int temp = 0;
            path.add(new Way(crawlY, crawlX));

            while (pred[crawlY][crawlX] != null) {
                path.add(pred[crawlY][crawlX]);
                temp = crawlX;
                crawlX = pred[crawlY][crawlX].getCol();
                crawlY = pred[crawlY][temp].getRow();
            }

            path.removeLast();
            Way currentWay = path.removeLast();
            if (!enemy.isAlive()) {
                return;
            }
            System.out.println("Enemy on Way : " + currentWay.getRow() + currentWay.getCol());
            enemy.move(currentWay.getCol(), currentWay.getRow());
        }
    }

    public boolean findWayToPlayer(int enemyY, int enemyX, int playerY, int playerX, Way[][] pred) {
        LinkedList<Way> lineup = new LinkedList<Way>();
        boolean[][] visited = new boolean[10][12];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                visited[i][j] = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                pred[i][j] = null;
            }
        }

        visited[enemyY][enemyX] = true;
        lineup.add(new Way(enemyY, enemyX));
        while (!lineup.isEmpty()) {
            Way currentWay = lineup.remove();
            ArrayList<Way> ways = makeSquareWays(currentWay);
            for (int i = 0; i < ways.size(); i++) {
                if (!checkCollide(ways.get(i).getRow(), ways.get(i).getCol())) {
                    if (!visited[ways.get(i).getRow()][ways.get(i).getCol()]) {
                        visited[ways.get(i).getRow()][ways.get(i).getCol()] = true;
                        pred[ways.get(i).getRow()][ways.get(i).getCol()] = currentWay;
                        lineup.add(ways.get(i));

                        if (ways.get(i).getRow() == playerY && ways.get(i).getCol() == playerX) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Way> makeSquareWays(Way currentWay) {
        ArrayList<Way> ways = new ArrayList<>();

        if (currentWay.getRow() + 1 < 10) {
            ways.add(new Way(currentWay.getRow() + 1, currentWay.getCol()));
        }
        if (currentWay.getRow() - 1 >= 0) {
            ways.add(new Way(currentWay.getRow() - 1, currentWay.getCol()));
        }
        if (currentWay.getCol() + 1 < 12) {
            ways.add(new Way(currentWay.getRow(), currentWay.getCol() + 1));
        }
        if (currentWay.getCol() - 1 >= 0) {
            ways.add(new Way(currentWay.getRow(), currentWay.getCol() - 1));
        }
        return ways;
    }

    public boolean checkCollide(int row, int col) {
        return !mapData.getBlocks().get(row).get(col).isUsed();
    }

}
