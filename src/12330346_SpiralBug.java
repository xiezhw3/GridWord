/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 2
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> of exercise 2 in part2.
 * */
public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    public SpiralBug(int length) {
        this.steps = 0;
        this.sideLength = length;
    }

    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}
