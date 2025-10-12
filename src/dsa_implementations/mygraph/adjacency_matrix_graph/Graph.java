package dsa_implementations.mygraph.adjacency_graph.adjacency_matrix_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Graph implementation using an adjacency matrix.
 * Supports BFS, DFS, Topological Sort, and Single-Source Shortest Path (BFS).
 */
public class Graph {

    ArrayList<GraphNode> nodeList;  // List of vertices
    int[][] adjacencyMatrix;        // Matrix representing edges

    // Constructor: initialize matrix based on node count
    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
    }

    // ---------------------------
    // Edge Creation Methods
    // ---------------------------

    /**
     * Add an undirected edge (i ↔ j)
     */
    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    /**
     * Add a directed edge (i → j)
     */
    public void addDirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
    }

    // ---------------------------
    // Utility: Print Graph
    // ---------------------------

    /**
     * Print adjacency matrix in readable form
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (GraphNode node : nodeList) {
            s.append(node.name).append(" ");
        }
        s.append("\n");

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name).append(": ");
            for (int j : adjacencyMatrix[i]) {
                s.append(j).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // ---------------------------
    // Helper: Get Neighbors
    // ---------------------------

    /**
     * Return all neighbors of a given node
     */
    public ArrayList<GraphNode> getNeighbors(GraphNode node) {
        ArrayList<GraphNode> neighbors = new ArrayList<>();
        int nodeIndex = node.index;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[nodeIndex][i] == 1) {
                neighbors.add(nodeList.get(i));
            }
        }
        return neighbors;
    }

    // ---------------------------
    // Breadth-First Search (BFS)
    // ---------------------------

    /**
     * BFS traversal starting from a given node
     */
    void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            GraphNode current = queue.removeFirst();
            current.isVisited = true;
            System.out.print(current.name + " ");

            for (GraphNode neighbor : getNeighbors(current)) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    /**
     * BFS traversal for all components
     */
    public void bfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) bfsVisit(node);
        }
    }

    // ---------------------------
    // Depth-First Search (DFS)
    // ---------------------------

    /**
     * DFS traversal using stack
     */
    void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            GraphNode current = stack.pop();
            current.isVisited = true;
            System.out.print(current.name + " ");

            for (GraphNode neighbor : getNeighbors(current)) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    /**
     * DFS traversal for all components
     */
    void dfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) dfsVisit(node);
        }
    }

    // ---------------------------
    // Topological Sort (for DAGs)
    // ---------------------------

    /**
     * Recursive DFS helper for topological sort
     */
    void topologicalVisit(GraphNode node, Stack<GraphNode> stack) {
        for (GraphNode neighbor : getNeighbors(node)) {
            if (!neighbor.isVisited) topologicalVisit(neighbor, stack);
        }
        node.isVisited = true;
        stack.push(node);
    }

    /**
     * Perform topological sorting
     */
    void topologicalSort() {
        Stack<GraphNode> stack = new Stack<>();
        for (GraphNode node : nodeList) {
            if (!node.isVisited) topologicalVisit(node, stack);
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().name + " ");
        }
    }

    // ---------------------------
    // Shortest Path (Unweighted Graphs)
    // ---------------------------

    /**
     * Print path from source to a node
     */
    public static void pathPrint(GraphNode node) {
        if (node.parent != null) pathPrint(node.parent);
        System.out.print(node.name + " ");
    }

    /**
     * BFS-based Single Source Shortest Path
     */
    public void BFSForSSSPP(GraphNode startNode) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            GraphNode current = queue.removeFirst();
            current.isVisited = true;

            System.out.print("Printing path for node " + current.name + ": ");
            pathPrint(current);
            System.out.println();

            for (GraphNode neighbor : getNeighbors(current)) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                    neighbor.parent = current;
                }
            }
        }
    }
}
