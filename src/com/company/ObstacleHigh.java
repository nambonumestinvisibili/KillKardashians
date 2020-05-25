package com.company;

import javax.swing.*;

public class ObstacleHigh extends Obstacle {


    public ObstacleHigh(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        health = 3;
        img = new ImageIcon("resources\\obstacleImages\\kim.jpg");
        xspeed = 2;

    }
}
