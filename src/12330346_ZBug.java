/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 3
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> of exercise 3 in part2.
 * */

public class ZBug extends Bug {
    private int steps;
    private int sideLength;
    private int sideNum;

    public ZBug(int length) {
        setDirection(Location.EAST);
        this.steps = 0;
        this.sideLength = length;
        this.sideNum = 0;
    }

    public void act() {
        if (steps < sideLength) {
            if (canMove()) {
                steps++;
                move();
            }
        } else{
            sideNum++;
            if (sideNum == 1) {
                setDirection(Location.SOUTHWEST);
                steps = 0;
            }
            if (sideNum == 2) {
                setDirection(Location.EAST);
                steps = 0;
            }
        }
    }
}
