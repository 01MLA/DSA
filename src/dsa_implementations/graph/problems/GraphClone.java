package dsa_implementations.graph.problems;

import java.util.*;

// ───────────────────────────────────────────────────────────────
// 🧩 Problem: Clone Graph (LeetCode #133)
/*
You are given a reference to a node in a **connected undirected graph**.
Each node contains a value (`val`) and a list of its neighbors.
Your task is to return a **deep copy** (clone) of the graph.

-------------------------------------------------------
Example 1:
Input graph:
   1 -- 2
   |    |
   3 -- 4

Output (any equivalent clone):
   1' -- 2'
   |     |
   3' -- 4'

Every node is copied (new objects) and connections are identical.

-------------------------------------------------------
Example 2:
Input: [] (null)
Output: [] (null)

-------------------------------------------------------
Constraints:
- The number of nodes in the graph is in [0, 100].
- Node values are unique.
- No duplicate edges or self-loops.
-------------------------------------------------------
*/

class Node {
    public int val;               // unique identifier for node
    public List<Node> neighbors;  // list of all connected nodes

    public Node() {
        this.val = 0;
        this.neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        // Only show neighbor values (avoid printing full objects)
        List<Integer> neighborVals = new ArrayList<>();
        for (Node n : neighbors) {
            neighborVals.add(n.val);
        }
        return "Node{" + "val=" + val + ", neighbors=" + neighborVals + '}';
    }

}

class Solution {
    // Keeps track of cloned nodes to avoid infinite loops and duplicates
    private final Map<Node, Node> visited = new HashMap<>();

    /**
     * Clone a connected undirected graph using DFS.
     *
     * @param node the starting node of the graph
     * @return the cloned graph’s starting node
     */
    public Node cloneGraph(Node node) {
        // Step 1: Base case — if input node is null, nothing to clone
        if (node == null) return null;

        // Step 2: If node already cloned, just return its clone
        if (visited.containsKey(node)) return visited.get(node);

        // Step 3: Create new clone node
        Node clone = new Node(node.val);

        // Step 4: Mark current node as cloned
        visited.put(node, clone);

        // Step 5: Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor)); // recursive DFS call
        }

        // Step 6: Return the fully cloned node
        return clone;
    }

    /**
     * Print the entire graph (original or cloned) in a readable format.
     */
    public void printGraph(Node startNode) {
        if (startNode == null) {
            System.out.println("Graph is empty.");
            return;
        }

        Map<Node, Boolean> seen = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(startNode);
        seen.put(startNode, true);

        System.out.println("Graph structure:");
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Print this node and its neighbors
            System.out.print("Node " + current.val + " -> ");
            for (Node neighbor : current.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!seen.containsKey(neighbor)) {
                    seen.put(neighbor, true);
                    queue.offer(neighbor);
                }
            }
            System.out.println();
        }
    }
}

class CloneGraphMain {
    public static void main(String[] args) {
        /*
            Example graph:
                1 -- 2
                |    |
                3 -- 4
        */

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        // Undirected connections
        n1.neighbors.addAll(List.of(n2, n3));
        n2.neighbors.addAll(List.of(n1, n4));
        n3.neighbors.addAll(List.of(n1, n4));
        n4.neighbors.addAll(List.of(n2, n3));

        Solution solution = new Solution();
        Node clonedGraph = solution.cloneGraph(n1);

        System.out.println("Original Graph:");
        solution.printGraph(n1);

        System.out.println("\nCloned Graph:");
        solution.printGraph(clonedGraph);
    }
}
