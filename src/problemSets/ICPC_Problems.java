package problemSets;

import java.util.*;

/**
 * @author M.Latif-Arfani
 */
public class ICPC_Problems {

    //1. two-sum problem
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int complement = target = nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            } else {
                map.put(nums[i], i);
            }
        }
        System.out.println("No Mutch");
        return new int[]{};
    }

    //3. lengthOfLongestSubstring withou repeating chars
    public int lengthOfLongestSubstring(String s) {
        HashSet set = new HashSet();
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            int len = right - left + 1;
            max = Math.max(max, len);
        }
        return max;
    }

    //9. palindrome number
    boolean isPalidrome(int x) {
        if (x < 0) {
            return false;
        }
        String num = x + "";
        return checkPalindrome(num);
    }

    private boolean checkPalindrome(String num) {
        int left = 0;
        int right = num.length() - 1;
        while (left < right) {
            if (num.charAt(left) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 11. Container with most water
    int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int currArea = (int) (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, currArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    //13. change Roman number to Integer number.
    int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char chars[] = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (map.get(chars[i]) >= map.get(chars[i + 1])) {
                result += map.get(chars[i]);
            } else {
                result -= map.get(chars[i]);
            }
        }
        result += map.get(chars[chars.length - 1]); // the one char remained!
        return result;
    }

    //14. longestCommonPrefix
    String longestCommmonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    //15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList();
        }

        Set<List<Integer>> result = new HashSet();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                List<Integer> list = new ArrayList<>();
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    start++;
                    end--;
                    result.add(list);
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return new ArrayList<List<Integer>>(result);
    }

    class ListNode {

        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    //19. remove Nth node from the end of list
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        if (n == length) {
            head = head.next;
            return head;
        }

        curr = head;
        for (int i = 0; i < length - n - 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return head;
    }

    //19. remove Nth node from the end of list - second approach
    public ListNode removeNthFromEndOfList(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;

        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        if (right == null) { //the node to remove is the head itself.
            return head.next;
        }

        while (right.next != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return head;
    }

    //20. valid parentheses
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (!stack.isEmpty() && c == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (!stack.isEmpty() && c == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (!stack.isEmpty() && c == '}' && stack.peek() == '{') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // 21. Merge two sorted lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }
        return dummy.next;
    }

    //22. removeElments
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[len - 1];
                len--;
            } else {
                i++;
            }
        }
        return len;
    }

    //35. search insert position  ?
    public int searchInsert(int[] nums, int target) {
        int mid;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2; // why? left + (right-left)/2
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //41. First missing positive
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // Step 1: Place each number at its correct position (if possible)
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] with nums[nums[i] - 1]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // Step 2: Find the first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // Step 3: If all are in place, return n+1
        return n + 1;
    }

    /**
     * 42. Traping rain water
     * Given n non-negative integers representing an elevation map where the
     * width of each bar is 1, compute how much water it can trap after rain.
     * input: [0,1,0,2,1,0,1,3,2,1,2,1] output: 6 The above elevation map(black
     * section) is represented by array [above]. In this case, 6 units of rain
     * water (blue section) are being trapped.
     */
    int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        left[0] = height[0];
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(height[i], left[i - 1]);
        }
        int[] right = new int[len];
        right[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }
        int water = 0;
        for (int i = 0; i < len; i++) {
            water += (Math.min(left[i], right[i]) - height[i]);
        }
        return water;
    }

    /*48. Rotate Image 
    You are given an n*n 2D matrix representing an image, roate the image by 90
    degrees.(clockwise)(inplace rotate)
     */
    static void rotate(int[][] matrix) {
        transposeMatrix(matrix);
        reverseRows(matrix);
    }

    static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    static void reverseRows(int[][] matrix) {
        for (int[] matrix1 : matrix) {
            int left = 0;
            int right = matrix.length - 1;
            while (left < right) {
                int temp = matrix1[left];
                matrix1[left] = matrix1[right];
                matrix1[right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * 49. Group anagrams
     * Given an array of strings strs, group the anagrams together. you can return
     * the answer in any order.
     * input: strs: {"eat","tea","ate","nat","bat"}
     * output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            if (!map.containsKey(sortedString)) {
                map.put(sortedString, new ArrayList<>());
            }
            map.get(sortedString).add(str);
        }
        result.addAll(map.values());
        return result;
    }

    /**
     * 53. Max subarray
     * Given an integer array nums, find the subarray with the largest sum,
     * and return its sum.
     * input: [-1,1,-3,4,-1,2,1,-5,4]
     * output: 6
     */
    int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    /**
     * 55. Jump Game
     * You are given an intege array nums. you are initailly in positioned at the
     * array's first index, and each element in the array represents your maximum
     * jump legth at that position.
     * return true if you can reach that last index or false otherwise.
     * input: [2,3,1,1,4] out: true, exp: jump 1 step from index 0 to 1, then 3 steps
     * to the last index.
     * inut: [3,2,1,0,4] out: false, exp: you will allways arrive at index 3 no, matter what. its
     * max jump length is 0, which makes impossible to reach last.
     */
    boolean jumpGame(int[] nums) {
        if (nums.length == 1) {
            return true; // already at the last index
        }
        int maxReach = 0; // farthest index we can reach
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {// we can't reach this position
                return false;
            }
            // update farthest index we can reach
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;//we can reach or pass the last index
            }
        }
        return false;
    }

    /**
     * 57.Insert Interval You are given an array of non-overlapping intervals
     * intervals where intervals[i] = [starti, endi] represent the start and the
     * end of the ith interval and intervals is sorted in ascending order by
     * starti.You are also given an interval newInterval = [start, end] that
     * represents the start and end of another interval.Insert newInterval into
     * intervals such that intervals is still sorted in ascending order by
     * start and intervals still does not have any overlapping intervals (merge
     * overlapping intervals if necessary). Return intervals after the
     * insertion.
     * <p>
     * Note that you don't need to modify intervals in-place. You can make a new
     * array and return it. Example 1: Input: intervals = [[1,3],[6,9]],
     * newInterval = [2,5] Output: [[1,5],[6,9]] Example 2: Input: intervals =
     * [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] Output:
     * [[1,2],[3,10],[12,16]] Explanation: Because the new interval [4,8]
     * overlaps with [3,5],[6,7],[8,10].
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = Arrays.copyOf(intervals, intervals.length + 1);
        newIntervals[newIntervals.length - 1] = newInterval;
        return merge(newIntervals);
    }

    int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(int[][]::new);
    }

    /**
     * 58. Length of last word
     * Given a string s consisting of some words separated by some number of spaces , return the len of last word.
     * A word is a maximum substring consisting of non-space characters only.
     * input: "Hello world", output: 5
     *
     */
    int lengthOfLastWord(String s) {
        s = s.trim(); // remove trailing spaces
        int lastSpace = s.lastIndexOf(' ');
        if (lastSpace == -1) {
            return s.length(); // single word
        }
        return s.length() - lastSpace - 1;
    }

    // second approach
    int lenOfLastWord(String s) {
        String[] strs = s.split(" ");
        return strs[strs.length - 1].length();
    }

    /**
     * 70. Climbing stairs
     * You are climbing a staircase. it takes n steps to reach the top.
     * Each time you can either climb 1 or 2 step. in how many distinct ways you can climb to the top.
     *
     */
    int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;
        int one = 1;
        int two = 1;
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = one + two;
            one = two;
            two = ans;
        }
        return ans;
    }

    //other approach
    int climbTheStairs(int n) {
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;
        for (int i = 2; i <= n; i++) {
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[steps.length - 1];
    }


    /**
     * 73. Set matrix zeroes (inplace)
     * Given an m*n integer matrix, if an element is 0, set its entire row and column to 0's.
     * input:
     * [
     * [1,1,1],
     * [1,0,1],
     * [1,1,1],
     * }
     * output:
     * [
     * [1,0,1],
     * [0,0,0],
     * [1,0,1],
     * }
     */
    void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowSet.contains(i) || colSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /* 75. Sort colors
     * Given an array nums with n objects colored red, white or blue, sort them in place so, that objects of the same color
     * are adjacent, with the colors in the order red, white and blue.
     * We will use the integer 0, 1 and 2 to represent the color red, white and blue respectively.
     * you must solve this problem without using the library's sort functions.
     * input: [2,0,2,1,1,0], out: [0,0,1,1,2,2]
     * */
    void sortColors(int[] colors) {
        int zeroPointer = 0;
        int twoPointer = colors.length - 1;
        int i = 0;
        while (i <= twoPointer) {
            if (colors[i] == 0) {
                int temp = colors[zeroPointer];
                colors[zeroPointer] = colors[i];
                colors[i] = temp;
                i++;
                zeroPointer++;
            } else if (colors[i] == 2) {
                int temp = colors[twoPointer];
                colors[twoPointer] = colors[i];
                colors[i] = temp;
                twoPointer--;
            } else {
                i++;
            }
        }
    }

    /* 76. Minimum Window substring
     * Given two string s and t lengths m and n respectively, return the min window substring of s such that every char,
     * is t(including duplicates) is included in the window. If there is no such substring, return the empty "".
     * the testcases will be generated such that the answer is unique.
     * input: s="AD0BECODEBANC", T="",      output: "BANC"
     * ex: the min window substring "BANC" includes 'A','B' and 'C' from string t.
     *
     * */
    String minWindow(String s, String t) {
        return "";
    }

    /**
     * 79.Word Search
     * Given an m * n grid of char board and a string word, return true if the word exist in the grid.
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
     * vertically neighboring. The same letter cell may not be used more than once.
     * input:{
     * {A,B,C,E},
     * {S,F,C,S},
     * {A,D,E,E}
     * }, word: "ABCCED"    ,       output: true
     */
    boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && helper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean helper(char[][] board, String word, int i, int j, int count) {
        if (count == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == word.charAt(count))
            return false;

        char temp = board[i][j];
        board[i][j] = ' ';
        boolean found = helper(board, word, i + 1, j, count + 1) || helper(board, word, i - 1, j, count + 1) || helper(board, word, i, j + 1, count + 1) || helper(board, word, i, j - 1, count + 1);
        board[i][j] = temp;
        return found;
    }

    /**
     * 83. Remove duplicates from sorted list
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
     * Return the linked list sorted as well.
     *
     *
     */
    ListNode removeDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) curr.next = curr.next.next;
            else curr = curr.next;
        }
        return head;
    }

    /**
     * 88. Merge sorted arrays
     * You are given two integer arrays nums1, nums2, sorted in non-decreasing order, and two integers m and n,
     * representing the number of elements in nums1 and nums2 respectively.
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order. the result is in nums1 (with m+n cap..)
     * input: int[] nums1 = {1, 2, 3, 0, 0, 0},  int[] nums2 = {2, 5, 6}, m=3, n=3
     * output: nums1 = {1, 2, 2, 3, 5, 6}
     */
    void mergeArrays(int[] nums1, int m, int[] nums2, int n) {
        int pointer = nums1.length - 1, mIn = m - 1, nIn = n - 1;
        while (pointer >= 0) {
            int element1 = (mIn >= 0) ? nums1[mIn] : Integer.MIN_VALUE;
            int element2 = (nIn >= 0) ? nums2[nIn] : Integer.MIN_VALUE;
            if (element2 > element1) {
                nums1[pointer] = element2;
                pointer--;
                nIn--;
            } else {
                nums1[pointer] = element1;
                pointer--;
                mIn--;
            }
        }
    }

    /**
     * 91. Decode Ways
     * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following
     * mapping: "1" -> 'A', "2" -> 'B' ... "25" -> 'Y', "26" -> 'Z'
     * However, while decoding the message, you realize that there are many different ways you can decode the message
     * because some codes are contained in other codes ("2" and "5" vs "25").
     * For example, "11106" can be decoded into:
     * <p>
     * "AAJF" with the grouping (1, 1, 10, 6)
     * "KJF" with the grouping (11, 10, 6)
     * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
     * Note: there may be strings that are impossible to decode.
     * <p>
     * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be
     * decoded in any valid way, return 0. The test cases are generated so that the answer fits in a 32-bit integer.
     * <p>
     * Example 1: Input: s = "12" Output: 2
     * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
     * <p>
     * Example 2: Input: s = "226", Output: 3
     * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     * <p>
     * Example 3: Input: s = "06", Output: 0
     * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.
     */
    public int numDecodings(String s) {
        return 0;
    }


    public static void main(String[] args) {
        ICPC_Problems icpc = new ICPC_Problems();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        icpc.mergeArrays(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

}