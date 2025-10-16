package dsa_implementations.graph.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * NumberOfEnclaves
 * ----------------
 * This class provides a BFS-based solution to find the number of land cells (1s)
 * in a binary grid that are not connected to the boundary.
 * <p>
 * A land cell is considered an "enclave" if it cannot reach any boundary cell
 * by moving in the four cardinal directions (up, down, left, right).
 * <p>
 * Approach:
 * 1. Start BFS from all land cells on the boundary and mark them as visited.
 * 2. These represent land cells that can "escape" to the boundary.
 * 3. After BFS, count all remaining unvisited land cells — they are enclaves.
 * <p>
 * Time Complexity:  O(N * M)
 * Space Complexity: O(N * M)
 */
public class NumberOfEnclaves { // todo: simply uses what it needed from bBFS algorithms

    /**
     * A simple pair structure to store grid coordinates (row, column).
     */
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Counts the number of enclave land cells in the given grid.
     *
     * @param grid The 2D matrix of 0s (water) and 1s (land)
     * @return The number of enclaved land cells
     */
    public int countEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];

        Queue<Cell> queue = new LinkedList<>();

        // Step 1: Add all boundary land cells to the BFS queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean isOnBorder = (i == 0 || j == 0 || i == n - 1 || j == m - 1);
                if (isOnBorder && grid[i][j] == 1) {
                    queue.add(new Cell(i, j));
                    visited[i][j] = 1;
                }
            }
        }

        // Directions: Up, Right, Down, Left
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Step 2: BFS to mark all reachable land cells from the boundary (those that we can reach border from)
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;

            for (int i = 0; i < 4; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && visited[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
                    queue.add(new Cell(newRow, newCol));
                    visited[newRow][newCol] = 1;
                }
            }
        }

        // Step 3: Count unvisited land cells (enclaves)
        int enclaveCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    enclaveCount++;
                }
            }
        }

        return enclaveCount;
    }

    /**
     * Example usage of the NumberOfEnclaves algorithm.
     */
    public static void main(String[] args) {
        NumberOfEnclaves solver = new NumberOfEnclaves();
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        System.out.println("Grid: ");
        for (int[] arr : grid) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
        }

        int result = solver.countEnclaves(grid);
        System.out.println("Number of enclaves: " + result);

    }
}
