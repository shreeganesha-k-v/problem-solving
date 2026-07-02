package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MergeOverlappingInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        List<int[]> merged = Arrays.stream(intervals)

                // Sort by start time
                .sorted(Comparator.comparingInt(a -> a[0]))

                // Custom collector
                .collect(
                        ArrayList::new,

                        (list, current) -> {

                            if (list.isEmpty()) {
                                list.add(current);
                            } else {

                                int[] last = list.get(list.size() - 1);

                                // Overlap exists
                                if (current[0] <= last[1]) {

                                    last[1] = Math.max(last[1], current[1]);

                                } else {

                                    list.add(current);
                                }
                            }
                        },

                        (list1, list2) -> {
                            throw new UnsupportedOperationException();
                        }
                );
        merged.forEach(a-> System.out.println(Arrays.toString(a)));

    }
}
