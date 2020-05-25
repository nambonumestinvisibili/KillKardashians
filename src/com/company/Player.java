package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Sprites{

    int startx = 400;
    int starty = 500;

    double xspeed;
    double yspeed;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    int health;

    int way = 1;

    public Player(int x, int y, GamePanel panel){

        this.panel = panel;
        this.x = x;
        this.y = y;
        width = 50;
        height = 50;
        hitBox = new Rectangle(x,y, width, height);

        health = 4;

    }

    public void set() {

        maintainSpeed();
        maintainPlatformCollision();

        isHit();

        x += xspeed;
        y += yspeed;

        hitBox.x = x;
        hitBox.y = y;
    }

    private void maintainSpeed(){

        //maintaining speed
        if (keyLeft && keyRight || !keyRight && !keyLeft) xspeed *= 0.8;
        else if (keyLeft && !keyRight) xspeed--;
        else if (!keyLeft && keyRight) xspeed++;

        if(xspeed > 0 && xspeed < 0.75) xspeed = 0;
        if(xspeed < 0 && xspeed > -0.75) xspeed = 0;

        if(xspeed > 7) xspeed = 7;
        if(xspeed < -7) xspeed = -7;

        //maintaining jumping
        if(keyUp) {

            hitBox.y++;
            for (Wall wall: panel.walls){
                if (wall.hitBox.intersects(this.hitBox)) yspeed = -10;
            }
            hitBox.y--;
            //yspeed = -6;
        }
        yspeed += 0.3;

    }

    private void maintainPlatformCollision(){
        //Horizontal Collision
        hitBox.x += xspeed;
        for (Wall wall : panel.walls){
            if (hitBox.intersects(wall.hitBox)){
                hitBox.x -= xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                xspeed = 0;
                x = hitBox.x;
            }
        }



        //Vertical Collision
        hitBox.y += yspeed;
        for (Wall wall : panel.walls){
            if (hitBox.intersects(wall.hitBox)){
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }
    }

    private void isHit(){
        ArrayList<Integer> obstacleMissilesToRemove = new ArrayList<>();

        //checking getting hit by an obstacle
        for (Obstacle obstacle : panel.obstacles){
            if (hitBox.intersects(obstacle.hitBox)){
                health--;

                hitBox.x = startx;
                hitBox.y = starty;
                x = startx;
                y = starty;
            }
        }

        //checking getting hit by a missile
        for (Missile missile : panel.obstacleMissiles){
            if (hitBox.intersects(missile.hitBox)){
                health--;

                hitBox.x = startx;
                hitBox.y = starty;
                x = startx;
                y = starty;

                obstacleMissilesToRemove.add(panel.obstacleMissiles.indexOf(missile));

            }
        }
        for (int every : obstacleMissilesToRemove) panel.obstacleMissiles.remove(every);
    }


    public void draw(Graphics2D gtd) {
        gtd.setColor(Color.black);
        gtd.fillRect(x,y,width,height);

        gtd.setFont(new Font("TimesRoman", Font.PLAIN, 20));


    }
}
