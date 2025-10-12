package icpc_boot_camps.third;

import java.util.Scanner;

public class MazePathValidator {
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, up, right, left

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int startRow = sc.nextInt();
        int startCol = sc.nextInt();

        int destRow = sc.nextInt();
        int destCol = sc.nextInt();

        int[][] maze = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        boolean[][] visited = new boolean[m][n];
        boolean canReach = dfs(maze, startRow, startCol, destRow, destCol, visited);

        System.out.println(canReach ? "true" : "false");
    }

    static boolean dfs(int[][] maze, int row, int col, int destRow, int destCol, boolean[][] visited) {
        if (visited[row][col]) return false;
        if (row == destRow && col == destCol) return true;

        visited[row][col] = true;
        int m = maze.length;
        int n = maze[0].length;

        for (int[] dir : directions) {
            int r = row;
            int c = col;

            // Roll in this direction until hitting a wall or boundary
            while (r + dir[0] >= 0 && r + dir[0] < m && c + dir[1] >= 0 && c + dir[1] < n && maze[r + dir[0]][c + dir[1]] == 0) {
                r += dir[0];
                c += dir[1];
            }

            if (dfs(maze, r, c, destRow, destCol, visited)) {
                return true;
            }
        }

        return false;
    }
}
