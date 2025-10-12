package dsa_implementations.graph.weighted_graph;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphNode implements Comparable<GraphNode> {
    public String name;
    public ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();
    public HashMap<GraphNode, Integer> weightMap = new HashMap<>();
    public boolean isVisited = false;
    public GraphNode parent;
    public int distance;
    public int index;

    public GraphNode(String name, int index) {
        this.name = name;
        distance = Integer.MAX_VALUE;
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(GraphNode o) {
        return this.distance - o.distance;
    }


}
