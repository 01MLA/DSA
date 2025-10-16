package dsa_implementations.graph.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Detect Cycle in an Undirected Graph
 * -----------------------------------
 * This class provides implementations to detect cycles in an undirected graph.
 * It supports both BFS and DFS approaches.
 *
 * <p>Graph Representation:</p>
 * The graph is represented as an adjacency list:
 * <pre>
 * ArrayList&lt;ArrayList&lt;Integer&gt;&gt; adj;
 * </pre>
 *
 * <p>Time Complexity:</p>
 * O(V + E), where V = number of vertices, E = number of edges.
 *
 * <p>Space Complexity:</p>
 * O(V) for the visited array + O(V) for BFS queue (if BFS used).
 */
public class DetectCycleInUGraph {

    // ------------------- BFS Approach -------------------

    /**
     * Detects a cycle using BFS.
     *
     * @param vertices Number of vertices in the graph
     * @param adj      Adjacency list
     * @return true if a cycle exists, false otherwise
     */
    public boolean isCycle(int vertices, ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[vertices]; // 0 = unvisited, 1 = visited

        // Handle disconnected components
        for (int node = 0; node < vertices; node++) {
            if (visited[node] == 0) {
                if (bfsDetectCycle(node, adj, visited)) return true;
            }
        }
        return false;
    }

    /**
     * BFS helper to detect cycles in a connected component.
     *
     * @param startNode Starting node for BFS
     * @param adj       Adjacency list
     * @param visited   Array to track visited nodes
     * @return true if a cycle is found, false otherwise
     */
    private boolean bfsDetectCycle(int startNode, ArrayList<ArrayList<Integer>> adj, int[] visited) {
        // Queue stores pairs: [currentNode, parentNode]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startNode, -1}); // root node, no parent
        visited[startNode] = 1;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int current = pair[0];
            int parent = pair[1];

            for (int neighbor : adj.get(current)) {
                if (visited[neighbor] == 0) {
                    // Mark neighbor as visited and enqueue it with current as parent
                    visited[neighbor] = 1;
                    queue.add(new int[]{neighbor, current});
                } else if (neighbor != parent) {
                    // Visited neighbor is not parent → cycle detected
                    // Visited (else of visited[neighbor]==0) neighbor is not the parent → cycle detected. /* the neighbor that is visited, and it's not the parent. In an undirected graph, seeing a visited neighbor other than the parent means there’s an alternate path back to it, indicating a cycle. */
                    return true;
                }
            }
        }

        return false; // No cycle in this component
    }

    // ------------------- DFS Approach -------------------

    /**
     * Recursive DFS helper to detect a cycle from a given node.
     *
     * @param node    Current node
     * @param parent  Node from which we reached current node
     * @param visited Visited array
     * @param adj     Adjacency list
     * @return true if a cycle is detected, false otherwise
     */
    public boolean dfsDetectCycle(int node, int parent, int[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 0) {
                // Recurse on unvisited neighbor
                if (dfsDetectCycle(neighbor, node, visited, adj)) return true;
            } else if (neighbor != parent) {
                // Visited neighbor not parent → cycle detected
                // Visited (else of visited[neighbor]==0) neighbor is not the parent → cycle detected. /* the neighbor that is visited, and it's not the parent. In an undirected graph, seeing a visited neighbor other than the parent means there’s an alternate path back to it, indicating a cycle. */
                return true;
            }
        }

        return false; // No cycle found in this path
    }

    /**
     * Detects a cycle in the entire graph using DFS.
     *
     * @param vertices Number of vertices
     * @param adj      Adjacency list
     * @return true if a cycle exists, false otherwise
     */
    public boolean hasCycle(int vertices, ArrayList<ArrayList<Integer>> adj) {
        int[] visited = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            if (visited[i] == 0) {
                if (dfsDetectCycle(i, -1, visited, adj)) return true;
            }
        }

        return false;
    }
}

// ------------------- Main Class to Test -------------------

class DetectMain {
    public static void main(String[] args) {
        int vertices = 5;

        // Initialize adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(3).add(2);
        adj.get(2).add(3);
        adj.get(2).add(0);
        adj.get(0).add(2);
        // vertex 4 is isolated

        DetectCycleInUGraph graph = new DetectCycleInUGraph();

        // Detect using BFS
        boolean bfsCycle = graph.isCycle(vertices, adj);

        // Detect using DFS
        boolean dfsCycle = graph.hasCycle(vertices, adj);

        System.out.println("Cycle detected (BFS): " + bfsCycle);
        System.out.println("Cycle detected (DFS): " + dfsCycle);
    }
}
