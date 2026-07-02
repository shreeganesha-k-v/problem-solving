package bitmanipulation;

public class SetOrUnsetRightMostBit {
    // https://takeuforward.org/data-structure/set-the-rightmost-bit
    public static void main(String[] args) {
        int n =10; // Binary representation: 1010
        int setRightMostBit = n | (n + 1); // Set the rightmost unset bit
        int unsetRightMostBit = n & (n - 1); // Unset the rightmost set bit
        System.out.println("Original number: " + n + " (binary: " + Integer.toBinaryString(n) + ")");
        System.out.println("After setting the rightmost unset bit: " + setRightMostBit + " (binary: " + Integer.toBinaryString(setRightMostBit) + ")");
        System.out.println("After unsetting the rightmost set bit: " + unsetRightMostBit + " (binary: " + Integer.toBinaryString(unsetRightMostBit) + ")");
    }

}
