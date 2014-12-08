/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 2
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

/**
 * A <code>DancingBugRunner</code> of exercise 2 in part2.
 * */
public final class DancingBugRunner {
    private DancingBugRunner(){}
    private static final int TURNNUM1 = 1;
    private static final int TURNNUM2 = 2;
    private static final int TURNNUM3 = 3;
    private static final int TURNNUM4 = 4;
    private static final int TURNNUM5 = 5;
    private static final int TURNNUM6 = 6;
    private static final int TURNNUM7 = 7;
    private static final int TURNNUM8 = 8;

    private static final int XLOC = 2;
    private static final int YLOC = 3;


    public static void main(String[] args) {
        int[] list = {TURNNUM1, TURNNUM2, TURNNUM3, TURNNUM4, TURNNUM5, TURNNUM6, TURNNUM7, TURNNUM8};
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(list); 
        world.add(new Location(XLOC, YLOC), alice);
        world.show();
    }
}
