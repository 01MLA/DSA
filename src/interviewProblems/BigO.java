package interviewProblems;

/**
 * @author MLati
 */
public class BigO {

    /* Create a function which calculates the sum and product of elements of 
    array - Find the time complexity for created method.
     */
    static void sumAndProduct(int[] nums) { // time: O(n), space: O(1)
        int sum = 0;                // O(1)
        int product = 1;            // O(1)
        for (int num : nums) {      // loop runs n times → O(n)
            sum += num;             // O(1) per iteration
            product *= num;         // O(1) per iteration
        }
        System.out.println("Sum: " + sum + ", Product: " + product); // O(1)
    }

    /**
     * Create a function which prints to the console the pairs from given array.
     * Find the time complexity for created method
     */
    static void printPairs(int[] nums) { // time: O(n^2), space: O(1)
        for (int i = 0; i < nums.length; i++) { // O(n)
            for (int j = 0; j < nums.length; j++) { // O(n)
                System.out.print(nums[i] + "" + nums[j]); // O(1)
                System.out.print(", "); // O(1)
            }
            System.out.println(""); // O(1)
        }
    }

    /* - What is the time complexity for this method? */
    static void printUnorderedPairs(int[] array) { // Time: O(n^2), Space: O(1)
        for (int i = 0; i < array.length; i++) {         // O(n)
            for (int j = i + 1; j < array.length; j++) { // O(n - i - 1)
                System.out.println(array[i] + ", " + array[j]); // O(1)
            }
        }
    }

    /*  What is the runtime of the below code? */
    static void printUnorderedPairsWithCondition(int[] arrayA, int[] arrayB) { //Time: Time = O(a × b), space: O(1)
        for (int i = 0; i < arrayA.length; i++) {        // O(a), where a = arrayA.length
            for (int j = 0; j < arrayB.length; j++) {    // O(b), where b = arrayB.length
                if (arrayA[i] < arrayB[j]) {             // O(1)
                    System.out.println(arrayA[i] + "," + arrayB[j]); // O(1)
                }
            }
        }
    }

    static void printUnorderedPairs(int[] arrayA, int[] arrayB) { // Time = O(a × b) (or O(n²) if both arrays are size n)
        for (int i = 0; i < arrayA.length; i++) {       // O(a)
            for (int j = 0; j < arrayB.length; j++) {   // O(b)
                // O(1) work
            }
        }
    }

    /* What is the runtime of the below code? */
    static void printUnorderedPairs2(int[] arrayA, int[] arrayB) { // Time: O(ab), space: O(1)
        for (int i = 0; i < arrayA.length; i++) {          // O(a)
            for (int j = 0; j < arrayB.length; j++) {      // O(b)
                for (int k = 0; k < 1000000; k++) {        // O(1,000,000) = O(1) constant
                    System.out.println(arrayA[i] + "," + arrayB[j]); // O(1)
                }
            }
        }
    }

    /*
    Create a method which takes an array as a parameter and reverse it.
    find time and space complexity.
    - The loop runs about n/2 times, each iteration is O(1).
    - Total runtime is O(n).
    - Only a few extra variables are used, no extra data structures.
     */
    static int[] reverseArray(int[] nums) { // Time: O(n),Space: O(1)
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int a = nums[start];
            nums[start] = nums[end];
            nums[end] = a;
            start++;
            end--;
        }
        return nums;
    }

    /* What is the runtime of the below code? */
    // Time Complexity: O(n)   - each recursive call does constant work, n calls total
    // Space Complexity: O(n)  - recursion stack grows linearly with n
    static int factorial(int n) { //Time:O(n), space: O(1)
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1); // M(n-1)
        }
    }

    /* What is the runtime of the below code? */
    void allFib(int n) {
        // This loop calls fib(i) for i = 0 to n-1
        // Total time complexity will be the sum of T(fib(i)) for i = 0..n-1
        for (int i = 0; i < n; i++) {
            fib(i); // fib(i) has exponential runtime
        }
    }

    // Time Complexity of allFib(n): O(2^n) in total (because sum of exponential calls ≈ O(2^n))
    // Space Complexity of allFib(n): O(n) (due to recursion stack of the deepest fib call)
    static int fib(int n) {
        if (n <= 0) {
            return 0; // O(1)
        } else if (n == 1) {
            return 1; // O(1)
        } else {
            // Recursive call: fib(n-1) + fib(n-2)
            return fib(n - 1) + fib(n - 2);
            // Time Complexity of fib(n): O(2^n) 
            // Space Complexity of fib(n): O(n) due to recursion stack depth
        }
    }

    public static void main(String[] args) {
//        BigO.sumAndProduct(new int[]{2, 4, 6, 8, 10});
//        BigO.printPairs(new int[]{1, 3, 4, 5});
//        BigO.printUnorderedPairs(new int[]{2, 4, 6, 8, 10});
//        BigO.printUnorderedPairsWithCondition(new int[]{2, 4, 6, 8, 10}, new int[]{2, 4, 6, 8, 10});
        int[] array = (BigO.reverseArray(new int[]{1, 2, 3, 4, 5}));
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
