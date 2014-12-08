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

import java.awt.Color;

/**
 * A <code>CircleBugRunner</code> of exercise 2 in part2.
 * */
public final class CircleBugRunner
{
    private static final int SIGELEN1 = 6;
    private static final int SIGELEN2 = 3;
    private static final int XLOC1 = 7;
    private static final int YLOC1 = 8;
    private static final int XLOC2 = 6;
    private static final int YLOC2 = 6;

    private CircleBugRunner() {}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(SIGELEN1);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(SIGELEN2);
        world.add(new Location(XLOC1, YLOC1), alice);
        world.add(new Location(XLOC2, YLOC2), bob);
        world.show();
    }
}
