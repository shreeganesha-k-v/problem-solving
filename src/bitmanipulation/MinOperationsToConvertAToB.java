package bitmanipulation;

public class MinOperationsToConvertAToB {
    public static void main(String[] args) {
        int a = 29; // 11101 in binary
        int b = 15; // 01111 in binary

        System.out.println(minOperations(a, b));
    }

    private static int minOperations(int a, int b){
        int count = 0;
        int xor = a ^ b; // XOR will give us the bits that are different

        while(xor > 0){
            xor &= (xor-1); // Clear the least significant set bit
            count++;
        }
        return count;
    }
}
