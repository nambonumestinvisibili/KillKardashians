package com.company;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameStarter extends JFrame {

    JButton play;
    JTextPane instructions;

   public GameStarter(){
       setSize(1200,800);
       setTitle("Kill Kardashians");
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setResizable(false);


       String invits = "\n Welcome to Kill Kardashians Game!" +
               "\n The quest is to set the world free from those terrible Kardashian girls." +
               "\n In order to win the game you have to complete three tasks." +
               "\n In every one of them kill all the Kardahsians." +
               "\n Beware! It takes different amount of bullets to kill different Kardashian." +
               "\n You have three lives. Lose - the world will be taken over by them." +
               "\n Win and we all will be grateful." +
               "\n\n" +
               "\n Use A,W,D to move around. Hit space to shoot missiles." +
               "\n Click Play! to start the game and have fun!";

       instructions = new JTextPane();
       instructions.setBounds((this.getWidth()-600)/2, 100, 600, 380);

       //formatting instructions
       SimpleAttributeSet attributeSet = new SimpleAttributeSet();
       StyleConstants.setBold(attributeSet, true);
       StyleConstants.setFontSize(attributeSet, 18);

       instructions.setCharacterAttributes(attributeSet, true);
       instructions.setText(invits);

       play = new JButton("Play!");
       play.setBounds((this.getWidth()-300)/2, 500, 300,100);
       play.setVisible(true);
       play.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               try {
                   MainFrame frame = new MainFrame();

                   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                   frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
                           (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));

                   dispose();
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               }
           }
       });

       add(play);
       add(instructions);

       setLayout(null);
       setVisible(true);
   }
}
