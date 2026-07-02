package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKMostFrequentWords {
    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;

        Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.<Map.Entry<String,Long>>comparingLong(Map.Entry::getValue).thenComparing(Map.Entry::getKey))
                .limit(k)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}
