package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int r;
    private int c;
    private int[][] grids;

    private int opens;
    private WeightedQuickUnionUF union;

    private boolean percolation;

    private int[] directionup = {-1,1,0,0};
    private int[] directionleft = {0,0,-1,1};

    private int transfer_index (int row, int col) {
        return row * c +col;
    }

    private boolean qualified (int row , int col) {
        if (row<0 || row>(r-1) || col<0 || col>(c-1)) {
            return false;
        } else {
            return true;
        }
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        } else {
            r = N;
            c = N;
            opens = 0;
            grids = new int[N][N];
            for (int i =0; i<r ; i++) {
                for (int j=0; j<c ; j++) {
                    grids[i][j] = 0;
                }
            }
            union = new WeightedQuickUnionUF(r*c +2 );
            percolation = false;
        }
    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (!qualified(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (!isOpen(row,col)) {
            grids[row][col] = 1;
            opens = opens + 1;
            if (row == 0) {
                union.union(transfer_index(row,col),r*c);
            }
            if (row == r-1) {
                union.union(transfer_index(row,col),r*c+1);
            }
            for (int i = 0; i < 4 ; i++) {
                int neibor_row = row+directionup[i];
                int neibor_col = col+directionleft[i];
                if (qualified(neibor_row,neibor_col)) {
                    if (grids[neibor_row][neibor_col] == 1) {
                        union.union(transfer_index(row, col), transfer_index(neibor_row, neibor_col));
                    }
                }
            }
            if (isFull(row,col) && union.connected(transfer_index(row, col),r*c+1)) {
                percolation = true;
            }

        }
    }       // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (!qualified(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (grids[row][col] == 0) {
            return false;
        } else {
            return true;
        }
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (!qualified(row,col)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else {
            return union.connected(transfer_index(row,col),r*c);
        }
    }  // is the site (row, col) full?
    public int numberOfOpenSites() {
        return opens;
    }          // number of open sites
    public boolean percolates() {
        return percolation;
    }           // does the system percolate?
    public static void main(String[] args) {

    }  // use for unit testing (not required, but keep this here for the autograder)

}
