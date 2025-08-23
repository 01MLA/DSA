package problemSets;

public class NewClass {

    public static void main(String[] args) {
        System.out.println(NewClass.powerOfTwoIT(10));
        System.out.println(NewClass.sumOfDigits(23341));
        System.out.println(NewClass.powerOf2(10));
    }

    static int powerOfTwo(int n) {
        if (n == 0) {
            return 1;
        } else {
            var power = 2 * powerOfTwo(n - 1);
            return power;
        }
    }

    static int powerOf2(int num) {
        int power = 1;
        if (num == 0) {
            return 1;
        }
        power = 2 * powerOf2(num - 1);
        return power;
    }

    static int powerOfTwoIT(int n) {
        var i = 1;
        var power = 1;
        while (i <= n) {
            System.out.print(i + " ");
            power = power * 2;
            i++;
        }
        return power;
    }

    static int sumOfDigits(int num) {
        int sum = 0;
        if (num == 0) {
            return 0;
        }
        sum = num % 10 + sumOfDigits(num / 10);
        return sum;
    }

    // GCD
    static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    
}
