public class BubbleGrid {
    private int[][] grid;
    private UnionFind unionGrid;
    private int r;
    private int c;
    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {

        this.grid = grid;
        r = grid.length;
        c = grid[0].length;
        unionGrid = new UnionFind( r*c + 1 );

    }

    private int grid2array(int row, int col){
        return row * c +col;
    }
    private void orthogonallyUnion(int[][] grid,int row, int col){
        for (int i = 0; i < 4; i++){
            if((row + dx[i] >=0 && row + dx[i]<r) &&
                    (col + dy[i] >= 0 && col +dy[i] <c)){
                if (grid[row + dx[i]][col + dy[i]] == 1 ) {
                    unionGrid.union(grid2array(row,col),
                            grid2array(row+dx[i], col+dy[i]));
                }
            }
        }

    }
    private void gridToUnionFind(int[][] grid){
        for (int row = 0; row < r; row++){
            for (int col = 0; col < c; col++){
                if (grid[row][col] == 1){
                    if (row == 0) {
                        unionGrid.union(col,r*c); //所有顶部泡泡和第n+1个点连接
                    } else {
                        orthogonallyUnion(grid,row,col);
                    }
                }
            }
        }
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int[] count = new int[darts.length];
        int[][] gridcopy = new int[r][c];

        for (int i=0; i <r; i++){
            gridcopy[i] = grid[i].clone();
        }

        for(int[] dart:darts){
            gridcopy[dart[0]][dart[1]] = 0;
        }
        gridToUnionFind(gridcopy);
        int connect2ceiling = unionGrid.sizeOf(r*c);

        for (int i = darts.length - 1; i>=0;i--){
            int row = darts[i][0];
            int col = darts[i][1];

            if(grid[row][col] == 0) {
                count[i] = 0;
            } else {
                gridcopy[row][col] = 1;
                if (row == 0) {
                    unionGrid.union(r*c,grid2array(row,col));
                }
                orthogonallyUnion(gridcopy,row,col);
                int cur = unionGrid.sizeOf(r*c);
                if (cur - connect2ceiling == 0){
                    count[i] = 0;
                } else {
                    count[i] = cur - connect2ceiling - 1;
                }
            }
        }
        return count;
    }
}
