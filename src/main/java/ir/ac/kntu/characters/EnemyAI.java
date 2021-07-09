package ir.ac.kntu.characters;

import ir.ac.kntu.MapData;
import ir.ac.kntu.items.Dirt;
import ir.ac.kntu.items.Stone;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EnemyAI implements Runnable {
    private Enemy enemy;
    private GridPane gridPane;
    private MapData mapData;

    public EnemyAI(Enemy enemy, GridPane gridPane, MapData mapData) {
        this.enemy = enemy;
        this.gridPane = gridPane;
        this.mapData = mapData;
    }

    @Override
    public void run() {
        Platform.runLater(this);

        while (true) {
            int playerX = GridPane.getColumnIndex(mapData.getCurrentPlayer().getCurrentImageView());
            int playerY = GridPane.getRowIndex(mapData.getCurrentPlayer().getCurrentImageView());
            int enemyX = GridPane.getColumnIndex(enemy.getCurrentImageView());
            int enemyY = GridPane.getRowIndex(enemy.getCurrentImageView());

            Way[][] pred = new Way[10][12];
            if (findWayToPlayer(enemyY, enemyX, playerY, playerX, pred)) {

                LinkedList<Way> path = new LinkedList<>();

                int crawlY = playerY;
                int crawlX = playerX;

                path.add(new Way(crawlY, crawlX));
                while (pred[crawlY][crawlX] != null) {
                    path.add(pred[crawlY][crawlX]);
                    crawlX = pred[crawlY][crawlX].getCol();
                    crawlY = pred[crawlY][crawlX].getRow();
                }
                while (path.size() == 0) {
                    Way currentWay = path.removeLast();
                    GridPane.setRowIndex(enemy.getCurrentImageView(), currentWay.getRow());
                    GridPane.setColumnIndex(enemy.getCurrentImageView(), currentWay.getCol());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    if (playerX != GridPane.getColumnIndex(mapData.getCurrentPlayer().getCurrentImageView()) ||
                            playerY != GridPane.getRowIndex(mapData.getCurrentPlayer().getCurrentImageView())) {
                        break;
                    }
                }

            }

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
                if (!checkCollide(ways.get(i).getRow(),ways.get(i).getCol())) {
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
        if (!mapData.getBlocks().get(row).get(col).isUsed()) {
            if (mapData.getBlocks().get(row).get(col) instanceof Dirt) {
                return false;
            } else {
                System.out.println("Collided STONE!");
                return true;
            }
        }
        return false;
    }



    public void moveUp() {

    }
}
