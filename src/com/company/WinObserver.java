package com.company;

import javax.swing.*;
import java.awt.*;

public class WinObserver extends  Observer {

    GamePanel panel;

    WinObserver(GamePanel gamePanel){
        panel = gamePanel;
    }

    public void onNotify(){
        handleWin();
    }

    private void handleWin(){
            Object[] options = {"Yes",
                    "No, close the game."};

            int n = JOptionPane.showOptionDialog(panel,
                    "You've won to the Kardashians. " +
                            "Would you like to play again?",
                    "Wow... I'm amazed",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (n == JOptionPane.YES_OPTION){
                Factory.level = -1;
                Factory.handleNextLevel(panel);
                panel.player.health = 3;
            }
            else{
                for (Frame f : Frame.getFrames()){
                    f.dispose();
                    System.exit(0);
                }
            }

    }



}
