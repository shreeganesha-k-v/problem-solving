package array;

import java.util.HashMap;

public class LongestSubArrayWithSumK {
    public static void main(String[] args) {
        int[] arr = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(longestSubArrayWithSumK(arr, k));
    }

    private static int longestSubArrayWithSumK(int[] arr, int k){
        int maxLength = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            if(sum == k){
                maxLength = i + 1;
            }

            if(!map.containsKey(sum)){
                map.put(sum, i);
            }

            if(map.containsKey(sum - k)){
                maxLength = Math.max(maxLength, i - map.get(sum - k));
            }
        }
        return maxLength;
    }
}
