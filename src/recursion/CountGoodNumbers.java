package recursion;

/*A digit string is considered good if the digits at even indices (0-based) are even digits (0, 2, 4, 6, 8) and the digits at odd indices are prime digits (2, 3, 5, 7).

Given an integer n, return the total number of good digit strings of length n. As the result may be large, return it modulo 109 + 7.

A digit string is a string consisting only of the digits '0' through '9'. It may contain leading zeros.*/
public class CountGoodNumbers {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(countGoodNumbers(n)); // Output: 400
    }

    private static int countGoodNumbers(int n){
        long mod = 1000000007;
        long evenCount = (n + 1) / 2; // Count of even indices
        long oddCount = n / 2; // Count of odd indices

        long evenChoices = 5; // Even digits: 0, 2, 4, 6, 8
        long oddChoices = 4; // Prime digits: 2, 3, 5, 7

        long totalGoodNumbers = (modularExponentiation(evenChoices, evenCount, mod) * modularExponentiation(oddChoices, oddCount, mod)) % mod;

        return (int) totalGoodNumbers;
    }

    private static int modularExponentiation(long base, long exponent, long mod) {
        long result = 1;
        base = base % mod;

        while (exponent > 0) {
            if ((exponent & 1) == 1) { // If exponent is odd
                result = (result * base) % mod;
            }
            exponent = exponent >> 1; // Divide exponent by 2
            base = (base * base) % mod; // Square the base
        }

        return (int) result;
    }
}
