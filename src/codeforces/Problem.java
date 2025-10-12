package codeforces;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Problem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            Integer[] a = new Integer[n];
            Integer[] b = new Integer[k];

            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            for (int i = 0; i < k; i++) b[i] = sc.nextInt();

            Arrays.sort(a, Collections.reverseOrder()); // descending
            Arrays.sort(b); // ascending

            long total = 0;
            int j = 0;

            for (int i = 0; i < Math.min(k, n); i++) {
                total += a[i]; // pay for one expensive product
                j += (b[i] - 1);
            }

            int start = Math.min(k + j, n);
            for (int i = start; i < n; i++) {
                total += a[i];
            }

            System.out.println(total);
        }

        sc.close();
    }
}
