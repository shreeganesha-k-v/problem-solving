package bitmanipulation;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        int a = 5; // 0101 in binary
        int b = 3; // 0011 in binary

        System.out.println("Before swapping: a = " + a + ", b = " + b);
        swap(a, b);
    }

    private static void swap(int a, int b) {
        a = a ^ b; // Step 1: a now holds the result of a XOR b
        b = a ^ b; // Step 2: b now holds the original value of a
        a = a ^ b; // Step 3: a now holds the original value of b

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
