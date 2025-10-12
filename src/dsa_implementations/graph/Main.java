package dsa_implementations.graph;

import dsa_implementations.graph.adjacency_list_graph.Graph;
import dsa_implementations.graph.adjacency_list_graph.GraphNode;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create nodes
        ArrayList<GraphNode> list = new ArrayList<>();
        list.add(new GraphNode("A", 0));
        list.add(new GraphNode("B", 1));
        list.add(new GraphNode("C", 2));
        list.add(new GraphNode("D", 3));
        list.add(new GraphNode("E", 4));
        list.add(new GraphNode("F", 5));

        // Create graph
        Graph g = new Graph(list);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(2, 5);
        g.addUndirectedEdge(2, 1);
        g.addUndirectedEdge(3, 4);
        g.addUndirectedEdge(5, 3);

        g.BFSForSSSPP(list.getFirst());
    }
}
