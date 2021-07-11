package ir.ac.kntu.modules.characters.navigation;

import ir.ac.kntu.logic.Map.MapData;
import ir.ac.kntu.modules.characters.Enemy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnemyAI implements Runnable, Serializable {
    private final Enemy enemy;
    private final MapData mapData;
    private Way[][] pred;

    public EnemyAI(Enemy enemy, MapData mapData) {
        this.enemy = enemy;
        this.mapData = mapData;
    }

    @Override
    public void run() {
        int playerX = mapData.getCurrentPlayer().getCol();
        int playerY = mapData.getCurrentPlayer().getRow();
        int enemyX = enemy.getCol();
        int enemyY = enemy.getRow();

        pred = new Way[10][12];
        if (findWayToPlayer(enemyY, enemyX, playerY, playerX)) {
            LinkedList<Way> path = new LinkedList<>();

            int crawlY = playerY;
            int crawlX = playerX;
            int temp;
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
            System.out.println("Enemy on Way : " + currentWay.getRow() + " " + currentWay.getCol());
            enemy.move(currentWay.getCol(), currentWay.getRow());
        }
    }

    public boolean findWayToPlayer(int enemyY, int enemyX, int playerY, int playerX) {
        LinkedList<Way> lineup = new LinkedList<>();
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
            for (Way way : ways) {
                if (!checkCollide(way.getRow(), way.getCol())) {
                    if (!visited[way.getRow()][way.getCol()]) {
                        visited[way.getRow()][way.getCol()] = true;
                        pred[way.getRow()][way.getCol()] = currentWay;
                        lineup.add(way);

                        if (way.getRow() == playerY && way.getCol() == playerX) {
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
