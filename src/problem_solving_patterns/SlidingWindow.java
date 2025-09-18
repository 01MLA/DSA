package problem_solving_patterns;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SlidingWindow {

    List<Double> findAvgOfSubarrays(int[] array, int k) {
        List<Double> result = new ArrayList();
        int start = 0;
        int windowSum = 0;
        for (int end = 0; end < array.length; end++) {
            windowSum += array[end];
            if (end >= k - 1) {
                result.add(((double) windowSum / k));
                windowSum -= array[start];
                start++;
            }
        }
        return result;
    }

    int maxSubarrayOfSizeK(int[] array, int k) {
        int maxSum = 0;
        int winSum = 0;
        int start = 0;
        for (int end = 0; end < array.length; end++) {
            winSum += array[end];
            if (end >= k - 1) {
                maxSum = Math.max(maxSum, winSum);
                winSum -= array[start];
                start++;
            }
        }
        return maxSum;
    }

    int smallestSubarrayWithGivenSum(int[] array, int s) {
        int winSum = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        for (int end = 0; end < array.length; end++) {
            winSum += array[end];
            while (winSum >= s) {
                minLen = Math.min(minLen, end - start + 1);
                winSum -= array[start];
                start++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    int longestSubstringWithKDistinctChars(String str, int k) {
        int maxLen = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();

        int start = 0;
        for (int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
            while (charFrequency.size() > k) {
                char ch = str.charAt(start);
                charFrequency.replace(ch, charFrequency.get(ch) - 1);
                start++;
                if (charFrequency.get(ch) == 0) charFrequency.remove(ch);
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    int totalFruit(int[] array) {
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();



        /// ----------------------------------------
        return -1;
    }


    public static void main(String[] args) {
        SlidingWindow s = new SlidingWindow();
        String str = "araaci";
        System.out.println(s.longestSubstringWithKDistinctChars(str, 1));
    }
}
