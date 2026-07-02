package bitmanipulation;

public class CountSetBits {
    public static void main(String[] args) {
        System.out.println(countSetBits(5)); // Output: 2 (binary representation of 5 is 101)
        System.out.println(countSetBits(7)); // Output: 3 (binary representation of 7 is 111)
        System.out.println(countSetBits(0)); // Output: 0 (binary representation of 0 is 0)
        System.out.println(countSetBits(15)); // Output: 4 (binary representation of 15 is 1111)
    }

    private static int countSetBits(int n){
        int count = 0;

        while(n > 0){
            n &= (n-1); // Clear the least significant set bit
            count ++;
        }
        return count;
    }
}
