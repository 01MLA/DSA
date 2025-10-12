package dsa_implementations.graph.algorithms;


import java.util.*;

// Class representing a weighted graph using an adjacency list
public class DijkstraAlgorithm {

    // Dijkstra's algorithm to find the shortest paths from a single source
    public static void dijkstra(int n, int[][] edges, int source) {
        // Step 1️⃣: Build the graph as an adjacency list (each node pointing to a number of neighbors)
        Map<Integer, List<int[]>> graph = new HashMap<>(); // (node -> List<[neighbor, cost]>)
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[]{v, w});
        }

        // Step 2️⃣: Initialize distance array
        int[] dist = new int[n + 1]; // nodes are 1-indexed
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0; // distance to source is 0

        // Step 3️⃣: Use a min-heap (priority queue) to pick smallest distance first
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0}); // {node, distance}

        // Step 4️⃣: Process nodes until the queue is empty
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currDist = current[1];

            // Skip if we already found a shorter path to this node
            if (currDist > dist[node]) continue;

            // Step 5️⃣: Explore all neighbors of the current node
            if (!graph.containsKey(node)) continue;
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                // Calculate new possible distance
                int newDist = currDist + weight;

                // Step 6️⃣: If found a shorter path, update and push to queue
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }

        // Step 7️⃣: Print results (or handle unreachable nodes)
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("Node " + i + " is unreachable from source " + source);
            else System.out.println("Shortest distance from " + source + " → " + i + " = " + dist[i]);
        }
    }

    // 🧪 Example test
    public static void main(String[] args) {
        // Graph edges: (u, v, w)
        int[][] edges = {{1, 2, 4}, {1, 3, 1}, {3, 2, 2}, {2, 4, 1}, {3, 4, 5}};
        int n = 4;      // number of nodes
        int source = 1; // start node

        dijkstra(n, edges, source);
    }
}

