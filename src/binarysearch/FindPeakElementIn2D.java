package binarysearch;

public class FindPeakElementIn2D {
    // https://takeuforward.org/data-structure/find-peak-element-2d-matrix
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {5, 10, 8}, {4, 25, 7}, {3, 9, 6}
        };
        System.out.println(findPeak(arr)[0] + " " + findPeak(arr)[1]);
    }

    private static int[] findPeak(int[][] arr){
        int low = 0;
        int high = arr.length - 1;

        while( low <= high){
            int mid = low + (high-low)/2;
            int row = findMax(arr[mid]);

            int left = mid - 1 >= 0? arr[mid-1][row] : Integer.MIN_VALUE;
            int right = mid + 1 < arr.length? arr[mid+1][row] : Integer.MIN_VALUE;

            if(arr[row][mid] > left && arr[row][mid] > right) {
                return new int[]{row, mid};
            }else if(left > arr[row][mid]){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    private static int findMax(int[] arr){
        int max = Integer.MIN_VALUE;
        int index = -1;

        for(int i=0;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
}
