package dsa_implementations.mygraph.adjacency_list_graph;

import java.util.*;

public class Graph {
    ArrayList<GraphNode> nodeList;

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int nodeA, int nodeB) {
        GraphNode first = nodeList.get(nodeA);
        GraphNode second = nodeList.get(nodeB);
        first.neighbors.add(second);
        second.neighbors.add(first);
    }

    public void addDirectedEdge(int nodeA, int nodeB) {
        GraphNode first = nodeList.get(nodeA);
        GraphNode second = nodeList.get(nodeB);
        first.neighbors.add(second);
    }

    public void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            GraphNode currNode = queue.getFirst();
            currNode.isVisited = true;
            System.out.println(currNode.name + " ");

            for (GraphNode neighbor : currNode.neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void bfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) bfsVisit(node);
        }
    }

    public void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            GraphNode currNode = stack.pop();
            currNode.isVisited = true;
            System.out.println(currNode.name + " ");

            for (GraphNode neighbor : currNode.neighbors) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void dfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) dfsVisit(node);
        }
    }

    public void topologicalSort() {
        Stack<GraphNode> stack = new Stack<>();
        for (GraphNode node : nodeList) {
            if (!node.isVisited) topologicalVisit(node, stack);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().name + " ");
        }
    }

    private void topologicalVisit(GraphNode node, Stack<GraphNode> stack) {
        for (GraphNode neighbor : node.neighbors) {
            if (!neighbor.isVisited) topologicalVisit(neighbor, stack);
        }
        node.isVisited = true;
        stack.push(node);
    }


    /**
     * Problems we can solve with topological sort
     */
    // 1. CourseSchedule - leetcode. 207. a person can finish all courses only if there is no cycle.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        // Create adjacency list graph
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        // Queue for nodes with 0 indegree (no prerequisites)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int next : graph.get(course)) { // process other courses
                indegree[next]--;
                if (indegree[next] == 0) queue.add(next);
            }
        }

        return count == numCourses; // if all courses processed → no cycle
    }

}