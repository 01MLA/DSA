package dsa_implementations.mygraph.adjacency_list_graph;


import java.util.ArrayList;

public class GraphNode {
    public String name;
    public int index;
    public boolean isVisited = false;
    public GraphNode parent;

    ArrayList<GraphNode> neighbors = new ArrayList<>();

    public GraphNode(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
