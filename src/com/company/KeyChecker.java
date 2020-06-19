package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class KeyChecker extends KeyAdapter {

    GamePanel panel;

    public KeyChecker(GamePanel panel){
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e){

        Player player = panel.player;
        if( e.getKeyChar() == 'a') {
            player.keyLeft = true;
            player.way = -1;
        }
        if( e.getKeyChar() == 'w') player.keyUp = true;
        if( e.getKeyChar() == 'd') {
            player.keyRight = true;
            player.way = 1;
        }
        if( e.getKeyChar() == ' ') {
            panel.playerMissiles.add(new PlayerMissile(player.x+player.width/2,
                    player.y + 20, 20, 20, panel));
        }

    }

    @Override
    public void keyReleased(KeyEvent e){

        Player player = panel.player;
        if( e.getKeyChar() == 'a') player.keyLeft = false;
        if( e.getKeyChar() == 'w') player.keyUp = false;
        if( e.getKeyChar() == 'd') player.keyRight = false;

    }

}
