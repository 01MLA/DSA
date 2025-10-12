package problem_solving_patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TopKElements {

    static List<Integer> topKSmallest(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0) return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        
        return new ArrayList<>(maxHeap);
    }

    static List<Integer> topKLargest(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0) return new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 3, 2, 4};
        int k = 3;
        System.out.println(topKLargest(arr, k)); // Output: [2, 1, 3] (order may vary)
    }
}
