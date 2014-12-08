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
 * A <code>SpiralBugRunner</code> of exercise 2 in part2.
 * */

public final class ZBugRunner {
    private static final int NUM_OF_BUG = 4;
    private static final int XPOINT = 2;
    private static final int YPOINT = 3;

    private ZBugRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
                ZBug alice = new ZBug(NUM_OF_BUG); 
        world.add(new Location(XPOINT, YPOINT), alice);
        world.show();
    }
}

