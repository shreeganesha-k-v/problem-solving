package array;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,4};

        int res = removeDuplicates(nums);

        for(int i=0;i<res;i++){
            System.out.println(nums[i]);
        }


    }

    private static int removeDuplicates(int[] nums){
        int i =0;
        for(int j=1; j<nums.length ; j++ ){
            if(nums[i]!=nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
