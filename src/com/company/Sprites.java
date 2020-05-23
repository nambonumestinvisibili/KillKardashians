package com.company;

import java.awt.*;

public abstract class Sprites {

    int x;
    int y;
    int width;
    int height;

    GamePanel panel;

    Rectangle hitBox;


    public abstract void draw(Graphics2D gtd);
    public abstract void set();

}
