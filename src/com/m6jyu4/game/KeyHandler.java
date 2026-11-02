package com.m6jyu4.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

        GamePanel gamePanel;

        public boolean upPressed;
        public boolean downPressed;
        public boolean leftPressed;
        public boolean rightPressed;
        public boolean runPressed;

        public KeyHandler(GamePanel gamePanel) {
                this.gamePanel = gamePanel;
        }

        @Override
        public void keyTyped(KeyEvent keyEvent) {}

        @Override
        public void keyPressed(KeyEvent keyEvent) {
                int code = keyEvent.getKeyCode();
                switch(code){
                        case KeyEvent.VK_W -> wAction();
                        case KeyEvent.VK_A -> leftPressed = true;
                        case KeyEvent.VK_S -> sAction();
                        case KeyEvent.VK_E -> rightPressed = true;
                        case KeyEvent.VK_SHIFT  -> runPressed = true;
                        case KeyEvent.VK_ESCAPE -> escAction();
                        case KeyEvent.VK_ENTER -> enterAction();
                }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
                int code = keyEvent.getKeyCode();
                switch(code){
                        case KeyEvent.VK_W -> upPressed = false;
                        case KeyEvent.VK_A -> leftPressed = false;
                        case KeyEvent.VK_S -> downPressed = false;
                        case KeyEvent.VK_E -> rightPressed = false;
                        case KeyEvent.VK_SHIFT -> runPressed = false;
                }
        }

        private void wAction() {
                if (gamePanel.gameState == GameState.TITLE) {
                        if (gamePanel.ui.commandNum <= 0) return;
                        gamePanel.ui.commandNum--;
                }
                if (gamePanel.gameState == GameState.PLAY) {
                        upPressed = true;
                }

        }

        private void sAction() {
                if (gamePanel.gameState == GameState.TITLE) {
                        if (gamePanel.ui.commandNum >= 2) return;
                        gamePanel.ui.commandNum++;
                }
                if (gamePanel.gameState == GameState.PLAY) {
                        downPressed = true;
                }
        }

        private void enterAction() {
                if (gamePanel.gameState == GameState.TITLE) {
                        if (gamePanel.ui.commandNum == 0) gamePanel.gameState = GameState.PLAY;
                        if (gamePanel.ui.commandNum == 1) ;
                        if (gamePanel.ui.commandNum == 2) System.exit(0);
                }
                else if (gamePanel.gameState == GameState.PLAY) {

                }
                else if (gamePanel.gameState == GameState.DIALOGUE) {
                        gamePanel.gameState = GameState.PLAY;
                }
        }

        private void escAction() {
                if (gamePanel.gameState == GameState.PLAY) {
                        gamePanel.gameState = GameState.PAUSE;
                }
                else if (gamePanel.gameState == GameState.PAUSE) {
                        gamePanel.gameState = GameState.PLAY;
                }
        }
}
