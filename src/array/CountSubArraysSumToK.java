package array;

import java.util.HashMap;
import java.util.Map;

public class CountSubArraysSumToK {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int k = 3;
        System.out.println(countSubArraysSumToK(arr, k));
    }

    private static int countSubArraysSumToK(int[] arr, int k){
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        map.put(0,1); // base case: there is one way to have a sum of 0 (by taking no elements)
        int sum = 0;

        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            if(map.containsKey(sum-k)){
                count += map.get(sum-k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }
        return count;
    }
}
