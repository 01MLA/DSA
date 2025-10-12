package dsa_implementations.mygraph.adjacency_graph.adjacency_matrix_graph;

/**
 * Represents a single vertex in a graph when the graph is stored using an adjacency matrix.
 */
public class GraphNode {

    public String name;                // Node label (e.g., "A", "B", "C")
    public int index;                  // Index used in the adjacency matrix
    public boolean isVisited = false;  // Marks if node was visited (used in traversals)
    public GraphNode parent;           // Used for path tracking or backtracking

    // Constructor: initializes name and index of the node
    public GraphNode(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
