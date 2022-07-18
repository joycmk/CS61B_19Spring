package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;
import static huglife.HugLifeUtils.randomInt;

public class Clorus extends Creature {

    private int r;

    private int g;

    private int b;

    public Clorus (double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }


    @Override
    public void move() {
        energy = Math.max(0,energy -0.03);
    }

    @Override
    public void attack(Creature c) {
        energy = c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = 0.5 * energy;
        Clorus baby = new Clorus(energy);
        baby.color();
        return baby;
    }

    @Override
    public void stay() {
        energy = Math.max(0,energy -0.01);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        boolean anyPlip = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction direction : neighbors.keySet()) {
            if (neighbors.get(direction).name() == "empty") {
                emptyNeighbors.addFirst(direction);
            } else if (neighbors.get(direction).name() == "plip") {
                anyPlip = true;
                plipNeighbors.addFirst(direction);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip == true) {
            return new Action(Action.ActionType.ATTACK,randomEntry(plipNeighbors));
        }
        // Rule 3
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }


        // Rule 4
        return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r,g,b);
    }
}
