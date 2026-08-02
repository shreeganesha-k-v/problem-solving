package bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/*
* Traverse the entire array, performing an XOR operation on all numbers. This will effectively cancel out all the numbers that appear twice, leaving us with the XOR of the two unique numbers.
Determine the rightmost set bit (bit that is 1) in the result from the first step. This set bit can be used to differentiate the two unique numbers since they must differ at this bit position.
Traverse the array again, but this time divide the numbers into two groups:
One group where the numbers have the rightmost set bit.
Another group where the numbers do not have this bit set.
Perform XOR operations while adding numbers in each group. This will cancel out the duplicate numbers, leaving only the unique numbers in each group.
Sort the two unique numbers in ascending order and return them.
* */
public class NumberAppearingTwice {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 2};

        int[] res = numberAppearingTwice(arr);

        for(int num: res){
            System.out.println(num);
        }

    }

    private static int[] numberAppearingTwice(int[] arr){
        int xor = 0;

        for(int num : arr){
            xor ^= num;
        }

        int rightMost = (xor & (xor-1)) ^ xor ;
        int xor1 =0 , xor2 = 0;
        for(int num: arr){
            if((num & rightMost) != 0){
                xor1 ^= num;
            }else{
                xor2 ^= num;
            }
        }

        if(xor1 < xor2)return new int[]{xor1,xor2};

        return new int[]{xor2, xor1};

    }
}
