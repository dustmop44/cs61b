package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean targetFound = false;
    private Maze maze;
    private Stack<Integer> depth;
    private vlineage[] lineages;
    private boolean hascycle;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
        lineages = new vlineage[maze.V()];
    }

    private void drawcycle(int w, int v) {
        vlineage lineageww = lineages[w];
        vlineage lineagevv = lineages[v];
        int[] lineagew = lineageww.lineage;
        int[] lineagev = lineagevv.lineage;
        for (int i = 0; i < lineagew.length; i++) {
            System.out.print(lineagew[i] + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < lineagev.length; i++) {
            System.out.print(lineagev[i]+ " " );
        }
        int k = 0;
        int m = 0;
        int n = 0;
        boolean foundmatch = false;
        for (int i = lineagew.length - 1; i > -1; i--) {
            for (int j = lineagev.length - 1; j > -1; j--) {
                if (lineagew[i] == lineagev[j]) {
                    m = j;
                    n = i;
                    foundmatch = true;
                }
                if (foundmatch) {
                    break;
                }
            }
            if (foundmatch) {
                break;
            }
        }
        while (m < lineagev.length - 1) {
            edgeTo[lineagev[m]] = lineagev[m+1];
            m++;
        }
        edgeTo[lineagev[lineagev.length-1]] = lineagew[lineagew.length-1];
        k = lineagew.length-1;
        while (k != n) {
            edgeTo[lineagew[k]] = lineagew[k-1];
            k--;
        }
        System.out.println(" ");
        for (int i = 0; i < edgeTo.length; i++) {
            System.out.println(edgeTo[i] + " " );
        }
        announce();

    }

    private void dfs(vlineage vv) {
        if (hascycle) {
            return;
        }
        marked[vv.v] = true;
        announce();

        for (int w : maze.adj(vv.v)) {
            if (!marked[w]) {
                vlineage ww = new vlineage(w, vv);
                lineages[w] = ww;
                dfs(ww);
            } else if (marked[w] && w != vv.parentv) {
                drawcycle(w, vv.v);
                hascycle = true;
                return;
            }
        }
    }

    private class vlineage{
        private int[] lineage;
        private int v;
        private int parentv;

        public vlineage(int vv) {
            v = vv;
            parentv = 0;
            lineage = new int[1];
            lineage[0] = vv;
        }

        public vlineage(int vv, vlineage parent) {
            v = vv;
            parentv = parent.v;
            lineage = new int[parent.lineage.length + 1];
            int k = 0;
            for (int i : parent.lineage) {
                lineage[k] = i;
                k++;
            }
            lineage[lineage.length-1] = v;
        }
    }


    @Override
    public void solve() {
        vlineage start = new vlineage(s);
        lineages[0] = start;
        dfs(start);
    }

    // Helper methods go here
}

