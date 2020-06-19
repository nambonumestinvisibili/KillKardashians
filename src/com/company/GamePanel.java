package com.company;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements ActionListener {

    Timer gameTimer;
    int timeCounter = 0;

    //Visible Objects
    Player player;

    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<PlayerMissile> playerMissiles = new ArrayList<>();
    ArrayList<ObstacleMissile> obstacleMissiles = new ArrayList<>();

    int frameX = 1200;


    public GamePanel() throws FileNotFoundException {

        //adding all visible objects
        player = new Player(400, 300, this);
        ParseLevel.createAllGameObjects(this);

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
                checkMissilesAndObstacles();

                //For all remaining obstacle shoot missiles if interval is ok
                if ((timeCounter % 30 == 0))
                    shootObstacleMissiles();


                //checking if game should finish
                handleWin();
                handleGameOver();

                //getting to next level
                handleNextLevel();


                repaint();
            }

        }, 0, 17);
    }




    //Handling missile things to handle

    private void checkMissilesAndObstacles(){
        ArrayList<Integer> missilesToRemove = new ArrayList<>();
        ArrayList<Integer> obstaclesToRemove = new ArrayList<>();

        for (Missile missile : playerMissiles) {
            if (missile.x > frameX || missile.x < 0){
                missilesToRemove.add(playerMissiles.indexOf(missile));
            }

            for (Obstacle obstacle : obstacles){
                if (missile.hitBox.intersects(obstacle.hitBox)){
                    obstacle.health--;
                    System.out.println(obstacle.health);

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
    private void showGameOverMessage(){

        Object[] options = {"Yes, please",
                "No, close the game."};

        int n = JOptionPane.showOptionDialog(this,
                "You've lost to the Kardashians. " +
                "Would you like to play again?",
                "What is it like to lose to the Kardashians...?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (n == JOptionPane.YES_OPTION){
            player.health = 3;
        }
        else{
            for (Frame f : Frame.getFrames()){
                f.dispose();
                System.exit(0);
            }
        }



    }

    private void nextLevelCleaning(){
        walls.clear();
        playerMissiles.clear();
        obstacleMissiles.clear();
        obstacles.clear();

        player.x = player.startx;
        player.hitBox.x = player.startx;
        player.y = player.starty;
        player.hitBox.y = player.starty;
    }

    private void handleNextLevel(){
        if (obstacles.size() == 0){
            ParseLevel.level++;

            nextLevelCleaning();

            try {
                ParseLevel.createAllGameObjects(this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleGameOver(){
        if (player.health < 1) {
            showGameOverMessage();
            ParseLevel.level = 0;

            nextLevelCleaning();
            try {
                ParseLevel.createAllGameObjects(this);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleWin(){
        if (ParseLevel.levelPaths.size() == ParseLevel.level+1 &&
                            obstacles.size() == 0){
            System.out.println("siemano");
            Object[] options = {"Yes, please",
                    "No, close the game."};

            int n = JOptionPane.showOptionDialog(this,
                    "You've won to the Kardashians. " +
                            "Would you like to play again?",
                    "Wow... I'm amazed",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (n == JOptionPane.YES_OPTION){
                ParseLevel.level = -1;
                handleNextLevel();
                player.health = 3;
            }
            else{
                for (Frame f : Frame.getFrames()){
                    f.dispose();
                    System.exit(0);
                }
            }
        }
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


    //Handling KeyEvent Actions
    public void keyPressed(KeyEvent e) {
        if( e.getKeyChar() == 'a') {
            player.keyLeft = true;
            player.way = -1;
        }
        if( e.getKeyChar() == 'w') player.keyUp = true;
        if( e.getKeyChar() == 's') player.keyDown = true;
        if( e.getKeyChar() == 'd') {
            player.keyRight = true;
            player.way = 1;
        }
        if( e.getKeyChar() == ' ') {
            playerMissiles.add(new PlayerMissile(player.x+player.width/2,
                    player.y + 20, 20, 20, this));
        }
    }

    public void keyReleased(KeyEvent e) {
        if( e.getKeyChar() == 'a') player.keyLeft = false;
        if( e.getKeyChar() == 'w') player.keyUp = false;
        if( e.getKeyChar() == 's') player.keyDown = false;
        if( e.getKeyChar() == 'd') player.keyRight = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
