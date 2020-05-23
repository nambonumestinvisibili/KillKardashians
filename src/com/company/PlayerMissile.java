package com.company;

public class PlayerMissile extends Missile {

    public PlayerMissile(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        this.side = panel.player.way;
    }

}
