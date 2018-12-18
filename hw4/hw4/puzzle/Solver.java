package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Iterator;

public class Solver {
    private int moves = 0;
    private MinPQ<WorldStateNode> trials;
    private Stack<WorldState> SOLUTION = new Stack<>();
    private HashSet<WorldStateNode> DQList = new HashSet<>();

    private class WorldStateNode implements Comparable<WorldStateNode>{
        private WorldState state;
        private WorldStateNode previous;
        private int moves;
        private int estimatedDistance;

        public WorldStateNode(WorldState STATE, WorldStateNode PREVIOUS) {
            state = STATE;
            previous = PREVIOUS;
            estimatedDistance = state.estimatedDistanceToGoal();
        }

        public int compareTo(WorldStateNode T) {
            return this.moves + this.estimatedDistance - T.moves - T.estimatedDistance;
        }
    }

    public Solver(WorldState initial) {
        trials = new MinPQ<>();
        WorldStateNode INITIAL = new WorldStateNode(initial, null);
        INITIAL.moves = 0;
        SolverHelper(INITIAL);
    }

    private void DQBefore(WorldStateNode thing, Iterator<WorldStateNode> DQLIst) {
        if (!DQLIst.hasNext()) {
            trials.insert(thing);
        } else {
            if (DQLIst.next().state.equals(thing.state)) {
                return;
            } else {
                DQBefore(thing, DQLIst);
            }
        }
    }

    private void SolverHelper(WorldStateNode thing) {
        if (thing.state.isGoal()) {
            makesolution(thing);
        } else {
            for (WorldState i : thing.state.neighbors()) {
                if (thing.previous == null) {
                    WorldStateNode insert = new WorldStateNode(i, thing);
                    insert.moves = thing.moves + 1;
                    trials.insert(insert);
                } else if (!i.equals(thing.previous.state)) {
                    WorldStateNode insert = new WorldStateNode(i, thing);
                    insert.moves = thing.moves + 1;
                    DQBefore(insert, DQList.iterator());
                }
            }
            WorldStateNode next = trials.delMin();
            DQList.add(next);
            SolverHelper(next);
        }
    }

    private void makesolution(WorldStateNode thing) {
        moves = thing.moves;
        while (thing.previous != null) {
            SOLUTION.push(thing.state);
            thing = thing.previous;
        }
        SOLUTION.push(thing.state);
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return SOLUTION;
    }
}
