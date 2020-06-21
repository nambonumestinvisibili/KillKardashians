package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    Timer gameTimer;
    int timeCounter = 0;

    //Visible Objects
    Player player;

    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<PlayerMissile> playerMissiles = new ArrayList<>();
    ArrayList<ObstacleMissile> obstacleMissiles = new ArrayList<>();

    ArrayList<Observer> observers = new ArrayList<>();

    int frameX = 1200;


    public GamePanel() throws FileNotFoundException {


        //adding all visible objects
        player = new Player(400, 300, this);
        Factory.createAllGameObjects(this);

        //adding observers
        observers.add(new WinObserver(this));
        observers.add(new LoseObserver(this));

        //setting GameTimer
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run() {

                //timeCounter is used as an interval for shooting missiles by Obstacles
                if (timeCounter > 122) timeCounter = 0;
                else timeCounter++;


                //movement of objects
                player.set();
                for (Obstacle obstacle : obstacles) obstacle.set();
                for (Missile missile : playerMissiles) missile.set();
                for(Missile missile : obstacleMissiles) missile.set();

                //Checking if player missiles hit obstacles or whether they came to be out of the frame
                checkMissilesAndObstaclesIntersect();

                //For all remaining obstacle shoot missiles if interval is ok
                if ((timeCounter % 30 == 0))
                    shootObstacleMissiles();


                //Win Observer
                if ((Factory.levelPaths.size() == Factory.level+1 &&
                        obstacles.size() == 0)) notifyObserver(observers.get(0));

                //Lose Observer
                if(player.health < 1) notifyObserver(observers.get(1));

                //getting to next level
                nextlevel();


                repaint();
            }

        }, 0, 17);
    }


    //private-void functions to make TaskTimer less cluttered:

    private void checkMissilesAndObstaclesIntersect(){
        ArrayList<Integer> missilesToRemove = new ArrayList<>();
        ArrayList<Integer> obstaclesToRemove = new ArrayList<>();

        for (Missile missile : playerMissiles) {
            if (missile.x > frameX || missile.x < 0){
                missilesToRemove.add(playerMissiles.indexOf(missile));
            }

            for (Obstacle obstacle : obstacles){
                if (missile.hitBox.intersects(obstacle.hitBox)){
                    obstacle.health--;
                    

                    if (obstacle.health == 0) {
                        obstaclesToRemove.add(obstacles.indexOf(obstacle));
                    }
                    missilesToRemove.add(playerMissiles.indexOf(missile));

                }
            }
        }

        for (int every : missilesToRemove) playerMissiles.remove(every);
        for (int every : obstaclesToRemove) obstacles.remove(every);
    }

    private void shootObstacleMissiles(){
        for (Obstacle obstacle: obstacles){
            if (player.y >= obstacle.y && player.y < obstacle.y + obstacle.height){
                obstacleMissiles.add(new ObstacleMissile(obstacle.x, obstacle.y, 20,20, this));
            }
        }
    }


    //Handling game logistics
    private void nextlevel(){
        Factory.handleNextLevel(this);
    }

    //Handling observers
    private void notifyObserver(Observer obs){
            obs.onNotify();
    }

    //Handling painting
    public void paint(Graphics g){

        super.paint(g);
        Graphics2D gtd =(Graphics2D) g;

        //drawing all visible objects
        player.draw(gtd);
        for (Wall wall : walls) wall.draw(gtd);
        for (Obstacle obstacle : obstacles) obstacle.draw(gtd);
        for (Missile missile : playerMissiles) missile.draw(gtd);
        for (Missile missile : obstacleMissiles) missile.draw(gtd);

        //if (player.health < 1) showGameOverMessage();


        //drawing all the other neccessary objects
        gtd.drawString("Health:" + player.health,80,30);
    }

}
