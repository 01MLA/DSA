package dsa_implementations.graph.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Rotten Oranges Problem
 * ----------------------
 * This algorithm determines the minimum time required for all fresh oranges in a grid
 * to become rotten. Each sec, any fresh orange adjacent (up, right, down, left)
 * to a rotten orange becomes rotten.
 *
 * <p>Approach:</p>
 * The problem is solved using Breadth-First Search (BFS). Initially, all rotten
 * oranges are enqueued with time = 0. BFS then spreads the "rotting effect" level by level.
 * DFS version is possible, but less intuitive and more error-prone.
 * BFS is the natural fit because it spreads the rotting in waves (like real infection propagation).
 *
 * <p>Time Complexity:</p>
 * O(N × M), where N and M are the grid dimensions.
 *
 * <p>Space Complexity:</p>
 * O(N × M) due to the auxiliary queue and visited array.
 *
 * <p>Example Use Case:</p>
 * Commonly used in infection spread simulations, matrix BFS problems, and level-order traversal patterns.
 *
 * @author M.Latif Arfani
 * @version 1.1
 */
public class RottenOranges { // todo: must be added to cheat sheet (especially when for dfs we need to find some nodes and do dfs on them)

    /**
     * Helper class representing a cell's position and the time at which it becomes rotten.
     */
    static class Cell {
        int row;
        int col;
        int time;

        public Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    /**
     * Calculates the minimum time required for all fresh oranges to rot.
     *
     * @param grid 2D array where:
     *             <ul>
     *                 <li>0 represents an empty cell</li>
     *                 <li>1 represents a fresh orange</li>
     *                 <li>2 represents a rotten orange</li>
     *             </ul>
     * @return The time required for all oranges to rot, or -1 if impossible.
     */
    public int orangeRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Cell> queue = new LinkedList<>();
        int[][] visited = new int[rows][cols];
        int freshOranges = 0;

        // Initialize queue with all initially rotten oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) freshOranges++;
                if (grid[r][c] == 2) {
                    queue.add(new Cell(r, c, 0)); // Enqueue all initially rotten oranges
                    visited[r][c] = 1; // Mark as visited (visited && already rotten)
                }
            }
        }

        // Directions: Up, Right, Down, Left
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaCol = {0, +1, 0, -1};

        int maxTime = 0;
        int rottenCount = 0;

        // Perform BFS
        while (!queue.isEmpty()) { // till all initial rotten oranges are dequeued
            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;
            int time = current.time;

            maxTime = Math.max(maxTime, time); // till now how many sec(s) did it take to.

            // Explore all 4 neighbors
            for (int i = 0; i < 4; i++) {
                int nextRow = row + deltaRow[i];
                int nextCol = col + deltaCol[i];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && visited[nextRow][nextCol] == 0 && grid[nextRow][nextCol] == 1) {
                    // Make the fresh orange rotten
                    queue.add(new Cell(nextRow, nextCol, time + 1));
                    visited[nextRow][nextCol] = 1; // marked as visited with 1
                    rottenCount++;
                }
            }
        }

        // If not all fresh oranges have turned rotten, return -1
        return (rottenCount != freshOranges) ? -1 : maxTime;
    }

    /**
     * Example usage of the RottenOranges algorithm.
     */
    public static void main(String[] args) {
        RottenOranges rotting = new RottenOranges();
        int[][] grid = {{2, 1, 0, 2}, {1, 0, 1, 2}, {1, 0, 0, 1}};
        int result = rotting.orangeRotting(grid);
        if (result == -1) System.out.println("Not all oranges can become rotten.");
        else System.out.println("All oranges become rotten in: " + result + " sec(s).");
    }
}