package com.company;

public class ObstacleMissile extends Missile {

    public ObstacleMissile(int x, int y, int width, int height, GamePanel panel) {
        super(x, y, width, height, panel);
        this.side = (int) Math.signum(panel.player.x-x);
    }
}
