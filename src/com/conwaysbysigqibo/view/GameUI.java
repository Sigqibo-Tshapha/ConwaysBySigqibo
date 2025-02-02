package com.conwaysbysigqibo.view;

import com.conwaysbysigqibo.model.GameState;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame{
    /*
    Creates the JFrame, GridPanel, Grid an
     */
    private GameState game;
    private GamePanel gamePanel;

    private Timer gameTimer;

    public GameUI(GameState game, int cellSize) {
        int columns = game.COLUMNS;
        int rows = game.ROWS;
        this.game = game;
        this.gamePanel = new GamePanel(game, cellSize);

        // Set up the UI Components
        JFrame frame = new JFrame("Conways By Sigqibo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel buttonPanel = createButtonsPanel();

        // Bring the components together
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerPanel.setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));
        gamePanel.setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));
        centerPanel.add(gamePanel, BorderLayout.CENTER);
        frame.setSize(columns * columns , rows * rows + 4 * cellSize);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        pack();
        frame.setVisible(true);
    }

    public JPanel createButtonsPanel() {
        // Set up the buttons
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> nextGeneration());
        JButton runBtn = new JButton("Run");
        runBtn.addActionListener(e -> startGame());
        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener(e -> stopGame());

        // Add to a new panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(runBtn);
        buttonPanel.add(stopBtn);
        return buttonPanel;
    }

    public void nextGeneration() {
        game.createNextGeneration();
        gamePanel.repaint();
    }

    public void startGame() {
        Timer timer = new Timer(50, e -> {
            if (Thread.interrupted()) {
                ((Timer) e.getSource()).stop();
                System.out.println("Game interrupted manually.");
                return;
            }
            this.game.createNextGeneration();
            gamePanel.repaint();

            if (game.checkTerminalState()) {
                // Stop the game when you touch the edges or all cells die
                this.gameTimer.stop();
            }
        });

        timer.start();  // Start the timer.
        this.gameTimer = timer;
    }

    public void stopGame() {
        System.out.println("Stop btn clicked");
        if (this.gameTimer != null) {
            this.gameTimer.stop();
        }
        gamePanel.repaint();
    }
}
