package problemSets;

import java.util.*;

public class TestClass {

    public static int removeElement(int[] nums, int val) {
        int lastVal = nums.length - 1;
        int i = 0;
        while (i < lastVal) {
            if (nums[i] == val) {
                nums[i] = nums[lastVal];
                nums[lastVal] = Integer.MAX_VALUE;
                lastVal--;
            } else {
                i++;
            }
        }
        return lastVal;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 2, 6, 2, 3};
        for (int num : nums) System.out.print(num + ",");
        removeElement(nums, 2);
        System.out.println();
        for (int num : nums) System.out.print(num + ",");
    }
}
