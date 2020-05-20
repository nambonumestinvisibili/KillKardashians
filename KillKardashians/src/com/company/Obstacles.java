package com.company;

import java.awt.*;

public class Obstacles {

    int x;
    int y;
    int width = 50;
    int height = 50;

    GamePanel panel;

    double movement = 1;

    Rectangle hitBox;


    public Obstacles(int x, int y, int width, int height, GamePanel panel){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.panel = panel;
        hitBox = new Rectangle(x, y, width+2, height);
    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.red);
        gtd.fillRect(x, y, width, height);

    }

    public void set(){

        hitBox.y += 3;
        for (Wall wall : panel.walls){
            if (hitBox.intersects(wall.hitBox)){

                hitBox.x += movement;
                x += movement;
            }
            if (!hitBox.intersects(wall.hitBox)){
                movement = -movement;
            }
        }
        hitBox.y -= 3;
    }




}
