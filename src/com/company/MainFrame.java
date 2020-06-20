package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


public class MainFrame extends JFrame {


    public MainFrame() throws FileNotFoundException {

        Factory.getLevels();
        Bank.getImages();

        setSize(1200,800);
        setTitle("Kill Kardashians");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        setPanel();

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

