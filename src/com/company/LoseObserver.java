package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class LoseObserver extends Observer {

    GamePanel panel;

    LoseObserver(GamePanel gamePanel) {
        panel = gamePanel;
    }

    @Override
    public void onNotify() {
        handleGameOver();
    }

    private void handleGameOver(){
            showGameOverMessage();
            panel.player.keyRight = false;
            panel.player.keyLeft = false;
            panel.player.keyUp = false;

            Factory.level = 0;

            Factory.nextLevelCleaning(panel);
            try {
                Factory.createAllGameObjects(panel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }

    private void showGameOverMessage(){

        Object[] options = {"Yes, please",
                "No, close the game."};

        int n = JOptionPane.showOptionDialog(panel,
                "You've lost to the Kardashians. " +
                        "Would you like to play again?",
                "What is it like to lose to the Kardashians...?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (n == JOptionPane.YES_OPTION){
            panel.player.health = 3;
            System.out.println(panel.player.health);
        }
        else{
            for (Frame f : Frame.getFrames()){
                f.dispose();
                System.exit(0);
            }
        }



    }
}
