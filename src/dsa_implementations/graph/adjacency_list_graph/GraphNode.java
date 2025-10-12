package dsa_implementations.graph.adjacency_list_graph;

import java.util.ArrayList;

/**
 * Represents a single vertex in a graph using adjacency list structure.
 */
public class GraphNode {

    public String name;                // Node label (e.g., "A", "B", "C")
    public int index;                  // Position/index in the graph list
    public boolean isVisited = false;  // Marks if node was visited (for BFS/DFS)
    public GraphNode parent;           // Reference to parent node (used in traversals)

    // Stores all directly connected neighboring nodes
    public ArrayList<GraphNode> neighbors = new ArrayList<>();

    // Constructor initializes the node with its name and index
    public GraphNode(String name, int index) {
        this.name = name;
        this.index = index;
    }
}