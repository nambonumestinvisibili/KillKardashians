package com.company;

import javax.swing.*;

public class ObstacleLow extends Obstacle {


    public ObstacleLow(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        health = 1;
        img = new ImageIcon("resources\\obstacleImages\\khloe.png");

    }


}
