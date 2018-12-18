package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<AStar> queue;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new MinPQ<>();
    }

    private class AStar implements Comparable<AStar>{
        private int h;
        private int v;

        public AStar(int vv) {
            v = vv;
            h = h(v);
        }

        public int compareTo(AStar ww) {
            return h - ww.h;
        }

    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x = maze.toX(t) - maze.toX(v);
        int y = maze.toY(t) - maze.toY(v);
        return x + y;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(AStar s) {
        marked[s.v] = true;
        announce();
        if (s.v == t) {
            System.out.println("found");
            targetFound = true;
            return;
        }
        if (targetFound) {
            return;
        }
        for (int w : maze.adj(s.v)) {
            if (!marked[w]) {
                edgeTo[w] = s.v;
                queue.insert(new AStar(w));
                }
            }

        while(!queue.isEmpty()) {
            astar(queue.delMin());
            if (targetFound) {
                return;
            }
        }
    }

    @Override
    public void solve() {
        astar(new AStar(s));
    }

}

