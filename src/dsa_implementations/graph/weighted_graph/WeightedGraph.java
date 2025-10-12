package dsa_implementations.graph.weighted_graph;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Weighted Graph supporting Dijkstra & Bellman-Ford algorithms.
 * Dijkstra → shortest paths of graph (no negative weights)
 * Bellman-Ford → handles negative weights & detects negative cycles
 */
public class WeightedGraph {
    private ArrayList<GraphNode> nodeList; // the representation of a graph using an adjacent list

    public WeightedGraph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * Connects two nodes with a directed weighted edge
     */
    public void addWeightedEdge(int i, int j, int weight) {
        GraphNode from = nodeList.get(i);
        GraphNode to = nodeList.get(j);
        from.neighbors.add(to); // add 'to' as a neighbor of the from.
        from.weightMap.put(to, weight);
    }

    /**
     * Dijkstra’s Algorithm – finds shortest path from a single source (no negative weights)
     */
    public void dijkstra(GraphNode startNode) {
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();
        pq.addAll(nodeList);

        startNode.distance = 0;
        while (!pq.isEmpty()) {
            GraphNode current = pq.remove();
            for (GraphNode neighbor : current.neighbors) {
                if (pq.contains(neighbor)) {
                    int newDist = current.distance + current.weightMap.get(neighbor);
                    if (newDist < neighbor.distance) {
                        neighbor.distance = newDist;
                        neighbor.parent = current;
                        pq.remove(neighbor); // update priority
                        pq.add(neighbor);
                    }
                }
            }
        }

        // Print shortest paths
        for (GraphNode node : nodeList) {
            System.out.print("Node " + node + ", distance: " + node.distance + ", Path: ");
            printPath(node);
            System.out.println();
        }
    }

    /**
     * Bellman-Ford Algorithm – handles negative weights & detects negative cycles
     */
    public void bellmanFord(GraphNode source) {
        source.distance = 0;

        // Relax all edges (V-1 times)
        for (int i = 0; i < nodeList.size() - 1; i++) {
            for (GraphNode current : nodeList) {
                for (GraphNode neighbor : current.neighbors) {
                    int newDist = current.distance + current.weightMap.get(neighbor);
                    if (newDist < neighbor.distance) {
                        neighbor.distance = newDist;
                        neighbor.parent = current;
                    }
                }
            }
        }

        // One more pass to detect negative cycle
        System.out.println("Checking for negative cycle...");
        for (GraphNode current : nodeList) {
            for (GraphNode neighbor : current.neighbors) {
                if (neighbor.distance > current.distance + current.weightMap.get(neighbor)) {
                    System.out.println("⚠ Negative cycle detected involving: " + neighbor.name);
                    return;
                }
            }
        }

        // Print shortest paths
        System.out.println("No negative cycle found.");
        for (GraphNode node : nodeList) {
            System.out.print("Node " + node + ", distance: " + node.distance + ", Path: ");
            printPath(node);
            System.out.println();
        }
    }

    /**
     * Utility: prints path from source to given node
     */
    private static void printPath(GraphNode node) {
        if (node.parent != null) printPath(node.parent);
        System.out.print(node.name + " ");
    }

    /**
     * Driver code for demo
     */
    public static void main(String[] args) {
        // Create graph nodes
        ArrayList<GraphNode> list = new ArrayList<>();
        list.add(new GraphNode("A", 0));
        list.add(new GraphNode("B", 1));
        list.add(new GraphNode("C", 2));
        list.add(new GraphNode("D", 3));
        list.add(new GraphNode("E", 4));
        list.add(new GraphNode("F", 5));

        WeightedGraph graph = getWeightedGraph(list);

        // Run Bellman-Ford from A
        System.out.println("Running Bellman-Ford from A:");
        graph.bellmanFord(list.getFirst());
    }

    @NotNull
    private static WeightedGraph getWeightedGraph(ArrayList<GraphNode> list) {
        WeightedGraph graph = new WeightedGraph(list);

        // Add edges (including a negative cycle)
        graph.addWeightedEdge(0, 1, 4);   // A -> B
        graph.addWeightedEdge(0, 2, 2);   // A -> C
        graph.addWeightedEdge(1, 3, 3);   // B -> D
        graph.addWeightedEdge(2, 3, 2);   // C -> D
        graph.addWeightedEdge(3, 4, 2);   // D -> E
        graph.addWeightedEdge(4, 5, 3);   // E -> F

        // Negative cycle: B → C → D → B
        graph.addWeightedEdge(1, 2, 2);
        graph.addWeightedEdge(2, 3, -4);
        graph.addWeightedEdge(3, 1, 1);
        return graph;
    }
}
