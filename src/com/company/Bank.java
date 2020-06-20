package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Bank {
    public static ArrayList<ImageIcon> images;

    public static void getImages(){
        images = new ArrayList<>();
        images.add(new ImageIcon("resources\\obstacleImages\\khloe.png"));
        images.add(new ImageIcon("resources\\obstacleImages\\kylie.png"));
        images.add(new ImageIcon("resources\\obstacleImages\\kim.jpg"));

    }
}
