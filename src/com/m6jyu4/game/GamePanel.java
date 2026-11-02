package com.m6jyu4.game;

import com.m6jyu4.game.entity.Entity;
import com.m6jyu4.game.entity.OldMan;
import com.m6jyu4.game.entity.Player;
import com.m6jyu4.game.object.GameObject;
import com.m6jyu4.game.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

        private Thread          gameThread;

        public CollisionChecker collisionChecker;
        public Player           player;
        public Entity           npc[];
        public GameObject[]     object;
        public AssetPlacer assetPlacer;
        public TileManager      tileManager;
        public Sound sound;
        public UI ui;

        public GameState gameState;

        public GamePanel() {
                initialise();
        }

        private void initialise() {

                KeyHandler keyHandler = new KeyHandler(this);
                this.collisionChecker = new CollisionChecker(this);
                this.player           = new Player(this, keyHandler);
                this.npc              = new Entity[10];
                this.object           = new GameObject[10];
                this.assetPlacer      = new AssetPlacer(this);
                this.tileManager      = new TileManager(this);
                this.sound            = new Sound();
                this.ui               = new UI(this);

                this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
                this.setBackground(Color.BLACK);
                this.setDoubleBuffered(true);
                this.addKeyListener(keyHandler);
                this.setFocusable(true);

        }

        public void initialiseGame() {
                assetPlacer.setObject();
                assetPlacer.setNPC();
                gameState = GameState.TITLE;
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
                        update(delta);
                        repaint();
                        delta--;

                }
        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D)g;

                if (gameState == GameState.TITLE) ui.draw(g2);
                else renderGame(g2);

                g2.dispose();
        }

        private void renderGame(Graphics2D g2) {
                tileManager.draw(g2);
                for (GameObject gameObject : object) {
                        if (gameObject == null) continue;
                        gameObject.draw(g2, this);
                }
                for (Entity entity : npc) {
                        if (entity == null) continue;
                        entity.draw(g2);
                }
                player.draw(g2);
                ui.draw(g2);
        }

        private void update(double delta) {
                if(gameState == GameState.PLAY) {

                        player.update(delta);
                        for (Entity entity : npc) {
                                if (entity == null) continue;
                                entity.update();
                        }

                }
                if(gameState == GameState.PAUSE) {

                }
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
