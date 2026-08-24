package recursion;

public class CheckIfSubsequenceHasSumK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(solve(0, nums, nums.length, 8));

        int[] nums2 = {4, 3, 9, 2};
        System.out.println(solve(0, nums2, nums2.length, 10));

    }

    private static boolean solve(int i, int[] nums, int n , int k){
        if(k == 0){
            return true;
        }

        if(k < 0){
            return false;
        }

        if(i == n){
            return k == 0;
        }

        return solve(i+1, nums, n, k-nums[i]) || solve(i+1, nums, n, k);
    }
}
