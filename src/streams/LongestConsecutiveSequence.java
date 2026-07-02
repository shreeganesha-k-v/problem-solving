package streams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        List<Integer> arr = List.of(100, 4, 200, 1, 3, 2);
        Set<Integer> set = new HashSet<>(arr);

        int res = arr.stream()
                .filter(num -> !set.contains(num - 1))
                .mapToInt(start -> {
                    int curr = start;
                    int count = 1;

                    while (set.contains(curr + 1)) {
                        curr++;
                        count++;
                    }
                    return count;
                })
                .max()
                .orElse(0);

        System.out.println(res);


    }

}
