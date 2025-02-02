package com.conwaysbysigqibo.model;

import java.awt.*;
import java.util.ArrayList;

public class Cell extends Point {
    private boolean isAlive;
    public Cell(int x, int y) {
        super(x, y);
        isAlive = false;
    }
    public void killCell() {
        this.isAlive = false;
    }

    public void setAlive() {
        this.isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void reviveCell() {
        this.isAlive = true;
    }

    public boolean isAtBoundary(int rows, int columns) {
        // Check if the cell is at the row/column edge
        return x == 0 || x == rows + 1 || y == 0 || y == columns  + 1;
    }

    public ArrayList<Cell> getNeighbours(Cell[][] grid) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        int rows = grid.length;
        int columns = grid[0].length;

        // Zoom in to the current cells 3*3 'neighbourhood'
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Skip the current cell
                if (i == 0 && j == 0)
                    continue;

                int neighbourX = this.x + i;
                int neighbourY = this.y + j;

                /*
                Stay within the boundaries
                We COULD implement 'virtual' cells outside the boundary to create an 'inifnity' edge effect here,
                but the instructions were clear that nothing outside the boundary should have an effect on the grid.
                I have observed that this results in 'dead-squares' for some patterns at the end as a consequence of live
                cells bundling up...
                */
                if (neighbourX >= 0 && neighbourX < rows && neighbourY >= 0 && neighbourY < columns) {
                    neighbours.add(grid[neighbourX][neighbourY]);
                }
            }
        }

        return neighbours;
    }
}
