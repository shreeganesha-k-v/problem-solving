package bitmanipulation;

public class SingleNumber {
    public static void main(String[] args) {
        int[] arr = {2,2,1};
        int[] arr2 = {4,1,2,1,2};

        System.out.println(findSingleNumber(arr));
        System.out.println(findSingleNumber(arr2));

    }

    private static int findSingleNumber(int[] arr){
        int res = 0;

        for(int num : arr){
            res ^= num;
        }
        return res;
    }
}
