package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Obstacle extends Sprites {

    ArrayList<ImageIcon> pictures = new ArrayList<>();
    int randomNumber;

    ImageIcon img = new ImageIcon("resources\\obstacleImages\\kim.jpg");
    Image kimimg = img.getImage();

    double xspeed = 1;
    int health;

    public Obstacle(int x, int y, int width, int height, GamePanel panel){
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
        gtd.drawImage(kimimg, x, y, null);

    }

    public void set(){

        hitBox.y += 3;
        for (Wall wall : panel.walls){
            if (hitBox.intersects(wall.hitBox)){

                hitBox.x += xspeed;
                x += xspeed;
            }
            if (!hitBox.intersects(wall.hitBox)){
                xspeed = -xspeed;
            }
        }
        hitBox.y -= 3;
    }

}
