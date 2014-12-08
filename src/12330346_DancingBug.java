/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 4
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.Bug;
import java.util.Arrays;

/**
 * A <code>DancingBug</code> of exercise 4 in part2.
 * */

public class DancingBug extends Bug {
    private int[] turnNumList;
    private int turnNo;

    public DancingBug(int[] list) {
        this.turnNumList = Arrays.copyOf(list, list.length);
        this.turnNo = 0;
    }

    /**
     * This method make the bug turn with
     * num * 45 degrees
     * 
     * @param num
     *         The num bug should turn
     * */
    private void turnNum(int num) {
        for (int i = 0; i != num; i++) {
            turn();
        }
    } 

    public void act() {
        if (turnNo == turnNumList.length) {
            turnNo = 0;
        }
        turnNum(turnNumList[turnNo]);
        if (canMove()) {
            move();
        }
        turnNo++;
    }
}
