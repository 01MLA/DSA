package dsa_implementations.graph.adjacency_list_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Graph implementation using adjacency list.
 * Supports BFS, DFS, Topological Sort, and SSSP (using BFS).
 */
public class Graph {

    ArrayList<GraphNode> nodeList;  // List of all nodes (vertices)

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }

    // ---------------------------
    // Edge Creation Methods
    // ---------------------------

    /**
     * Add an undirected edge between two nodes
     */
    public void addUndirectedEdge(int i, int j) {
        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);
    }

    /**
     * Add a directed edge from node i to node j
     */
    public void addDirectedEdge(int i, int j) {
        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbors.add(second);
    }

    // ---------------------------
    // Utility
    // ---------------------------

    /**
     * Returns string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GraphNode node : nodeList) {
            s.append(node.name).append(": ");
            for (int j = 0; j < node.neighbors.size(); j++) {
                s.append(node.neighbors.get(j).name);
                if (j < node.neighbors.size() - 1) s.append(" -> ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // ---------------------------
    // Breadth-First Search (BFS)
    // ---------------------------

    /**
     * BFS from a starting node (single component)
     */
    public void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            GraphNode current = queue.removeFirst();
            current.isVisited = true;
            System.out.print(current.name + " ");

            for (GraphNode neighbor : current.neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    /**
     * BFS for entire graph, covers all components even if disconnected
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
     * DFS using stack
     */
    public void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode current = stack.pop();
            current.isVisited = true;
            System.out.print(current.name + " ");

            for (GraphNode neighbor : current.neighbors) {
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
    public void dfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) dfsVisit(node);
        }
    }

    // ---------------------------
    // Topological Sort (DAG only)
    // ---------------------------

    /**
     * Recursive DFS helper for topological sort
     */
    private void topologicalVisit(GraphNode node, Stack<GraphNode> stack) {
        for (GraphNode neighbor : node.neighbors) {
            if (!neighbor.isVisited) topologicalVisit(neighbor, stack);
        }
        node.isVisited = true;
        stack.push(node);
    }

    /**
     * Performs topological sort.
     * <p>
     * Topological sorting is used for Directed Acyclic Graphs (DAGs) — graphs where:
     * All edges go in one direction, and
     * There are no cycles.
     * </p>
     */
    public void topologicalSort() {
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
     * Prints path from source to given node
     */
    public static void pathPrint(GraphNode node) {
        if (node.parent != null) pathPrint(node.parent);
        System.out.print(node.name + " ");
    }

    /**
     * BFS for Single Source Shortest Path Problem (SSSPP)
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

            for (GraphNode neighbor : current.neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                    neighbor.parent = current;
                }
            }
        }
    }

}