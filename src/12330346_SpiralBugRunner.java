/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 2
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.grid.Location;

/**
 * A <code>SpiralBugRunner</code> of exercise 2 in part2.
 * */

public final class SpiralBugRunner {
    private static final int SIDELEN = 3;
    private static final int XLOC = 5;
    private static final int YLOC = 5;
    
    private SpiralBugRunner() {}
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
        SpiralBug sprial = new SpiralBug(SIDELEN);
        world.add(new Location(XLOC, YLOC), sprial);
        world.show();
    }
}
