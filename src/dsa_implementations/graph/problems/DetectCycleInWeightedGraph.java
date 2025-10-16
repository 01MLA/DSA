package dsa_implementations.graph.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents an edge in the weighted graph.
 */
class Edge {
    int to;     // Destination vertex
    int weight; // Weight of the edge

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

/**
 * Detect Cycle in an Undirected Weighted Graph
 * --------------------------------------------
 * This class detects if an undirected weighted graph contains a cycle.
 * Edge weights are stored but not used for cycle detection.
 *
 * <p>Graph Representation:</p>
 * The graph is represented as an adjacency list of weighted edges:
 * <pre>
 * ArrayList&lt;ArrayList&lt;Edge&gt;&gt; adj;
 * </pre>
 *
 * <p>Time Complexity:</p>
 * O(V + E), where V is the number of vertices and E is the number of edges.
 *
 * <p>Space Complexity:</p>
 * O(V) for the visited array + O(V) for the BFS queue.
 */
public class DetectCycleInWeightedGraph {

    /**
     * Checks if the weighted undirected graph contains a cycle.
     *
     * @param vertices Number of vertices in the graph.
     * @param adj      Adjacency list representing the weighted graph.
     * @return true if a cycle exists, false otherwise.
     */
    public boolean isCycle(int vertices, ArrayList<ArrayList<Edge>> adj) {
        int[] visited = new int[vertices]; // 0 = unvisited, 1 = visited

        // Check each connected component
        for (int node = 0; node < vertices; node++) {
            if (visited[node] == 0) { // for each subgraph the no node of it is visited yet
                if (bfsDetectCycle(node, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * BFS-based cycle detection for a weighted undirected graph.
     *
     * @param startNode Starting node for BFS.
     * @param adj       Adjacency list of the graph.
     * @param visited   Array to track visited nodes.
     * @return true if a cycle is detected, false otherwise.
     */
    private boolean bfsDetectCycle(int startNode, ArrayList<ArrayList<Edge>> adj, int[] visited) {
        // Queue stores pairs: [currentNode, parentNode]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startNode, -1}); // start node, no parent
        visited[startNode] = 1;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int current = pair[0];
            int parent = pair[1];

            for (Edge edge : adj.get(current)) {
                int neighbor = edge.to;

                if (visited[neighbor] == 0) {
                    visited[neighbor] = 1;
                    queue.add(new int[]{neighbor, current});
                } else if (neighbor != parent) {
                    // Visited neighbor not parent → cycle detected
                    return true;
                }
            }
        }

        return false; // No cycle in this component
    }

    public static void main(String[] args) {
        // Example usage
        int vertices = 4;
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Add weighted edges
        // Graph: 0-1-2-0 (cycle) and 2-3
        adj.get(0).add(new Edge(1, 5));
        adj.get(1).add(new Edge(0, 5));

        adj.get(1).add(new Edge(2, 3));
        adj.get(2).add(new Edge(1, 3));

        adj.get(2).add(new Edge(0, 2));
        adj.get(0).add(new Edge(2, 2));

        adj.get(2).add(new Edge(3, 4));
        adj.get(3).add(new Edge(2, 4));

        DetectCycleInWeightedGraph graph = new DetectCycleInWeightedGraph();
        boolean hasCycle = graph.isCycle(vertices, adj);

        System.out.println(hasCycle ? "Graph contains a cycle." : "Graph does not contain a cycle.");
    }
}
