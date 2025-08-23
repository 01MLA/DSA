package interviewProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Array {

    /* Find the missing number in an integer array from 1 to n */
    static int findMissing(int[] nums, int n) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (n * (n + 1) / 2) - sum;
    }

    /* Write a program to find all pairs of integers whose sum is equal to a given number. */
    static List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                List list = new ArrayList();
                list.add(map.get(complement));
                list.add(i);
                result.add(list);
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    /* Write a program to find a value of integer in given numbers. */
    static void findNum(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                System.out.println("Found");
                return;
            }
        }
        System.out.println("Not found!");
    }

    /* Write a program to find max product of two numbers in an array of positive numbers.*/
    static int maxProductOfTwo(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                max = Math.max(max, nums[i] * nums[j]);
            }
        }
        return max;
    }

    /* Write a program to check if an array is unique or not. */
    static boolean isUnique(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return false;
            } else {
                set.add(num);
            }
        }
        return true;
    }

    /* you are given two integer arrays. 
    Write a program to check if they are permutation of each other.*/
    static boolean isPermutation(int[] nums1, int[] nums2) {
        if (nums1.length != nums2.length) {
            return false;
        }
        int sum1 = 0;
        int sum2 = 0;
        int product1 = 1;
        int product2 = 1;
        for (int i = 0; i < nums1.length; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
            product1 *= nums1[i];
            product2 *= nums2[i];
        }
        return sum1 == sum1 && product1 == product2;
    }

    /* Given an image represendted by n*n matrix, write a program to rotate the 
    the iamge by 90 degrees.*/
    static void rotateMatrix(int[][] matrix) { // time: O(n^2), space: O(1) // Not learned
        int n = matrix.length;
        // Process layers, from outermost to innermost
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                // Save top
                int top = matrix[first][i];
                // left -> top
                matrix[first][i] = matrix[last - offset][first];
                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];
                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];
                // top -> right
                matrix[i][last] = top;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 10};
        int missed = Array.findMissing(arr, 10);
        System.out.println(missed);

        int[] nums = {3, 2, 4};
        System.out.println(Array.twoSum(nums, 6));

        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        Array.findNum(numbers, 4);

        System.out.println(Array.maxProductOfTwo(numbers));

        int[] nums3 = {3, 2, 2, 4};
        System.out.println(Array.isUnique(nums3));

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(Array.isPermutation(arr1, arr2));

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        Array.rotateMatrix(matrix);
        for (int[] is : matrix) {
            for (int i : is) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
    }
}
