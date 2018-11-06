package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int[][] Map;
    public WeightedQuickUnionUF UnionSet;
    public int side;
    public int opencount;
    public Boolean percolate;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        side = N;
        Map = new int[N][N];
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j< Map[0].length; j++) {
                Map[i][j] = 0;
            }
        }
        opencount = 0;
        UnionSet = new WeightedQuickUnionUF(N*N + 1);
        percolate = false;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row > side - 1 || col > side - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        opencount++;
        Map[row][col] = 1;
        if (row > 0) {
            if (isOpen(row-1, col)) {
                UnionSet.union(row*side + col, (row-1)*side + col);
            }
        }
        if (row < side - 1) {
            if (isOpen(row + 1, col)) {
                UnionSet.union(row*side + col, (row+1)*side + col);
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                UnionSet.union(row*side + col, (row)*side + col - 1);
            }
        }
        if (col < side - 1) {
            if (isOpen(row, col + 1)) {
                UnionSet.union(row*side + col, (row)*side + col + 1);
            }
        }
        if (row == 0) {
            UnionSet.union(row*side + col, side*side);
        }
        if (row == side - 1) {
            if (UnionSet.connected(row*side + col, side*side)) {
                percolate = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row > side - 1 || col > side - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (Map[row][col] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row > side - 1 || col > side - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return UnionSet.connected(row*side + col, side*side);
    }

    public int numberOfOpenSites() {
        return opencount;
    }

    public boolean percolates() {
        return percolate;
    }

}
