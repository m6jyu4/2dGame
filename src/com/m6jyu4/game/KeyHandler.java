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
                        case KeyEvent.VK_W -> upPressed = true;
                        case KeyEvent.VK_A -> leftPressed = true;
                        case KeyEvent.VK_S -> downPressed = true;
                        case KeyEvent.VK_E -> rightPressed = true;
                        case KeyEvent.VK_SHIFT  -> runPressed = true;
                        case KeyEvent.VK_ESCAPE -> escAction();
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

        private void escAction() {
                if (gamePanel.gameState == GamePanel.playState) {
                        gamePanel.gameState = GamePanel.pauseState;
                }
                else if (gamePanel.gameState == GamePanel.pauseState) {
                        gamePanel.gameState = GamePanel.playState;
                }
        }
}
