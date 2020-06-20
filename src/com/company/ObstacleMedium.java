package com.company;

import javax.swing.*;

public class ObstacleMedium extends Obstacle {


    public ObstacleMedium(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        idx = 1;
        health = 2;
        //img = new ImageIcon("resources\\obstacleImages\\kylie.png");
        xspeed = 1;

    }


}
