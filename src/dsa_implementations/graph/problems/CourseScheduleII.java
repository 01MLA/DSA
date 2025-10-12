package dsa_implementations.graph.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Course Schedule II
 * ------------------
 * Given numCourses labeled 0..numCourses-1 and prerequisites[i] = [a,b]
 * meaning to take course a, you must first take course b.
 * Return a valid course order to finish all courses.
 * If impossible (cycle exists), return an empty array.
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // --- Step 1: Build adjacency list graph --- (0-numCourses are the nodes, and each arrayList is, list of neighbors to each)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) graph.get(pre[1]).add(pre[0]); // Add edges: b -> a (b is prerequisite of a)

        // --- Step 2: Prepare visited array for DFS ---
        /* 0 = unvisited, 1 = visiting, 2 = visited */
        int[] visited = new int[numCourses];

        List<Integer> order = new ArrayList<>(); // store topological order
        // --- Step 3: Perform DFS from each unvisited course ---
        for (int course = 0; course < numCourses; course++) {
            if (visited[course] == 0 && dfs(course, graph, visited, order)) {
                return new int[0]; // cycle detected → impossible to finish
            }
        }

        // --- Step 4: Reverse order to get correct topological sort ---
        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    /**
     * DFS helper function
     * -------------------
     * Marks courses as visiting/visited, detects cycles, and records order.
     *
     * @return true if a cycle is detected
     */
    private boolean dfs(int course, List<List<Integer>> graph, int[] visited, List<Integer> order) {
        visited[course] = 1; // mark as visiting
        // Visit all dependent courses
        for (int neighbor : graph.get(course)) {
            if (visited[neighbor] == 1) return true;       // cycle found
            if (visited[neighbor] == 0 && dfs(neighbor, graph, visited, order)) return true;
        }
        visited[course] = 2; // mark as fully visited
        order.add(course);    // add course after visiting all neighbors
        return false;         // no cycle
    }

}

/**
 * Course Schedule II using Node objects
 * --------------------------------------
 * Each course is a node with a list of neighbors (courses that depend on it).
 * Return a valid ordering of courses to finish all courses.
 * If impossible (cycle exists), return an empty array.
 */
class CourseScheduleIIWithNodes {

    // --- Node class representing a course ---
    static class CourseNode {
        int id;                      // course number
        List<CourseNode> neighbors;  // dependent courses

        CourseNode(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // --- Step 1: Create nodes for all courses ---
        CourseNode[] courses = new CourseNode[numCourses];
        for (int i = 0; i < numCourses; i++) courses[i] = new CourseNode(i);

        // --- Step 2: Build graph (add neighbors based on prerequisites) ---
        for (int[] pre : prerequisites) {
            int a = pre[0]; // course depending on b
            int b = pre[1]; // prerequisite course
            courses[b].neighbors.add(courses[a]); // b -> a
        }

        // --- Step 3: DFS setup ---
        int[] visited = new int[numCourses]; // 0=unvisited,1=visiting,2=visited
        List<Integer> order = new ArrayList<>();

        // --- Step 4: DFS on all nodes ---
        for (CourseNode course : courses) {
            if (visited[course.id] == 0 && dfs(course, visited, order)) {
                return new int[0]; // cycle detected
            }
        }

        // --- Step 5: Reverse order for topological sort ---
        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    /**
     * DFS helper
     * -----------
     * Marks nodes visiting/visited, detects cycles, records order.
     *
     * @return true if a cycle is detected
     */
    private boolean dfs(CourseNode node, int[] visited, List<Integer> order) {
        visited[node.id] = 1; // mark visiting

        for (CourseNode neighbor : node.neighbors) {
            if (visited[neighbor.id] == 1) return true; // cycle
            if (visited[neighbor.id] == 0 && dfs(neighbor, visited, order)) return true;
        }

        visited[node.id] = 2; // mark visited
        order.add(node.id);    // add after visiting neighbors
        return false;
    }

}

/**
 * Course Schedule II using Node objects and List
 * -----------------------------------------------
 * Each course is a node with neighbors (dependent courses).
 * Returns a valid course ordering, or empty array if impossible.
 */
class CourseScheduleIIWithListNodes {

    // --- Node class representing a course ---
    static class CourseNode {
        int id;                     // course number
        List<CourseNode> neighbors; // dependent courses

        CourseNode(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // --- Step 1: Create list of CourseNode objects ---
        List<CourseNode> courses = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            courses.add(new CourseNode(i));
        }

        // --- Step 2: Build graph based on prerequisites ---
        for (int[] pre : prerequisites) {
            int a = pre[0]; // course depending on b
            int b = pre[1]; // prerequisite
            courses.get(b).neighbors.add(courses.get(a)); // b -> a
        }

        // --- Step 3: Prepare visited array for DFS ---
        int[] visited = new int[numCourses]; // 0=unvisited,1=visiting,2=visited

        List<Integer> order = new ArrayList<>();
        // --- Step 4: DFS on all nodes ---
        for (CourseNode course : courses) {
            if (visited[course.id] == 0 && dfs(course, visited, order)) {
                return new int[0]; // cycle detected → impossible
            }
        }

        // --- Step 5: Reverse order for topological sort ---
        Collections.reverse(order);
        return order.stream().mapToInt(i -> i).toArray();
    }

    /**
     * DFS helper
     * -----------
     * Marks nodes visiting/visited, detects cycles, records order.
     */
    private boolean dfs(CourseNode node, int[] visited, List<Integer> order) {
        visited[node.id] = 1; // mark as visiting
        for (CourseNode neighbor : node.neighbors) {
            if (visited[neighbor.id] == 1) return true; // cycle detected
            if (visited[neighbor.id] == 0 && dfs(neighbor, visited, order)) return true;
        }
        visited[node.id] = 2;   // mark visited
        order.add(node.id);     // add after visiting neighbors
        return false;
    }

}

class Main {
    public static void main(String[] args) {

        CourseScheduleII cs0 = new CourseScheduleII();
        int[][] prereq0 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(cs0.findOrder(4, prereq0)));
        // Possible output: [0,2,1,3] or [0,1,2,3]

        CourseScheduleIIWithListNodes cs1 = new CourseScheduleIIWithListNodes();
        int[][] prereq1 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(cs1.findOrder(4, prereq1)));
        // Possible output: [0,2,1,3] or [0,1,2,3]

        CourseScheduleIIWithNodes cs2 = new CourseScheduleIIWithNodes();
        int[][] prereq2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(cs2.findOrder(4, prereq2)));
        // Possible output: [0,2,1,3] or [0,1,2,3]

        // ----------------------------- Example With cycle -----------------------------
        CourseScheduleIIWithNodes csCycle = new CourseScheduleIIWithNodes();
        int[][] prereqCycle = {{0, 1}, {1, 2}, {2, 0}}; // 0->1->2->0 forms a cycle
        System.out.println("CourseScheduleIINodeState (cycle): " + Arrays.toString(csCycle.findOrder(3, prereqCycle)));
        // Output: [] because cycle exists

    }
}