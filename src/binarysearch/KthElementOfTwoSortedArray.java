package binarysearch;

public class KthElementOfTwoSortedArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;
        System.out.println(kthElement(arr1, arr2, k));
    }

    private static int kthElement(int[] arr1,int[] arr2, int k) {
        int low = Math.max(0, k - arr2.length);
        int high = Math.min(k, arr1.length);

        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = k - cut1;

            int left1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int right1 = cut1 == arr1.length ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = cut2 == arr2.length ? Integer.MAX_VALUE : arr2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return -1;
    }
}
