package dsa_implementations.graph.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * NearestOne
 * ----------
 * Given a binary matrix (0s and 1s), this class computes for each cell the
 * minimum distance to the nearest cell containing 1.
 * <p>
 * Approach:
 * Uses a Breadth-First Search (BFS) traversal starting from all cells containing 1.
 * Each cell in the queue carries its row, column, and current distance.
 * <p>
 * Time Complexity:  O(N * M)
 * Space Complexity: O(N * M)
 */
public class NearestOne {

    /**
     * Helper class representing a cell in the matrix with its position and distance.
     */
    static class Cell {
        int row;
        int col;
        int distance;

        public Cell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    /**
     * Computes the shortest distance from each cell to the nearest cell containing 1.
     *
     * @param grid 2D binary matrix containing 0s and 1s
     * @return 2D matrix of same size where each element represents the distance
     * to the nearest cell with value 1
     */
    public int[][] nearest(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] visited = new int[rows][cols];
        int[][] distance = new int[rows][cols];

        Queue<Cell> queue = new LinkedList<>();

        // Step 1: Add all cells with 1 to the queue as starting points
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    queue.add(new Cell(row, col, 0));
                    visited[row][col] = 1;
                }
            }
        }

        // Directions for moving up, right, down, left
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Step 2: Perform BFS traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;
            int dist = current.distance;

            distance[row][col] = dist;

            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];

                // Check bounds and unvisited condition
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && visited[newRow][newCol] == 0) {
                    visited[newRow][newCol] = 1;
                    queue.add(new Cell(newRow, newCol, dist + 1));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        System.out.println("Grid:");
        for (int[] row : grid) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        NearestOne solver = new NearestOne();
        int[][] result = solver.nearest(grid);

        System.out.println("Distance to nearest 1 for each cell:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

    }
}
