package com.conwaysbysigqibo.view;

import com.conwaysbysigqibo.model.Cell;
import com.conwaysbysigqibo.model.GameState;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public final int COLUMNS;
    private final int ROWS;
    private Cell[][] matrixToDraw;

    private int cellSize;

    public GamePanel(GameState gameState, int cellSize) {
        this.ROWS = gameState.ROWS;
        this.COLUMNS = gameState.COLUMNS;
        this.matrixToDraw = gameState.getCurrentState();
        this.cellSize = cellSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell cell = matrixToDraw[i][j];
                // Draw my cell
                if (cell.isAlive()) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}