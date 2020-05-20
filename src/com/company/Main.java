package com.company;

import java.awt.*;
import java.io.FileNotFoundException;

//Basic platformer game developed due to OOP course at IIUwr


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

	    MainFrame frame = new MainFrame();

	    //setting location at the screen
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
                          (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));



    }
}
