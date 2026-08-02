package recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetThatSumK {
    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();

        int[] arr = {1,2,3};
        int k = 3;
        //recurse(arr, k, 0, new ArrayList<>(), res, 0 );
        backTrack(arr, k, 0, res, new ArrayList<>());

        res.forEach(System.out::println);


    }

    private static void recurse(int[] arr, int k , int index, List<Integer> curr, List<List<Integer>> res,int currSum){
        if(index == arr.length){
            if(currSum == k){
                res.add(new ArrayList<>(curr));
            }
            return;
        }

        curr.add(arr[index]);

        recurse(arr, k , index+1, curr, res, currSum+arr[index]);

        curr.remove(curr.size()-1);

        recurse(arr, k , index+1, curr, res, currSum);

    }

    //include duplicates
    private static void backTrack(int[] arr, int sum , int index , List<List<Integer>> res, List<Integer> curr){
        if(sum == 0){
            res.add(new ArrayList<>(curr));
            return;
        }

        if(index == arr.length || sum < 0){
            return;
        }


        for(int i=index;i<arr.length;i++){
            curr.add(arr[i]);
            backTrack(arr, sum-arr[i],i, res, curr); // backTrack(arr, sum-arr[i],i+1, res, curr)  for no duplciates
            curr.remove(curr.size()-1);
        }
    }
}
