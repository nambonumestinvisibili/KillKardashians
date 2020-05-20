package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class GamePanel extends JPanel implements ActionListener {

    Timer gameTimer;

    //Visible Objects
    Player player;

    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Obstacles> obstacles = new ArrayList<>();



    public GamePanel() throws FileNotFoundException {

        //adding all visible objects
        player = new Player(400, 300, this);
        makeObjects();

        //setting GameTimer
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run() {

                //movement of objects
                player.set();
                for (Obstacles obstacle : obstacles) obstacle.set();

                //checking if game should finish
                if (player.health < 1) showGameOverMessage();

                repaint();
            }

        }, 0, 17);
    }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D gtd =(Graphics2D) g;

        //drawing all visible objects
        player.draw(gtd);
        for (Wall wall : walls) wall.draw(gtd);
        for (Obstacles obstacle : obstacles) obstacle.draw(gtd);

        //if (player.health < 1) showGameOverMessage();


        //drawing all the other neccessary objects
        gtd.drawString("Health:" + String.valueOf(player.health),80,30);
    }

    public void makeObjects() throws FileNotFoundException {

        ArrayList<ArrayList<Integer>> leveldata = ParseLevel.parseLevel("resources\\level2.csv");
        int row = leveldata.size();
        int col = leveldata.get(0).size();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (leveldata.get(i).get(j) == 1){
                walls.add(new Wall(j*50, i*50, 50, 50));
                }

                if (leveldata.get(i).get(j) == 2){
                    obstacles.add(new Obstacles(j*50, i*50, 50, 50, this));
                }

            }
        }

    }

    private void showGameOverMessage(){

        //gtd.setFont(new Font("TimesRoman", Font.PLAIN, 800));
        //gtd.drawString("GAME OVER:" ,80,30);
        JOptionPane.showMessageDialog(this ,"GameOver");
        player.health = 3;



    }




    public void keyPressed(KeyEvent e) {
        if( e.getKeyChar() == 'a') player.keyLeft = true;
        if( e.getKeyChar() == 'w') player.keyUp = true;
        if( e.getKeyChar() == 's') player.keyDown = true;
        if( e.getKeyChar() == 'd') player.keyRight = true;
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
