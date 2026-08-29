package main;

import javax.swing.*;

public class Main {
        public static void main(String[] args) {
                GamePanel gamePanel = new GamePanel();
                createWindow(new JFrame(), gamePanel);
                gamePanel.initialiseGame();
                gamePanel.startGame();
        }

        private static void createWindow(JFrame window, GamePanel gamePanel) {
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("2D Adventure");
                window.add(gamePanel);
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
        }
}