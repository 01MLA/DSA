package interviewProblems;

public class Recursion {

    int sumOfDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumOfDigits(n / 10);
    }

    int power(int num, int pow) {
        if (pow == 0) return 1;
        return num * power(num, pow - 1);
    }

    int gcd(int a, int b) {
        if (a < 0 || b < 0) return -1;
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    int decimalToBinary(int x) {
        if (x == 0) return 0;
        return x % 2 + decimalToBinary(x / 2);
    }

    public static void main(String[] args) {

    }
}
