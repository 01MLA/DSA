package problem_solving_patterns;

public class BinarySearch {

    // Returns the minimum index where condition(mid) is true
    public static int binarySearch(int n, java.util.function.IntPredicate condition) {
        int left = 0, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (condition.test(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int left = binarySearch(nums, target, true);        // Find the leftmost index of target
        int right = binarySearch(nums, target, false);      // Find the rightmost index of target
        result[0] = left;
        result[1] = right;
        return result;
    }

    // Modified binary search to find first or last occurrence
    private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
        int left = 0, right = nums.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) right = mid - 1; // go left
            else if (nums[mid] < target) left = mid + 1;  // go right
            else {
                idx = mid; // target found
                if (isSearchingLeft) right = mid - 1; // keep searching left side
                else left = mid + 1;  // keep searching right side
            }
        }
        return idx; // return first or last index found
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in the right half
                left = mid + 1;
            } else {
                // Minimum could be mid or in the left half
                right = mid;
            }
        }

        // left == right, pointing to the minimum
        return nums[left];
    }

    int findMini(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }


}