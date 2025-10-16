## 🧭 Graphs

* **Cycle in graph:**
  If starting from a node you can return to it via edges → **cyclic**, otherwise → **acyclic**.

* **Path:**
  A sequence of connected nodes without visiting any node twice.

* **Degree of a node:**
  Number of connected neighbors.

    * **Total degree of graph:** `2 * numberOfEdges`
    * **In-degree:** number of incoming edges
    * **Out-degree:** number of outgoing edges

* **Graph representation:**

    * **Adjacency List:** each node stores a list of its neighbors
    * **Adjacency Matrix:** 2D array where `matrix[i][j] = 1` if an edge exists

---

### 🔹 Why nodes are stored in a matrix or list

* Graphs consist of nodes (vertices) and edges (connections).
* We store nodes in a matrix or adjacency list to efficiently represent **connections**.
* **Adjacency List:** memory-efficient for sparse graphs; each node keeps a list of neighbors.
* **Adjacency Matrix:** allows O(1) edge lookup; useful for dense graphs.
* These structures make DFS/BFS and other algorithms easier to implement.
* Enables quick determination of **reachability, paths, connected components, and cycles**.

**In short:**

> Nodes are stored in lists or matrices to capture connections for efficient traversal and algorithm implementation.

---

##### ✅ 1️⃣ Unweighted Undirected Graph (Adjacency List)

```java
import java.util.ArrayList;

public class UnweightedListGraph {
    public static void main(String[] args) {
        int n = 5; // number of nodes
        int[][] edges = {{0,1},{0,4},{1,2},{1,3},{1,4},{2,3}};

        // Create adjacency list
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());

        // Add edges (undirected)
        for(int[] e: edges){
            int u=e[0], v=e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Print adjacency list
        for(int i=0;i<n;i++)
            System.out.println("Node "+i+" -> "+graph.get(i));
    }
}
```

------

##### ✅ 2️⃣ Unweighted Undirected Graph (Adjacency Matrix)

```java
public class UnweightedMatrixGraph {
    public static void main(String[] args) {
        int n = 5; // number of nodes
        int[][] edges = {{0,1},{0,4},{1,2},{1,3},{1,4},{2,3}};
        int[][] matrix = new int[n][n]; // initialize n x n matrix

        // Add edges (undirected)
        for(int[] e: edges){
            int u=e[0], v=e[1];
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        // Print adjacency matrix
        for(int i=0;i<n;i++){
            System.out.print("Node "+i+" -> ");
            for(int j=0;j<n;j++) System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }
}
```

------

##### ✅ 3️⃣ Weighted Undirected Graph (Adjacency List)

```java
import java.util.*;

class WeightedListGraph {
    static class Edge {
        int to, weight;
        Edge(int to, int weight){ this.to=to; this.weight=weight; }

        @Override
        public String toString(){ return "(" + to + ", w=" + weight + ")"; }
    }

    public static void main(String[] args){
        int n = 4; // number of nodes
        int[][] edges = {{0,1,5},{0,2,3},{1,2,2},{2,3,7}};

        // Create adjacency list
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());

        // Add weighted edges (undirected)
        for(int[] e: edges){
            int u=e[0], v=e[1], w=e[2];
            graph.get(u).add(new Edge(v,w));
            graph.get(v).add(new Edge(u,w));
        }

        // Print adjacency list with weights
        for(int i=0;i<n;i++)
            System.out.println("Node "+i+" -> "+graph.get(i));
    }
}
```

------

##### ✅ 4️⃣ Weighted Undirected Graph (Adjacency Matrix)

```java
public class WeightedMatrixGraph {
    public static void main(String[] args){
        int n = 4; // number of nodes
        int[][] edges = {{0,1,5},{0,2,3},{1,2,2},{2,3,7}};
        int[][] matrix = new int[n][n]; // initialize n x n matrix

        // Add weighted edges (undirected)
        for(int[] e: edges){
            int u=e[0], v=e[1], w=e[2];
            matrix[u][v] = w;
            matrix[v][u] = w;
        }

        // Print adjacency matrix with weights
        for(int i=0;i<n;i++){
            System.out.print("Node "+i+" -> ");
            for(int j=0;j<n;j++) System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }
}
```

------

✅ **Notes on comments**:

- Each code block shows the **graph type** (weighted/unweighted, list/matrix).
- **Edge addition** is clearly labeled for **undirected graphs**.
- **Printing** demonstrates the structure of adjacency list/matrix.
- Comments are **short, clear, and descriptive** without overexplaining.

------

### 🌐 Connected and Disconnected Graphs

* **Connected graph:** every node can reach every other node (directly or indirectly).
* **Disconnected graph:** two or more **connected components** — nodes in one component cannot reach nodes in another.

**Example Illustration:**

**Connected Graph**

```
0 -- 1 -- 2
 \        /
   \----/
```

**Disconnected Graph**

```
0 -- 1      2 -- 3
```

Components: `{0,1}` and `{2,3}`

---

##### ✅ Example: Find Connected Components

```java
import java.util.*;

public class ConnectedComponents {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}, {4, 5}};

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                System.out.print("Component " + components + ": ");
                dfs(i, graph, visited);
                System.out.println();
            }
        }

        System.out.println("Total Connected Components = " + components);
    }

    private static void dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : graph.get(node))
            if (!visited[neighbor]) dfs(neighbor, graph, visited);
    }
}
```

**Output:**

```
Component 1: 0 1 2
Component 2: 3 4 5
Total Connected Components = 2
```

---

### 🧠 **Graph Algorithm Quick Selection Guide **(put in cheat sheet)

 🪄 **Summary Tip:**
 DFS → explore deeply
 BFS → explore broadly
 Dijkstra/Bellman-Ford → find cheapest route
 Kruskal/Prim → connect everything minimally
 Topo Sort → order dependencies
 Union-Find → check or connect groups efficiently

> Use this as a mental map to choose the right algorithm type:

**1️⃣ CONNECTEDNESS / GROUPS / COMPONENTS**

- Keywords: “connected”, “island”, “cluster”, “component”, “subgraph”.
- Use: **DFS** or **BFS**
   • DFS → go deep, explore fully, good for recursion-based problems.
   • BFS → explore level-by-level, good for shortest unweighted path or layering.
- Examples: *Number of Islands*, *Clone Graph*, *Connected Components*.

**2️⃣ PATHS / SHORTEST PATH / DISTANCE / COST**

- Keywords: “shortest path”, “minimum distance”, “minimum cost”.
- Choose algorithm by edge type:
   • **Dijkstra** → weighted graph, non-negative edges.
   • **Bellman-Ford** → weighted graph, allows negative edges.
   • **Floyd-Warshall** → all-pairs shortest paths, smaller graphs.
   • **BFS** → unweighted graph (each edge = 1 unit).
- Examples: *Dijkstra’s Shortest Path*, *Network Delay Time*.

**3️⃣ ORDER / DEPENDENCY / SEQUENCE**

- Keywords: “order”, “prerequisite”, “build sequence”.
- Use: **Topological Sort**
   • DFS version (postorder stack)
   • BFS version (Kahn’s algorithm)
- Examples: *Course Schedule*, *Build Order problems*.

**4️⃣ CYCLE DETECTION**

- Keywords: “cycle”, “loop”, “circular dependency”.
- Use:
   • **DFS** (directed graphs)
   • **Union-Find / Disjoint Set** (undirected graphs)
- Examples: *Detect Cycle in Graph*, *Redundant Connection*.

**5️⃣ MINIMUM SPANNING TREE (MST)**

- Keywords: “connect all nodes with minimum cost”.
- Use:
   • **Kruskal** → edge-based (sort + union-find)
   • **Prim** → node-based (priority queue)
- Examples: *Minimum Cost to Connect All Points*, *Network Connection Cost*.

**6️⃣ LEVEL / STEPS / LAYERS**

- Keywords: “minimum steps”, “levels”, “spread”, “shortest transformation”.
- Use: **BFS** (since edges are uniform weight)
- Examples: *Word Ladder*, *Rotten Oranges*.

**7️⃣ CENTER / ROOT / TRIMMING PROBLEMS**

- Keywords: “minimum height”, “center node”, “balance point”.
- Use: **Topological trimming** (remove leaves iteratively)
- Example: *Minimum Height Trees (MHT)*.

-----

### 🚀 Graph Traversal Techniques

Traversal = systematically visiting all nodes (used for paths, components, shortest routes, cycle detection).

| Technique | Approach                    | Data Structure    | Nature    | Common Use                                       |
|-----------|-----------------------------|-------------------|-----------|--------------------------------------------------|
| **DFS**   | Go deep before backtracking | Stack / Recursion | Recursive | Detect cycles, components, topological sort      |
| **BFS**   | Visit level by level        | Queue             | Iterative | Shortest path (unweighted), levels, connectivity |

---

#### ✅ DFS (Depth-First Search)

**Preconfigurations:**

* Visited array → track visited nodes
* Algorithm → **recursive** (system call stack) or **iterative** (manual Stack if not recursive)

**Steps:**

1. Start from any node, mark visited
2. Visit all unvisited neighbors recursively or push to a stack
3. Backtrack when no neighbors remain

```java
import java.util.ArrayList;

class Solution {

    // Recursive DFS
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> result) {
        visited[node] = true;         // mark current node visited
        result.add(node);             // process node

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adj, result); // visit unvisited neighbors
            }
        }
    }

    // DFS traversal of graph
    public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[v];   // visited array
        ArrayList<Integer> result = new ArrayList<>();
        dfs(0, visited, adj, result);        // start DFS from node 0
        return result;
    }
}
```

```java
import java.util.*;

class Solution {

    // Iterative DFS
    public ArrayList<Integer> dfsOfGraphIterative(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[v];          // track visited nodes
        ArrayList<Integer> result = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();        // stack to simulate recursion
        stack.push(0);                               // start from node 0
        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                visited[node] = true;
                result.add(node);                    // process node

                // Push neighbors in reverse order to maintain similar traversal as recursive DFS
                List<Integer> neighbors = adj.get(node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }
}
```

**Output:**

```
DFS Traversal: 0 1 3 4 2
```

---

#### ✅ BFS (Breadth-First Search)

**Preconfigurations:**

* Queue → nodes waiting to be visited
* Visited array → prevent revisiting

**Steps:**

1. Add start node to queue, mark visited
2. While queue not empty:

    * Remove front node, process it
    * Add all unvisited neighbors to queue, mark visited

```java
import interviewProblems.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public ArrayLis<Integer> bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> bfsResult = new ArrayList<>();
        int[] visited = new int[v];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsResult.add(node);
            for (int neighbor : adj.get(node)) {
                if (visited[neighbor] == 0) {
                    visited[neighbor] = 1;
                    queue.add(neighbor);
                }
            }
        }
        return bfsResult;
    }
}
```

**Output:**

```
BFS Traversal: 0 1 2 3 4
```

---

### 🧠 Key Takeaways

* **DFS:** deep first, uses recursion or stack, good for cycles/components/topological sort
* **BFS:** level by level, uses queue, good for shortest path and levels
* Traversals apply to **directed, undirected, weighted, or unweighted graphs**

---

### 🔹 Traversal Levels

* Any node can be chosen as the starting point.
* **Level 0:** starting node
* **Level 1:** directly connected neighbors
* **Level 2:** neighbors two edges away
* Further levels continue similarly

> Each level represents the **distance from the starting node**.

---
