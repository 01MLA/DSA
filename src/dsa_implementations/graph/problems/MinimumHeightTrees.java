package dsa_implementations.graph.problems;

import java.util.*;

/*
  🧩 Problem: Minimum Height Trees (MHT) - LeetCode #310
  <p>
  Given a tree (connected acyclic undirected graph) with n nodes labeled 0..n-1
  and edges, find all possible root labels of Minimum Height Trees (MHTs).
  <p>
  - Tree height = max distance from root to any leaf.
  - MHT roots are nodes where height is minimized.
  <p>
  Constraints:
  - 1 <= n <= 2 * 10^4
  - edges.length == n - 1
  - No self-loops or duplicate edges
  <p>
  Example 1:
  Input: n = 4, edges = [[1,0],[1,2],[1,3]]
  Output: [1]
  Explanation: Root at 1 gives height 1.
  <p>
  Example 2:
  Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
  Output: [3,4]
  Explanation: Roots 3 or 4 give min height 2.
 */

/**
 * Technique: Trim Leaves / Find Tree Centers
 * ------------------------------------------
 * - A tree's Minimum Height Tree (MHT) roots are always the center nodes (1 or 2 nodes).
 * - Start with all leaf nodes (degree = 1) and remove them layer by layer.
 * - After removing outer layers iteratively, the remaining nodes are the MHT roots.
 * - Efficient O(n) approach using adjacency list + queue.
 * - Think of it like peeling an onion from the leaves toward the center.
 */

public class MinimumHeightTrees {

    /**
     * Find all roots of Minimum Height Trees
     *
     * @param n     number of nodes
     * @param edges tree edges
     * @return list of MHT root labels
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Base case: single node tree
        if (n == 1) return List.of(0);

        // Step 1: Build adjacency list representation
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new HashSet<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Step 2: Add all leaf nodes (degree = 1) to start trimming from the outer layer
        Queue<Integer> leavesQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) leavesQueue.offer(i);
        }

        // Step 3: Trim leaves iteratively until ≤ 2 nodes remain (the remaining 1/2 nodes are centers)
        int remaining = n;  // number of nodes left to process
        while (remaining > 2) {  // stop when 2 or fewer nodes remain (MHT roots)
            int size = leavesQueue.size();
            remaining -= size;  // remove current leaves from remaining count
            for (int i = 0; i < size; i++) {
                int leaf = leavesQueue.poll();  // process current leaf
                int neighbor = graph.get(leaf).iterator().next();  // leaf's only neighbor
                graph.get(neighbor).remove(leaf);  // remove leaf from neighbor
                if (graph.get(neighbor).size() == 1)  // neighbor becomes new leaf
                    leavesQueue.add(neighbor);
            }
        }

        // Step 4: Remaining nodes are MHT roots
        return new ArrayList<>(leavesQueue);
    }

}

class MinHeightMain {
    public static void main(String[] args) {
        MinimumHeightTrees solver = new MinimumHeightTrees();
        // Example 1
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println("Example 1 MHT roots: " + solver.findMinHeightTrees(4, edges1)); // [1]

        // Example 2
        int[][] edges2 = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println("Example 2 MHT roots: " + solver.findMinHeightTrees(6, edges2)); // [3,4]

        // Edge case: single node
        int[][] edges3 = {};
        System.out.println("Single node tree: " + solver.findMinHeightTrees(1, edges3)); // [0]
    }
}