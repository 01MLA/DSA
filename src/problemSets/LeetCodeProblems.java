package problemSets;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

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

/**
 * @author M.Latif-Arfani
 */
public class LeetCodeProblems {

    /**
     * 48. Rotate Image
     * You are given an n*n 2D matrix representing an image, roate the image by 90
     * degrees.(clockwise)(inplace rotate)
     */
    static void rotate(int[][] matrix) {
        transposeMatrix(matrix);
        reverseRows(matrix);
    }

    // Helper
    static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // Helper
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
     * Problem: 1. Two Sum
     * LeetCode Link: <a href="https://leetcode.com/problems/two-sum/">...</a>
     * <p>
     * Given an array of integers `nums` and an integer `target`, return **indices of the two numbers** such that they add up to `target`.
     * <p>
     * ✅ Idea/Intuition:
     * - Use a HashMap to store visited numbers and their indices.
     * - For each number, check if (target - num) exists in map.
     * - Return indices when found.
     * <p>
     * 🛠️ Technique/Pattern:
     * - Hashing / Complement Search
     * - Single-pass HashMap
     * <p>
     * 📊 Complexity:
     * - Time: O(n)
     * - Space: O(n)
     * <p>
     * ⚠️ Edge Cases:
     * - No solution → return empty array
     * - Duplicates handled by map storing index
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            else map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * Problem 02: Length Of Longest Substring Without Repeating Characters (LeetCode #3)
     * <p>
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * <p>
     * Given a string s, find the length of the longest substring without repeating characters.
     * <p>
     * Example 1
     * <p>
     * ```
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with length of 3.
     * ```
     * <p>
     * Example 2
     * <p>
     * ```
     * Input: s = "bbbbb"
     * Output:1
     * Explanation: The answer is "b", with length of 1.
     * ```
     * ✅ Idea/Intuition:
     * <p>
     * - Use the sliding window technique with two pointers (left, right).
     * - Expand `right` pointer to add new characters.
     * - If a duplicate is found, shrink the window from the `left` until it's valid.
     * - Track the maximum window size seen so far.
     * <p>
     * 🛠️ Technique/Pattern:
     * <p>
     * - Sliding Window
     * - Two Pointers
     * - HashSet to check uniqueness
     * <p>
     * 📊 Complexity:
     * <p>
     * - Time: O(n)  (each character visited at most twice)
     * - Space: O(min(n, alphabet size))
     * <p>
     * ⚠️ Edge Cases:
     * <p>
     * - Empty string → return 0
     * - All characters same (e.g., "aaaa") → return 1
     * - String with all unique characters → return length of string
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet set = new HashSet();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    // Problem 03: palindrome number (leetCode: #9)
    // Given an integer number, return true if it's palindrome otherwise false.
    boolean isPalindrome(int x) {
        if (x < 0) return false;
        String num = x + "";
        return checkNumPalindrome(num);
    }

    private boolean checkNumPalindrome(String num) {
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
            int currArea = (right - left) * Math.min(height[left], height[right]);
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
        char[] chars = s.toCharArray();
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
        if (strs.length == 0) return "";
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

    //15. 3Sum triples which become 0
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList();

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
        return new ArrayList<>(result);
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
        ListNode right = head;
        if (right == null) return head.next; //the node to remove is the head itself.

        for (int i = 0; i < n; i++) right = right.next;

        ListNode left = head;
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
            if (c == '{' || c == '[' || c == '(') stack.push(c);
            else if (!stack.isEmpty() && c == '}' && stack.peek() == '{') stack.pop();
            else if (!stack.isEmpty() && c == ']' && stack.peek() == '[') stack.pop();
            else if (!stack.isEmpty() && c == ')' && stack.peek() == '(') stack.pop();
            else return false;
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
        if (list1 != null) curr.next = list1;
        else if (list2 != null) curr.next = list2;

        return dummy.next;
    }

    //22. removeElements
    public int removeElement(int[] nums, int val) {
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

    //35. search insert position
    public int searchInsert(int[] numbers, int target) {
        int mid;
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2; // used (left+): to avoid integer overflow when left and right are large.
            if (numbers[mid] == target) return mid;
            else if (target < numbers[mid]) right = mid - 1;
            else left = mid + 1;

        }
        return left; // When the loop ends, left always points to the smallest index where value is/be ≥ target:
    }

    //41. First missing positive number in an array
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int corrIndex = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[corrIndex]) { // positive(1,2,3...) and not in its place.
                swap(nums, i, corrIndex);
            } else i++;
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) return j + 1;

        }

        return n + 1;
    }

    void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 42. Trapping rain water
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
     * 57.Insert Interval
     * <p>
     * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
     * represent the start and the end of the ith interval and intervals is sorted in ascending order by
     * starti.You are also given an interval newInterval = [start, end] that represents the start and end of another
     * interval.Insert newInterval into intervals such that intervals is still sorted in ascending order by
     * start and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     * Return intervals after the
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

    // Second approach
    int[][] insert2(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> result = new LinkedList();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(int[][]::new);
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
     * 62. Unique Paths: number of possible ways the robot can go from grid[0][0] to grid[n-1][n-1].
     * This approach fills cell by cell to get value of last cell.
     */
    int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1; // first row
        for (int i = 0; i < n; i++) dp[0][i] = 1; // first column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // if you see any obstacle cell(marked with 1), you can't use it.
    int uniquePathsII(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) // first row
            if (obstacleGrid[i][0] == 1) break;
            else dp[i][0] = 1;

        for (int i = 0; i < n; i++)
            if (obstacleGrid[0][i] == 1) break;
            else dp[0][i] = 1; // first column

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
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
        String result = "";
        if (s.length() < t.length()) return result;
        int[] tCount = new int[128];
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }
        char[] sChars = s.toCharArray();
        int left = 0, right = 0, toFind = t.length(), found = 0, minWinLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char ch = sChars[right];
            tCount[ch]--;
            if (tCount[ch] >= 0) found++;
            while (found == toFind) {
                if (minWinLen > (right - left + 1)) {
                    minWinLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                // I don't get this block.
                tCount[sChars[left]]++;
                if (tCount[sChars[left]] > 0) found--;
                left++;
            }
            right++;
        }
        return result;
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
    boolean exist(char[][] board, String word) { // todo: put it in cheat sheets
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
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(count))
            return false; // check if we are within the boundaries.

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
    public int numDecoding(String s) { // todo: not understood fully
        if (s.charAt(0) == '0') return 0;

        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '0' && s.charAt(i) == '0') {
                dp[i] = 0;
            } else if (s.charAt(i - 1) == '0' && s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            } else if (s.charAt(i - 1) != '0' && s.charAt(i - 1) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    dp[i] = (i >= 2) ? dp[i - 2] : 1;
                } else {
                    dp[i] = '0';
                }
            } else {
                if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26) {
                    dp[i] = dp[i - 1] + ((i >= 2) ? dp[i - 2] : 1);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 100. Same Tree:
     * if both tree are the same in structure and value.
     */
    boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, p.left) && isSameTree(p.right, q.right);
    }

    /**
     * 102. Binary Tree leve order traverse
     */
    List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currLevel = new ArrayList();
            for (int i = 0; i < levelSize; i++) {
                currLevel.add(queue.remove().val);
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            result.add(currLevel);
        }
        return result;
    }

    /**
     * 121. Best time to buy and sell stack
     * You are given an array of prices , each showing the stack price in a day.
     * first must to buy and then sell
     * return max profit.
     *
     */
    int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            min = Math.min(min, price);
            maxProfit = Math.max(maxProfit, price - min);
        }
        return maxProfit;
    }

    /**
     * 122. Best time to buy and sell stack II
     * You can only hold at most one share of the stack at any time.
     * You can do multiple buying and selling but not in one time.
     */
    int maxProfit2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    /**
     * 125. Valid palindrome
     * <p>
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
     * all non-alphanumeric characters, it reads the same forward and backward.
     * Alphanumeric characters include letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * <p>
     */

    public boolean isPalindrome(String s) { // to first change the string and then check.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String result = sb.toString().toLowerCase();
        return checkStrPalindrome(result);
    }

    private boolean checkStrPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(String s) {//ignore non-alphabets during checking(checking palindrome during traverse)
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (right > left && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 136. Single Number
     * <p>
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * ex. Input: nums = [2,2,1]     Output: 1
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (var entry : map.entrySet())
            if (entry.getValue() == 1) return entry.getKey();
        return -1;
    }

    public int singleNumber2(int[] nums) { // with Bitwise XOR
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    /**
     * 137. Single Number II
     * All numbers in array are repeated three times, except one, find it
     *
     */
    int SingleElement(int[] nums, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (var num : map.entrySet()) {
            if (num.getValue() == 1) result = num.getKey();
        }
        return result;
    }

    /**
     * 141. Linked List cycle.
     * Check if a linked list has a cycle.
     * Solution: fast and slow pointers.
     */
    boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 143. Reorder list
     * given list: l0 -> l1 -> ... -> ln-1 -> ln
     * result: l0 -> ln -> l1 -> ln-1 -> ...
     * Solution: first, reverse 2nd half of list. then, combine it with 1st half.
     */
    void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode mid = findMiddleNode(head);
        ListNode second = reverseList(mid.next);
        mid.next = null;

        ListNode first = head;
        while (first != null && second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            if (tmp1 == null) break; // important for odd-length lists
            second.next = tmp1;
            first = tmp1;
            second = tmp2;
        }
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 76. Minimum window substring
     * given two strings, s and t, find the min window containing all chars of t in s.
     *
     */

    String minWin(String s, String t) {
        String result = "";
        if (t.length() > s.length()) return result;
        int[] tCount = new int[128];
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }
        char[] sChars = s.toCharArray();
        int left = 0, right = 0;
        int found = 0, toFound = 0;
        int minWinLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = sChars[right];
            tCount[c]--;
            if (tCount[c] >= 0) {
                found++;
            }
            while (found == toFound) {
                if (minWinLen > (right - left + 1)) {
                    minWinLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                tCount[sChars[left]]++;
                if (tCount[sChars[left]] > 0) {
                    found--;
                }
                left++;
            }
        }
        return result;
    }

    /**
     * 145. Binary Tree post order traverse
     *
     */
    List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helperPostOrder(root, result);
        return result;
    }

    private void helperPostOrder(TreeNode root, List<Integer> result) {
        if (root != null) {
            helperPostOrder(root.left, result);
            helperPostOrder(root.right, result);
            result.add(root.val);
        }
    }

    /**
     * 147. Insertion Sort list
     * Given the head of a linked list, sort it using insertion sort. return sorted list's head.
     */
    ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = head;
        while (curr != null) {
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }
            ListNode next = curr.next; // to remind the rest of list

            // to put the picked element
            curr.next = prev.next;
            prev.next = curr;

            curr = next; // loop pointer
        }
        return dummy.next;
    }

    /**
     * 150. Evaluate Reverse polish notation (+,-,*,/)
     * given an array of Strings tokens, that represents arithmetic expressions in a reverse polish notation.
     *
     */
    int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String str : tokens) {
            if (isOperator(str)) {
                String a2 = stack.pop();
                String a1 = stack.pop();
                int result = switch (str) {
                    case "+" -> Integer.parseInt(a2) + Integer.parseInt(a1);
                    case "-" -> Integer.parseInt(a2) - Integer.parseInt(a1);
                    case "*" -> Integer.parseInt(a2) * Integer.parseInt(a1);
                    case "/" -> Integer.parseInt(a2) / Integer.parseInt(a1);
                    default -> 0;
                };
                stack.push(String.valueOf(result));
            } else stack.push(str);
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    /**
     * 151. reverse words in a string
     * ex. "a good   example " to "example good a"
     */
    String reverseWords(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }
            StringBuilder word = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                i++;
            }
            if (!word.toString().isEmpty()) result.insert(0, word + " ");
        }
        if (result.isEmpty()) return "";
        return result.substring(0, result.length() - 1);
    }

    /**
     * 152. find minimum in rotated sorted array (in O(log n) time complexity)
     * e.g. numbers = {3,4,5,1,2}, output: 1
     * ex: the original array was {1,2,3,4,5} rotated 3 times.
     */
    int findMin(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) return nums[0];

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            else if (nums[mid] < nums[mid - 1]) return nums[mid];

            if (nums[mid] < nums[left]) right = mid - 1;
            else left = mid + 1;
        }
        return nums[0];
    }

    /**
     * 162. Find peek element. (int O(log n) TC)
     */
    int findPeekElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid - 1]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    /**
     * 167. Two sum II - input array is sorted
     */
    int[] towSum(int[] nums, int target) {
        int[] result = new int[2];
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            } else if (sum > target) right--;
            else left++;
        }
        return result;
    }

    /**
     * 169. Majority element
     */
    int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        int majority = nums.length / 2;
        int result = 0;
        for (var num : map.entrySet()) {
            if (num.getValue() > majority) result = num.getKey();
        }
        return result;
    }

    /**
     * 189. Rotate Array
     * rotate an array to the right, k steps.
     * e.g. nums = {1,2,3,4,5,,6,7}, output: {5,6,7,1,2,3,4}
     * <p>
     * step 1: {7,1,2,3,4,5,6}
     * step 2: {6,7,1,2,3,4,5}
     * step 3: {5,6,7,1,2,3,4}
     * </p>
     */
    void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[(i + k) % len] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    // other solution
    void rotate2(int[] nums, int k) {
        if (k > nums.length) k = k % nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 198. House robber
     */
    int[] memo = new int[101];

    int rob(int[] nums) {
        Arrays.fill(memo, -1);
        return solve(nums, 0);
    }

    // helper
    private int solve(int[] nums, int index) {
        if (index >= nums.length) return 0;
        if (memo[index] != -1) return memo[index];
        int skipped = solve(nums, index + 1);
        int robbed = nums[index] + solve(nums, index + 2);
        return memo[index] = Math.max(skipped, robbed);
    }

    /**
     * 199. Binary Tree Right Side View
     */
    List<Integer> rightSideView(TreeNode root) { // how it works just for right side?
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                curr = queue.poll();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            result.add(curr.val);
        }
        return result;
    }

    /**
     * 201. Bitwise AND of numbers range
     */
    int rangeBitwiseAnd(int left, int right) {
        int shifts = 0;
        while (left != right) { // to find where it remains just common binary part.
            left >>= 1;
            right >>= 1;
            shifts++;
        }
        return left << shifts; // left or right can be modifier to get result.
    }

    /**
     * 205. isomorphic strings
     * <p>
     * They are isomorphic if the characters in s can be replaced to get t. order should be considered.
     * </p>
     */
    boolean isIsomorphic(String s, String t) {
        return isoHelper(s).equals(isoHelper(t));
    }

    String isoHelper(String s) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) map.put(c, i);
            sb.append(map.get(c));
            sb.append("-");
        }
        return sb.toString();
    }

    /**
     * 206. reverse linked list
     */
    ListNode reverseList2(ListNode head) {
        if (head == null) return head;

        // three pointers used to reverse the connection in each point
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while (curr != null) {
            curr.next = prev; // to reverse the connection in this point
            // change the three pointers to point to next point.
            prev = curr;
            curr = next;
            if (next != null) next = curr.next;
        }
        return head = prev;
    }

    /**
     * 209. min size subarray sum
     */
    int minSubArrayLen(int[] array, int target) {
        int winSum = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        for (int end = 0; end < array.length; end++) {
            winSum += array[end];
            while (winSum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                winSum -= array[start];
                start++;
            }
        }
        return minLen;
    }

    /**
     * 217. contains duplicate
     */
    boolean containsDuplicate(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(num)) return true;
            else set.add(num);
        }
        return false;
    }

    /**
     * 219.contains duplicate II
     * <p>
     * where the same numbers are in two different indexes, far k steps.
     * </P>
     */
    boolean containsNearbyDuplicates(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], i);
            else {
                int diff = Math.abs(map.get(nums[i]) - i);
                if (diff <= k) return true;
                else map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 231. power of two
     */
    boolean isPowerOfTwo(int x) {
        if (x <= 0) return false;
        int shifts = 0;
        int bitCount = Integer.bitCount(x);
        return bitCount == 1;
    }

    /**
     * 234. Palindrome linked list
     */
    boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!Objects.equals(list.get(left), list.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * 238. product of array except self - first approach
     */
    int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];
        for (int i = 0, temp = 1; i < nums.length; i++) {
            leftProduct[i] = temp;
            temp *= nums[i];
        }
        for (int i = nums.length - 1, temp = 1; i >= 0; i--) {
            rightProduct[i] = temp;
            temp *= nums[i];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = leftProduct[i] * rightProduct[i];
        }
        return result;
    }

    /**
     * 238. product of array except self - second approach
     */
    int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0, temp = 1; i < nums.length; i++) {
            result[i] = temp;
            temp *= nums[i];
        }
        for (int i = nums.length - 1, temp = 1; i >= 0; i--) {
            result[i] *= temp;
            temp *= nums[i];
        }
        return result;
    }

    /**
     * 242. Valid anagram - for english small letters
     */
    boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }
        for (int num : count) {
            if (num > 0) return false;
        }
        return true;
    }

    /**
     * 268. missing number
     * <p>
     * in a range of [0,n]
     * </p>
     */
    int missingNumber(int[] nums) {
        int n = nums.length;
        int numsSum = 0;
        for (int num : nums) {
            numsSum += num;
        }
        int totalSum = (n * (n - 1)) / 2;
        return totalSum - numsSum;
    }

    /**
     * 279. Perfect squares - approach one
     * <p>
     * Given an int n,return the least number of perfect square numbers that sum to n.
     * Perfect square is an integer that is the square of an integer.
     * </p>
     */

    int[] squareMemo;

    int numSquares(int n) {
        squareMemo = new int[n + 1];
        Arrays.fill(squareMemo, -1);
        return helperFun(n - 1);
    }

    int helperFun(int n) {
        if (n == 0) return 0;
        if (squareMemo[n] != -1) return squareMemo[n];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            int curr = 1 + helperFun(n - i * i);
            min = Math.min(min, curr);
        }
        return squareMemo[n] = min;
    }


    /**
     * 283. Move Zeroes
     * <p>
     * Move zeroes to end of array , no more space used.
     * </p>
     */
    void moveZeroes(int[] nums) {
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < n; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 290. Word pattern
     * <p>
     * check if s follows the pattern (full match). Here fallow means,that there is a bijection between a letter in
     * pattern and a non-empty word in s.
     * </p>
     */
    boolean wordPattern(String pattern, String s) {
        Map<Character, String> char_map = new HashMap<>();
        Map<String, Character> word_map = new HashMap<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (!char_map.containsKey(c)) {
                if (word_map.containsKey(word)) return false;
                else {
                    char_map.put(c, word);
                    word_map.put(word, c);
                }
            } else {
                String mappedWord = char_map.get(c);
                if (!mappedWord.equals(word)) return false;
            }
        }
        return true;
    }

    /**
     * 130. longest increasing subsequence
     * <p>
     * return the len of longest strictly increasing subsequence
     * </p>
     * input: nums:{10,9,2,5,,3,7,101,18}, output:4 {2,3,7,101}
     */
    int lenOfLIS(int[] nums) {
        return -1;
    }

    /**
     * helper fun to print linked lists
     */
    void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            if (curr.next != null) {
                System.out.print(" ~> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * 328. odd even linked list
     * <p>
     * group all the nodes with odd indices followed the nodes with even indices , and return the reordered list.
     * </p>
     */
    ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode odd = head; // list of odds
        ListNode even = head.next; // list of evens
        ListNode evenStart = even; // pointer to remember start of evens
        while (odd.next != null && even.next != null) {
            odd.next = even.next; // point to other odd: add-even-odd
            odd = even.next; // move further
            even.next = odd.next; // point to other even: even-odd-even
            even = odd.next; // move further
        }
        odd.next = evenStart; // connect end of adds to evens
        return head;
    }

    /**
     * 334. increasing triplet subsequence
     * <p>
     * check if in the array exists a triple of indices (i,j,k) such that i<j<k and nums[i] < nums[j] < nums[k]
     * </p> todo: use it a cheat sheet (a good example way)
     */
    boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) first = num;
            else if (num <= second) second = num;
            else return true;
        }
        return false;
    }


    /**
     * 338. Counting Bits
     * <p>
     * Given an int n,return an array ans of len n+1, such that for each i (0 <= i <= n) is the number of 1's in the
     * binary representation of i.
     * </p>
     * e.g. n = 2   output: [0,1,1]
     * exp: 0 --> 0, 1 --> 1, 2 --> 10
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            String binary = Integer.toBinaryString(i);
            System.out.println(binary);
            ans[i] = countOnes(binary);
        }
        return ans;
    }

    private int countOnes(String num) {
        int count = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    //    Optimized Code
    int[] countBits2(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1); // it computes the previous computed bits (as a precomputed num) plus end bit.
        }
        return ans;
    }

    // solution 3
    static class Solution {
        static {
            Solution sol = new Solution();
            for (int i = 0; i < 500; i++) sol.countBits(0);  // JIT warmup
        }

        public int[] countBits(int n) {
            int[] res = new int[n + 1];
            res[0] = 0;
            if (n == 0) {
                return res;
            }
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    res[i] = res[i / 2] + 1;
                } else {
                    res[i] = res[i / 2];
                }
            }
            return res;
        }
    }

    /**
     * 344. Reverse String
     */
    void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[end];
            s[end] = s[start];
            s[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * 345. Reverse String - reverse vowels
     */
    String reverseStringVowels(String s) {
        int start = 0;
        int end = s.length() - 1;
        char[] chars = s.toCharArray();
        String vowels = "AEIOUaeiou";
        while (start < end) {
            while (start < end && vowels.indexOf(chars[start]) == -1) start++;
            while (end > start && vowels.indexOf(chars[end]) == -1) end--;

            char temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;
            start++;
            end--;
        }
        return String.valueOf(chars);
    }

    /**
     * 347. top k frequent elements
     */
    int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));
        int[] result = new int[k];
        int index = 0;
        for (int i = 0; i < k; i++) {
            if (i < map.size()) {
                result[index] = list.get(i); // if there was asked k top numbers, but there wasn't k type number.
            } else result[index] = -1;
            index++;
        }
        return result;
    }

    /**
     * 349. Intersection of two arrays
     */
    int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums1) map.put(num, 1);
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) == 1) {
                result.add(num);
                map.put(num, 0); // to avoid repeats
            }
        }
        int[] answers = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answers[i] = result.get(i);
        }
        return answers;
    }

    /**
     * 368. Largest Divisible subset
     */
    List<Integer> result;
    int[] subsetSizeMemo;

    List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<>();
        subsetSizeMemo = new int[nums.length];
        Arrays.fill(subsetSizeMemo, -1);
        l_helper(nums, 0, new ArrayList<>(), 1);
        return result;
    }

    private void l_helper(int[] nums, int index, ArrayList<Integer> currList, int prev) {
        if (currList.size() > result.size()) {
            result = new ArrayList<>(currList);
        }
        for (int i = index; i < nums.length; i++) {
            if (currList.size() > subsetSizeMemo[i] && nums[i] % prev == 0) {
                subsetSizeMemo[i] = currList.size();
                currList.add(nums[i]);
                l_helper(nums, i + 1, currList, nums[i]);
                currList.removeLast();
            }
        }
    }


    /**
     * 374. guess number higher or lower
     */

    int guessNumber(int n) {
        return -1;
    }

    int guess() {
        return -1;
    }

    /**
     * Insert delete getRandom O(1)
     */
    class RandomizedSet {
        List<Integer> list;
        HashMap<Integer, Integer> map;
        Random random = new Random();

        public RandomizedSet() {
            this.list = new ArrayList<>();
            this.map = new HashMap<>();
        }

        public boolean insert(int value) {
            if (map.containsKey(value)) return false;
            map.put(value, list.size());
            list.add(value);
            return true;
        }

        public boolean remove(int value) {
            if (!map.containsKey(value)) return false;
            int pos = map.get(value);
            if (pos != (list.size() - 1)) {
                int lastElement = list.getLast();
                list.set(pos, lastElement);
                map.put(lastElement, pos);
            }
            map.remove(value);
            list.removeLast();
            return true;
        }

        public int getRandom() {
            int randomInt = random.nextInt(list.size());
            return list.get(randomInt);
        }
    }

    /**
     * 383. Ransom note
     * Given two strings ransom Note and other magazine, check if the note is constructable out of magazine.
     */
    boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }
        int count = 0;
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (map.containsKey(c) && map.get(c) > 0) {
                count++;
                map.put(c, map.get(c) - 1);
            } else break;
        }
        return count == ransomNote.length();
    }

    // other solution - optimized
    public boolean canConstruct2(String ransomNote, String magazine) {
        int from[] = new int[26];
        for (char ch : magazine.toCharArray()) {
            from[ch - 'a']++;
        }

        int to[] = new int[26];
        int index = 0;
        for (char ch : ransomNote.toCharArray()) {
            index = ch - 'a';
            to[index]++;

            if (to[index] > from[index]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 387. first unique character in a string
     */
    int firstUniqueChar(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i)] == 1) return i;
        }
        return -1;
    }

    /**
     * 389. find the difference
     */
    char findTheDifference(String s, String t) {
        int t_sum = 0;
        int s_sum = 0;
        for (char c : s.toCharArray())
            s_sum += c;
        for (char c : t.toCharArray())
            t_sum += c;
        return (char) (t_sum - s_sum);
    }

    /**
     * 392. is subsequence
     */
    boolean isSubSequence(String s, String t) {
        if (s == null || s.isEmpty()) return true;
        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(index) == t.charAt(i)) index++;
            if (index == s.length()) return true;
        }
        return false;
    }

    // more optimized solution
    public boolean isSubsequence(String s, String t) {
        int prev = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = t.indexOf(c, prev + 1);
            if (idx == -1) {
                return false;

            }
            prev = idx;

        }
        return true;
    }

    /**
     * 402. Remove k digits. num is non-negative int
     * <p>
     * return the smallest possible integer after removing k digits from num.
     * <p>
     * e.g. num="1432219", k=3, output="1219"
     * e.g. num="10200", k=1, output="200"
     * e.g. num="10", k=2, output="0"
     * </p>
     * </p>
     */
    String removeKDigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }
        while (!stack.isEmpty() && k > 0) { // some times while all chars are passed, but not k digits deleted.
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == 0) sb.deleteCharAt(0);
        return (!sb.isEmpty()) ? sb.toString() : "0";
    }

    /**
     * 442. Find all duplicates in an array
     */
    List<Integer> findDuplicates(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int c : arr) {
            arr[Math.abs(c) - 1] *= -1;
        }
        for (int c : arr) {
            if (arr[Math.abs(c) - 1] > 0) {
                result.add(Math.abs(c));
                arr[Math.abs(c) - 1] *= -1;
            }
        }
        return result;
    }

    /**
     * 443. String comparison
     */
    int compress(char[] chars) {
        int index = 0;
        int start = 0;
        while (start < chars.length) {
            int end = start;
            while (end < chars.length && chars[start] == chars[end]) {
                end++;
            }
            int count = end - start; // len of reputation
            chars[index++] = chars[start];
            if (count >= 2) {
                char[] freq = Integer.toString(count).toCharArray();
                for (char c : freq) {
                    chars[index++] = c;
                }
            }
            start = end;
        }
        return index;
    }

    /**
     * 455. Assign cookies - not understood weel
     */
    int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int children = 0;
        int cookie = 0;
        while (cookie < s.length && children < g.length) {
            if (s[cookie] >= g[children]) {
                children++;
            }
            cookie++;
        }
        return children;
    }

    /**
     * 485. max consecutive ones
     *
     */
    int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                max = Math.max(max, count);
            } else count = 0;
        }
        return max;
    }

    /**
     * 520. Detect capital
     * <p>
     * check if capital these rules are noticed in the string.
     * 1. all lower case
     * 2. title case: Title
     * 3. all upper case
     * </p>
     *
     */
    boolean detectCapitalUse(String word) {
        return allLower(word) || allCaps(word) || titleCase(word);
    }

    // helper
    private boolean allCaps(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isUpperCase(c)) return false;
        }
        return true;
    }

    // helper
    private boolean allLower(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLowerCase(c)) return false;
        }
        return true;
    }

    // helper
    private boolean titleCase(String s) {
        return Character.isUpperCase(s.charAt(0)) && allLower(s.substring(1));
    }


    /**
     * 446. Arithmetic Slices II - Subsequence
     *
     */
    int numberOfArithmeticSlices(int[] nums) {
        return -1;
    }

}

class Main {

    public static void main(String[] args) {
        LeetCodeProblems lc = new LeetCodeProblems();
        System.out.println(lc.detectCapitalUse("TiTLE"));

    }
}

/**
 * 232. Implement queue using stacks
 */
class MyQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> tempStack = new Stack<>();

    MyQueue() {
    }

    void push(int value) {
        while (!stack1.isEmpty()) {
            tempStack.push(stack1.pop());
        }
        stack1.push(value);
        while (!tempStack.isEmpty()) {
            stack1.push(tempStack.pop());
        }
    }

    int pop() {
        return (!stack1.isEmpty()) ? stack1.pop() : -1;
    }

    int peek() {
        return (!stack1.isEmpty()) ? stack1.peek() : -1;
    }

    boolean isEmpty() {
        return stack1.isEmpty();
    }

}
