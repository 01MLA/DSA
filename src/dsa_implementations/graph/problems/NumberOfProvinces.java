package dsa_implementations.graph.problems;

/**
 * LeetCode 547 – Number of Provinces
 * <p>
 * Problem:
 * Given n cities, some are directly or indirectly connected.
 * A province is a group of connected cities with no connections outside.
 * Input: n x n matrix isConnected[i][j] = 1 if city i and j are directly connected.
 * Output: Total number of provinces.
 */
public class NumberOfProvinces {

    /**
     * Returns the number of provinces (connected components) in the graph.
     */
    public static int numOfProvinces(int[][] cities) {
        int n = cities.length;
        boolean[] visited = new boolean[n];  // marks visited cities
        int provinces = 0;                   // count of connected components
        for (int city = 0; city < n; city++) {
            if (!visited[city]) {               // start DFS if city not visited
                // started one province (every time started a not visited city -> we get,
                // new province is tarted to be traversed) -> number of starts = number of provinces
                provinces++;
                // to start with a city, it will visit all cities connected with it. (a complete province is marked as visited)
                dfs(city, cities, visited);
            }
        }

        return provinces;
    }

    /**
     * Depth-First Search to explore all cities connected to the given city.
     */
    private static void dfs(int city, int[][] cities, boolean[] visited) {
        visited[city] = true;  // mark current city as visited
        for (int neighbor = 0; neighbor < cities.length; neighbor++) {
            if (cities[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, cities, visited);  // recursively visit connected city
            }
        }
    }

    public static void main(String[] args) {
        // Example 1: Two provinces
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("Provinces: " + NumberOfProvinces.numOfProvinces(isConnected1)); // Output: 2

        // Example 2: Three separate provinces
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Provinces: " + NumberOfProvinces.numOfProvinces(isConnected2)); // Output: 3
    }

}
