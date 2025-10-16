package dsa_implementations.graph.problems;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n*m matrix, consisting of 0s (water) and 1s (land). find the number islands (groups of lands).
 * land can be connected with others in any of 8 directions.
 */
public class NumberOfIslands {

    public int numOfIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visit = new int[n][m];

        int numberOfIslands = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (visit[row][col] == 0 && grid[row][col] == 1) { // new land -> new island
                    numberOfIslands++;
                    bfs(row, col, visit, grid);
                }
            }
        }
        return numberOfIslands;
    }

    private void bfs(int ro, int co, int[][] visit, int[][] grid) {
        visit[ro][co] = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(ro, co));
        int n = grid.length;
        int m = grid[0].length;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int row = pair.first;
            int col = pair.second;

            // for its neighbors (Check all 8 directions (horizontal, vertical, diagonal))
            for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int nRow = row + delRow;
                    int nCol = col + delCol;
                    // if the counted row and column are in range of grid && not visited && contain 1
                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && grid[nRow][nCol] == 1 && visit[nRow][nCol] == 0) {
                        visit[nRow][nCol] = 1;
                        queue.add(new Pair(nRow, nCol));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int[][] grid = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1}
        };
        System.out.println(numberOfIslands.numOfIslands(grid));
    }
}

class Pair {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

