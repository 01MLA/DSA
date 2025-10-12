package dsa_implementations.graph.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Course Schedule (BFS / Kahn’s Algorithm)
 * ----------------------------------------
 * Determine if all courses can be finished given prerequisites.
 * Returns true if possible, false if cycle exists.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // --- Step 1: Prepare adjacency list graph and indegree array ---
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());

        // --- Step 2: Build graph and indegree counts ---
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph.get(prereq).add(course);
            indegree[course]++;
        }

        // --- Step 3: Add courses with 0 indegree to queue ---
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        // --- Step 4: BFS processing ---
        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completed++;
            for (int next : graph.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.add(next);
            }
        }

        // --- Step 5: Check if all courses completed ---
        return completed == numCourses;
    }
}

/**
 * Course Schedule using Node objects
 * -----------------------------------
 * BFS using array of CourseNode objects with neighbors and indegree.
 */
class CourseScheduleWithNodes {

    static class CourseNode {
        int id;
        List<CourseNode> neighbors;
        int indegree;

        CourseNode(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
            this.indegree = 0;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // --- Step 1: Create nodes ---
        CourseNode[] courses = new CourseNode[numCourses];
        for (int i = 0; i < numCourses; i++) courses[i] = new CourseNode(i);

        // --- Step 2: Build graph and indegree ---
        for (int[] pre : prerequisites) {
            int a = pre[0], b = pre[1];
            courses[b].neighbors.add(courses[a]);
            courses[a].indegree++;
        }

        // --- Step 3: Initialize queue with 0 indegree nodes ---
        Queue<CourseNode> queue = new LinkedList<>();
        for (CourseNode c : courses) {
            if (c.indegree == 0) queue.add(c);
        }

        // --- Step 4: BFS processing ---
        int completed = 0;
        while (!queue.isEmpty()) {
            CourseNode c = queue.poll();
            completed++;
            for (CourseNode neighbor : c.neighbors) {
                neighbor.indegree--;
                if (neighbor.indegree == 0) queue.add(neighbor);
            }
        }

        return completed == numCourses;
    }
}

/**
 * Course Schedule using List of Node objects
 * -------------------------------------------
 * BFS using List<CourseNode> instead of array.
 */
class CourseScheduleWithListNodes {

    static class CourseNode {
        int id;
        List<CourseNode> neighbors;
        int indegree;

        CourseNode(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
            this.indegree = 0;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // --- Step 1: Create list of nodes ---
        List<CourseNode> courses = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) courses.add(new CourseNode(i));

        // --- Step 2: Build graph and indegree ---
        for (int[] pre : prerequisites) {
            int a = pre[0], b = pre[1];
            courses.get(b).neighbors.add(courses.get(a));
            courses.get(a).indegree++;
        }

        // --- Step 3: Initialize queue with 0 indegree nodes ---
        Queue<CourseNode> queue = new LinkedList<>();
        for (CourseNode c : courses) {
            if (c.indegree == 0) queue.add(c);
        }

        // --- Step 4: BFS processing ---
        int completed = 0;
        while (!queue.isEmpty()) {
            CourseNode c = queue.poll();
            completed++;
            for (CourseNode neighbor : c.neighbors) {
                neighbor.indegree--;
                if (neighbor.indegree == 0) queue.add(neighbor);
            }
        }

        return completed == numCourses;
    }
}

class MainClass {
    public static void main(String[] args) {

        // -----------------------------
        // Example 1: No cycle
        // -----------------------------
        int[][] prereq0 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        CourseSchedule cs0 = new CourseSchedule();
        System.out.println(cs0.canFinish(4, prereq0));
        // Output: true

        CourseScheduleWithListNodes cs1 = new CourseScheduleWithListNodes();
        System.out.println(cs1.canFinish(4, prereq0));
        // Output: true

        CourseScheduleWithNodes cs2 = new CourseScheduleWithNodes();
        System.out.println(cs2.canFinish(4, prereq0));
        // Output: true

        // -----------------------------
        // Example 2: With cycle
        // -----------------------------
        int[][] prereqCycle = {{0, 1}, {1, 2}, {2, 0}}; // forms a cycle 0->1->2->0

        CourseSchedule csCycle = new CourseSchedule();
        System.out.println(csCycle.canFinish(3, prereqCycle));
        // Output: false

        CourseScheduleWithListNodes csListCycle = new CourseScheduleWithListNodes();
        System.out.println(csListCycle.canFinish(3, prereqCycle));
        // Output: false

        CourseScheduleWithNodes csNodeCycle = new CourseScheduleWithNodes();
        System.out.println(csNodeCycle.canFinish(3, prereqCycle));
        // Output: false
    }
}
