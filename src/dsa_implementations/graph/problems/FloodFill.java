package dsa_implementations.graph.problems;

/**
 * FloodFill Algorithm Implementation
 * ----------------------------------
 * The Flood Fill algorithm replaces a region of connected cells in a 2D image
 * (represented as a matrix) that share the same initial color with a new color.
 * This implementation uses Depth-First Search (DFS) to propagate the color change
 * to all connected neighboring cells in four directions (up, right, down, left).
 *
 * <p>Example Use Case:</p>
 * Used in image editing software to fill an enclosed region with a new color,
 * similar to the "paint bucket" tool in graphics programs.
 *
 * <p>Time Complexity:</p>
 * O(N × M), where N and M are the number of rows and columns in the image.
 *
 * <p>Space Complexity:</p>
 * O(N × M) in the worst case due to recursive stack usage.
 *
 * @author M.Latif Arfani
 * @version 1.0
 */
public class FloodFill {

    /**
     * Performs the flood fill operation starting from a given pixel (sr, sc).
     *
     * @param sr       The starting row index.
     * @param sc       The starting column index.
     * @param image    The 2D array representing the image.
     * @param newColor The new color to apply to the connected region.
     * @return The updated image after flood fill.
     */
    public int[][] floodFill(int sr, int sc, int[][] image, int newColor) {
        int initColor = image[sr][sc]; // Original color at the start point

        // Directions: Up, Right, Down, Left
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaCol = {0, +1, 0, -1};

        // Start DFS to replace connected cells
        dfs(sr, sc, image, newColor, deltaRow, deltaCol, initColor);
        return image;
    }

    /**
     * Depth-First Search helper function that recursively fills connected cells
     * sharing the same initial color with the new color.
     *
     * @param row      Current cell row index.
     * @param col      Current cell column index.
     * @param image    Original image matrix.
     * @param newColor The new color to apply.
     * @param delRow   Row direction offsets (Up, Right, Down, Left).
     * @param delCol   Column direction offsets (Up, Right, Down, Left).
     * @param iniColor The original color that should be replaced.
     */
    private void dfs(int row, int col, int[][] image, int newColor, int[] delRow, int[] delCol, int iniColor) {
        int n = image.length;
        int m = image[0].length;

        image[row][col] = newColor; // Fill current cell with the new color
        for (int i = 0; i < 4; i++) { // Explore all four neighboring cells
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            // Check bounds and fill only cells matching the initial color
            if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && image[nRow][nCol] == iniColor && image[nRow][nCol] != newColor) {
                // Recursively process neighboring cells
                dfs(nRow, nCol, image, newColor, delRow, delCol, iniColor);
            }
        }
    }

    /**
     * Demonstrates the flood fill algorithm with a sample 2D matrix.
     * The filled matrix is printed to the console.
     */
    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        int[][] image = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0}
        };
        System.out.println("Initial Image:");
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }

        int[][] result = floodFill.floodFill(0, 0, image, 3);
        System.out.println("Resulting Image:");
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
