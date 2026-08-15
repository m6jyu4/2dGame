package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

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
        }
    }
}
