package ir.ac.kntu.modules.characters;

import java.io.Serializable;

public class Way implements Serializable {
    private final int row;

    private final int col;

    public Way(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
