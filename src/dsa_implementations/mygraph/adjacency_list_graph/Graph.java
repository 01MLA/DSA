package dsa_implementations.mygraph.adjacency_list_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

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

}