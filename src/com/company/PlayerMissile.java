package com.company;

import javax.swing.*;
import java.awt.*;

public class PlayerMissile extends Missile {

    public PlayerMissile(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        this.side = panel.player.way;
        color = Color.BLUE;
    }

}
