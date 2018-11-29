package hw4.puzzle;

import edu.princeton.cs.algs4.Stack;

public class Board implements WorldState {
    int[][] board;
    int size;

    public Board(int[][] tiles) {
        size = tiles.length;
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > size - 1 || j < 0 || j > size - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    public Iterable<WorldState> neighbors() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    return neighborsHelper(i, j);
                }
            }
        }
        return null;
    }

    public Stack<WorldState> neighborsHelper(int i, int j) {
        Stack<WorldState> neighborList = new Stack<>();
        if (i != 0) {
            neighborList.push(new Board(tileMaker(i, j, i - 1, j)));
        }
        if (j != 0) {
            neighborList.push(new Board(tileMaker(i, j, i, j - 1)));
        }
        if (i != board.length - 1) {
            neighborList.push(new Board(tileMaker(i, j, i + 1, j)));
        }
        if (j != board[0].length - 1) {
            neighborList.push(new Board(tileMaker(i, j, i, j + 1)));
        }
        return neighborList;
    }

    public int[][] tileMaker(int zerox, int zeroy, int switchx, int switchy) {
        int[][] newboard = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == zerox && j == zeroy) {
                    newboard[i][j] = board[switchx][switchy];
                } else if (i == switchx && j == switchy) {
                    newboard[i][j] = 0;
                } else {
                    newboard[i][j] = board[i][j];
                }
            }
        }
        return newboard;
    }

    public int hamming() {
        int k = 1;
        int hammingInt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j< size; j++) {
                if (board[i][j] != k) {
                    hammingInt++;
                }
                k++;
            }
        }
        return hammingInt;
    }

    public int manhattan() {
        int manhattanInt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j< size; j++) {
                //System.out.println(board[i][j]+ " " + Math.abs(((board[i][j] - 1)/size)) +" "+i+" "+ Math.abs(((board[i][j] - 1)%size))+" " + j);
                if (board[i][j] == 0) {
                    continue;
                }
                manhattanInt += Math.abs(((board[i][j]-1)/size) - i) + Math.abs(((board[i][j]-1)%size) - j);
            }
        }
        return manhattanInt;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != ((Board) y).tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
