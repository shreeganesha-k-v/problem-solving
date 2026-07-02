package streams;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 3, 7, 1);

        arr.stream()
                .filter(num -> arr.indexOf(num) != arr.lastIndexOf(num))
                .distinct()
                .forEach(System.out::println);
        //Appraoch 2 using groupingBy

        arr.stream()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        //Approach 3 using HashSet
        Set<Integer> seen = new HashSet<>();

        arr.stream()
                .filter(num-> !seen.add(num))
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        //Using Collections.frequency
        arr.stream()
                .filter(num->Collections.frequency(arr,num)>1)
                .distinct()
                .forEach(System.out::println);
    }
}
