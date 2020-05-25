package com.company;

import com.sun.prism.paint.Color;

import javax.swing.*;
import java.awt.*;

public class Missile extends Sprites {

    java.awt.Color color;
    double xspeed = 3;
    int side;

    public Missile(int x, int y, int width, int height, GamePanel panel){
        this.height = height;
        this.width = width;
        this.panel = panel;
        this.side = panel.player.way;
        this.x = x;
        this.y = y;
        this.hitBox = new Rectangle(x, y, width+2, height);
    }


    @Override
    public void draw(Graphics2D gtd) {
        gtd.setColor(color);
        gtd.fillRect(x,y,width,height);

    }

    @Override
    public void set() {
        x+= side *xspeed;
        hitBox.x += side *xspeed;

    }


}
