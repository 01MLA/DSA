package dsa_implementations.graph.problems;

/**
 * ReplaceOsWithX
 * ---------------
 * This class provides a solution for the classic “Replace O's with X” problem.
 * <p>
 * Problem Description:
 * --------------------
 * Given a binary matrix (containing 0s and 1s):
 * - 0 represents 'O' (open cell)
 * - 1 represents 'X' (filled cell)
 * <p>
 * Any region of connected 0s that is **completely surrounded** by 1s
 * should be replaced (filled) with a given value `x`.
 * However, 0s that are **connected to the boundary** (edge) of the grid
 * are not replaced, as they are not enclosed.
 * <p>
 * Approach:
 * ----------
 * 1. Perform DFS from all 0s that are located on the boundary (first/last row or column)
 * to mark them as visited — these cannot be replaced.
 * 2. Then, traverse the entire matrix and replace all unvisited 0s with `x`.
 * <p>
 * Time Complexity:  O(N * M)
 * Space Complexity: O(N * M)
 */
public class ReplaceOsWithX {

    /**
     * Performs a Depth-First Search (DFS) from the given cell to mark all
     * connected 0s that are reachable (not enclosed) as visited.
     *
     * @param row     Current row index
     * @param col     Current column index
     * @param visited Matrix marking visited cells
     * @param grid    Input binary grid
     */
    private void dfs(int row, int col, int[][] visited, int[][] grid) {
        visited[row][col] = 1;

        int n = grid.length;
        int m = grid[0].length;

        // 4 possible movement directions (up, right, down, left)
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Explore all valid neighbors recursively
        for (int i = 0; i < 4; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 0 && visited[newRow][newCol] == 0) {
                dfs(newRow, newCol, visited, grid);
            }
        }
    }

    /**
     * Fills all enclosed 0s in the grid with the given value `x`.
     *
     * @param grid 2D binary matrix (0s and 1s)
     * @param x    Value to fill enclosed 0s with (commonly 1 or another marker)
     * @return Updated matrix after replacement
     */
    public int[][] fill(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];

        // Step 1: DFS from all boundary cells containing 0
        // First and last rows
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 0 && visited[0][j] == 0) dfs(0, j, visited, grid);
            if (grid[n - 1][j] == 0 && visited[n - 1][j] == 0) dfs(n - 1, j, visited, grid);
        }

        // First and last columns
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 0 && visited[i][0] == 0) dfs(i, 0, visited, grid);
            if (grid[i][m - 1] == 0 && visited[i][m - 1] == 0) dfs(i, m - 1, visited, grid);
        }

        // Step 2: Replace all unvisited 0s (enclosed regions) with x
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && visited[i][j] == 0) {
                    grid[i][j] = x;
                }
            }
        }

        return grid;
    }

    /**
     * Example test case.
     */
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 0, 1}, {0, 1, 1, 1}};
        System.out.println("Grid of 0s and Xs (1):");
        for (int[] row : grid) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        ReplaceOsWithX solver = new ReplaceOsWithX();
        int[][] result = solver.fill(grid, 1);

        System.out.println("Grid after replacing enclosed 0s with X (1):");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
