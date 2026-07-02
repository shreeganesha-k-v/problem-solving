package array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestSequence {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longestSequence(arr));
    }
    private static int longestSequence(int[] arr){
        Set<Integer> set = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toSet());

        return set.stream()
                .filter(num -> !set.contains(num-1))
                .mapToInt(num->{
                    int count = 1;
                    while(set.contains(num+1)){
                        count++;
                        num++;
                    }
                    return count;
                })
                .max().getAsInt();

    }
}
