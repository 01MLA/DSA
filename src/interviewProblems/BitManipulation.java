package interviewProblems;

public class BitManipulation {

    /**
     * a  = 0010
     * 1  = 0001
     * ------------
     * a & 1 = 0000 → 0
     * ✅ Result = 0 → means even
     */
    void oddOrEven(int a) {
        if ((a & 1) == 1) System.out.println("Odd");
        else System.out.println("Even");
    }

    public static void main(String[] args) {
        BitManipulation bitM = new BitManipulation();
        bitM.oddOrEven(2);
    }
}
