/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer odf the exercise 2
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> of exercise 1 in part2.
 * */
public class CircleBug extends Bug{
    private int steps;
    private int sideLength;
    
    // The length of the square
    public CircleBug(int length) {
        this.steps = 0;
        this.sideLength = length;
    }

    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}
