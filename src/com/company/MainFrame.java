package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class MainFrame extends JFrame {


    public MainFrame() throws FileNotFoundException {

        ParseLevel.getLevels();

        setSize(1200,800);
        setTitle("Kill Kardashians");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setPanel();
        setVisible(true);







    }

    private void setPanel() throws FileNotFoundException {
        GamePanel panel = new GamePanel();
        panel.setLocation(0, 0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
        this.add(panel);
        addKeyListener(new KeyChecker(panel));
    }



}

