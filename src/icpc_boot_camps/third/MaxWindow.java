package icpc_boot_camps.third;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxWindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of elements and window size
        int n = sc.nextInt();
        int k = sc.nextInt();

        // Read the array elements
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Compute sliding window maximums
        Integer[] result = maxSlidingWindow(arr, k);

        // Print the result separated by spaces
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]); // while it's not the end element, print without space after.
            if (i < result.length - 1) {
                System.out.print(" ");
            }
        }
    }

    // Function to find max of each k-sized window
    public static Integer[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new Integer[0];
        ArrayList<Integer> result = new ArrayList<>();

        // Slide the window from start to end
        for (int start = 0; start <= nums.length - k; start++) {
            int max = nums[start]; // initialize max for current window

            // Find max in the current window
            for (int end = start + 1; end < start + k; end++) {
                max = Math.max(max, nums[end]);
            }

            // Add max to the result list
            result.add(max);
        }

        // Convert ArrayList to Integer array and return
        return result.toArray(Integer[]::new);
    }
}
