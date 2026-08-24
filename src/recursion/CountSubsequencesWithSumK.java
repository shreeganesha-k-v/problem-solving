package recursion;

public class CountSubsequencesWithSumK {
    public static void main(String[] args) {
        int[] nums = {4, 9, 2, 5, 1};
        System.out.println(solve( nums,0, nums.length, 10));

        int[] nums2 = {4, 2, 10, 5, 1, 3};
        System.out.println(solve( nums2, 0,nums2.length, 5));
    }

    private static int solve(int[] nums , int i, int n, int k){
        if(k == 0)return 1;

        if(k < 0 || i == n)return 0;

        return solve(nums, i+1, n,k-nums[i]) + solve(nums, i+1, n, k);
    }
}
