package main;

import main.entity.Player;
import main.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private Thread gameThread;
    private KeyHandler keyHandler;
    public CollisionChecker collisionChecker;
    public Player player;
    public TileManager tileManager;


    public GamePanel() {
        initialise();
    }

    private void initialise() {

        this.keyHandler       = new KeyHandler();
        this.collisionChecker = new CollisionChecker(this);
        this.player           = new Player(this, keyHandler);
        this.tileManager      = new TileManager(this);

        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // que?
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGame() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {
        double drawInterval = (double) 1000000000 / GameSettings.FPS;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta < 1) continue;
            Toolkit.getDefaultToolkit().sync();
            update();
            repaint();
            delta--;

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

    private void update() {
        player.update();
    }
}
