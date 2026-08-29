package main;

import main.entity.Player;
import main.object.GameObject;
import main.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

        private Thread gameThread;
        private KeyHandler keyHandler;
        public CollisionChecker collisionChecker;
        public Player player;
        public GameObject[] object;
        public AssetPlacer assetPlacer;
        public TileManager tileManager;
        public Sound sound;
        public UI ui;

        public GamePanel() {
                initialise();
        }

        private void initialise() {

                this.keyHandler       = new KeyHandler();
                this.collisionChecker = new CollisionChecker(this);
                this.player           = new Player(this, keyHandler);
                this.object           = new GameObject[10];
                this.assetPlacer      = new AssetPlacer(this);
                this.tileManager      = new TileManager(this);
                this.sound            = new Sound();
                this.ui               = new UI(this);

                this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
                this.setBackground(Color.BLACK);
                this.setDoubleBuffered(true); // que?
                this.addKeyListener(keyHandler);
                this.setFocusable(true);

        }

        public void initialiseGame() {
                assetPlacer.setObject();
//                playMusic(0);
        }

        public void startGame() {
                this.gameThread = new Thread(this);
                this.gameThread.start();
        }

        public void stopGame() {
                this.gameThread = null;
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
                for(int i = 0; i < object.length; i++) {
                        if(object[i] != null) {
                                object[i].draw(g2, this);
                        }
                }
                player.draw(g2);

                ui.draw(g2);

                g2.dispose();
        }

        private void update() {
                player.update();

        }

        public void playMusic(int i) {
                sound.setFile(i);
                sound.play();
                sound.loop();
        }

        public void stopMusic() {
                sound.stop();
        }

        public void playSoundEffect(int i) {
                sound.setFile(i);
                sound.play();
        }
}
