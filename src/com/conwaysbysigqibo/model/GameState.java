package com.conwaysbysigqibo.model;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    private boolean isRunning;
    private ArrayList<Cell> startState;
    public int ROWS;
    public int COLUMNS;
    public Cell[][] currentState;

    public GameState(int rows, int columns, ArrayList<Cell> startState) {
        this.isRunning = false;
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.startState = startState;
        this.currentState = createInitialMatrix();
    }

    private Cell[][] createInitialMatrix() {
        Cell[][] grid = new Cell[ROWS][COLUMNS];
        // Initialize cells with coordinates
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell currentCell = new Cell(i, j);
                if (startState.contains(currentCell)) {
                    currentCell.reviveCell();
                }
                grid[i][j] = currentCell;
            }
        }
        return grid;
    }

    public void createNextGeneration() {
        ArrayList<Cell> cellsToKill = getCellsToKill();
        ArrayList<Cell> cellsToRevive = getDeadCellsToRevive();
        cellsToKill.forEach(Cell::killCell);
        cellsToRevive.forEach(Cell::reviveCell);
    }

    public ArrayList<Cell> getCellsToKill() {
        ArrayList<Cell> cellsToKill = new ArrayList<>();
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {
                Cell currentCell = currentState[i][j];
                ArrayList<Cell> neighbours;
                try {
                    neighbours = currentState[i][j].getNeighbours(currentState);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    continue;
                }
                long liveNeighborCount = neighbours.stream().filter(Cell::isAlive).count();
                if (currentCell.isAlive()) {
                    if (liveNeighborCount < 2 | liveNeighborCount > 3){
//                      Any live cell with fewer than two live neighbours dies, as if by underpopulation
//                      Any live cell with more than three live neighbours dies, as if by overpopulation
                        cellsToKill.add(currentState[i][j]);
                    }
                }
            }
        }
        return cellsToKill;
    }

    public ArrayList<Cell> getDeadCellsToRevive() {
        ArrayList<Cell> cellsToRevive = new ArrayList<>();
        for (int i=0; i<ROWS; i++) {
            for (int j=0; j<COLUMNS; j++) {
                Cell currentCell = currentState[i][j];
                ArrayList<Cell> neighbours;
                try {
                    neighbours = currentState[i][j].getNeighbours(currentState);
                } catch (ArrayIndexOutOfBoundsException ex) {

                    continue;
                }
                long liveNeighborCount = neighbours.stream().filter(Cell::isAlive).count();
                System.out.println("Cell (" + i + ", " + j + ") has " + liveNeighborCount + " live neighbors");
                if (!currentCell.isAlive() && liveNeighborCount == 3) {
//                      Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                    cellsToRevive.add(currentState[i][j]);
                }
            }
        }
        return cellsToRevive;
    }

    public Cell[][] getCurrentState() {
        return currentState;
    }

    public boolean checkTerminalState() {
        // define 'terminal' state as state when all cells are dead, or when any of the boundaries are touched
        boolean allCellsDead = Arrays.stream(currentState).flatMap(Arrays::stream).noneMatch(Cell::isAlive);
        boolean boundaryTouched = Arrays.stream(currentState).flatMap(Arrays::stream)
                .anyMatch(cell -> cell.isAlive() && cell.isAtBoundary(currentState[0].length, currentState.length));

        return allCellsDead || boundaryTouched;
    }
}
