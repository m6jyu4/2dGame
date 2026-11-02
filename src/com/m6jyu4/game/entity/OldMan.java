package com.m6jyu4.game.entity;

import com.m6jyu4.game.Direction;
import com.m6jyu4.game.GamePanel;

import java.util.Random;

public class OldMan extends Entity {

        public OldMan(GamePanel gamePanel) {
                super(gamePanel);

                direction = Direction.DOWN;
                speed = 1;
                registerTexture();
                setDialogue();
        }

        private void registerTexture() {

                // Walking animations
                up1    = loadTexture("/npc/oldman_up_1");
                up2    = loadTexture("/npc/oldman_up_2");
                down1  = loadTexture("/npc/oldman_down_1");
                down2  = loadTexture("/npc/oldman_down_2");
                left1  = loadTexture("/npc/oldman_left_1");
                left2  = loadTexture("/npc/oldman_left_2");
                right1 = loadTexture("/npc/oldman_right_1");
                right2 = loadTexture("/npc/oldman_right_2");
        }

        @Override
        public void setAction() {

                actionLockCounter ++;

                if(actionLockCounter < 120) return;
                moveX = moveY = 0;

                var random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 10) {
                        moveX = 1;
                }
                if (i > 10 && i <= 20) {
                        moveX = -1;
                }
                if (i > 20 && i <= 30) {
                        moveY = 1;
                }
                if (i > 30 && i <= 40) {
                        moveY = -1;
                }
                if (i > 40 && i <= 50) {
                        moveX = 1;
                        moveY = 1;
                }
                if (i > 50 && i <= 60) {
                        moveX = -1;
                        moveY = -1;
                }
                if (i > 60 && i <= 70) {
                        moveX = 1;
                        moveY = -1;
                }
                if (i > 70) {
                        moveX = -1;
                        moveY = 1;
                }
                if (i > 80) {
                        moveX = 0;
                        moveY = 0;
                }

                actionLockCounter = 0;
        }

        public void setDialogue() {
                dialogues[0] = "Hello, lad.";
                dialogues[1] = "So you've come to this island \nto seek treasures?";
                dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for adventures.";
                dialogues[3] = "Well, good luck to you.";
        }

        public void speak() {
                super.speak();
        }
}
