package problem_solving_patterns;

import java.util.*;

public class SlidingWindow {

    /**
     * Finds average of all contiguous subarrays of size k
     */
    List<Double> findAveOfSubarraysOfSizeK(int[] array, int k) {
        List<Double> result = new ArrayList<>();
        int start = 0;
        int windowSum = 0;

        for (int end = 0; end < array.length; end++) {
            windowSum += array[end]; // expand window by adding new element

            // when window size reaches k, compute average
            if (end >= k - 1) {
                double ave = (double) windowSum / k;
                result.add(ave);

                // slide the window forward
                windowSum -= array[start];
                start++;
            }
        }
        return result;
    }

    /**
     * Finds maximum sum of any contiguous subarray of size k
     */
    int maxSumSubarrayOfSizeK(int[] array, int k) {
        int maxSum = 0;

        int winSum = 0;
        int start = 0;
        for (int end = 0; end < array.length; end++) {
            winSum += array[end]; // expand window

            if (end >= k - 1) {
                maxSum = Math.max(maxSum, winSum); // update max sum
                winSum -= array[start]; // slide window
                start++;
            }
        }
        return maxSum;
    }

    /**
     * Finds length of smallest subarray with sum >= s
     */
    int smallestSubarrayWithGivenSum(int[] array, int s) {
        int minLen = Integer.MAX_VALUE;
        int winSum = 0;
        int start = 0;

        for (int end = 0; end < array.length; end++) {
            winSum += array[end]; // add next element

            // shrink window while condition is met
            while (winSum >= s) {
                minLen = Math.min(minLen, end - start + 1);
                winSum -= array[start];
                start++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    /**
     * Finds length of longest substring with at most k distinct characters
     */
    int longestSubstringWithKDistinctChars(String str, int k) {
        int maxLen = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();
        int start = 0;

        for (int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);

            // shrink window if more than k distinct chars
            while (charFrequency.size() > k) {
                char ch = str.charAt(start);
                charFrequency.replace(ch, charFrequency.get(ch) - 1);
                if (charFrequency.get(ch) == 0) charFrequency.remove(ch);
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    /**
     * Total fruit - longest subarray with at most 2 types (solution 1 using Set)
     */
    int totalFruit(char[] array) {
        int maxFruit = 0;
        Set<Character> set = new HashSet<>();
        int start = 0;

        for (int end = 0; end < array.length; end++) {
            set.add(array[end]);

            // shrink window if more than 2 types
            while (set.size() > 2) {
                set.remove(array[start]);
                start++;
            }

            maxFruit = Math.max(maxFruit, end - start + 1);
        }
        return maxFruit;
    }

    /**
     * Total fruit - solution 2 using frequency map (better)
     */
    int totalFruit2(char[] array) {
        int maxFruit = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

        for (int end = 0; end < array.length; end++) {
            map.put(array[end], map.getOrDefault(array[end], 0) + 1);

            // shrink window if more than 2 types
            while (map.size() > 2) {
                map.put(array[start], map.get(array[start]) - 1);
                if (map.get(array[start]) == 0) map.remove(array[start]);
                start++;
            }

            maxFruit = Math.max(maxFruit, end - start + 1);
        }
        return maxFruit;
    }

    /**
     * Finds length of longest substring without repeating characters
     */
    int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            // shrink window until no duplicate
            while (set.contains(c)) {
                set.remove(s.charAt(start));
                start++;
            }

            set.add(c);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    /**
     * Longest substring with same letters after replacing at most k chars
     */
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26]; // frequency of chars A-Z
        int left = 0, n = s.length(), maxLen = 0, maxCount = 0;

        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);

            // shrink if replacements exceed k
            if (right - left + 1 - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Longest subarray of 1s after replacing up to k zeros (buggy version)
     */
    int longestOnesAfterReplacement(int[] nums, int k) {
        int max = 0;
        int zeroCount = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) zeroCount++;

            // shrink if zeros exceed k-1 (logic issue)
            while (zeroCount > k - 1) {
                if (nums[end] == 0) zeroCount--;
                start++;
            }

            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    /**
     * Longest subarray of 1s after replacing up to k zeros (correct version)
     */
    int longestOnesAfterReplacement2(int[] nums, int k) {
        int maxLen = 0;
        int oneCount = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 1) oneCount++;

            // shrink if zeros > k
            while ((end - start + 1) - oneCount > k) {
                if (nums[start] == 1) oneCount--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}

class Main {
    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();


    }
}
