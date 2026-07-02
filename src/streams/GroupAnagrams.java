package streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public static void main(String[] args) {
            String[] strs = {"eat","tea","tan","ate","nat","bat"};

            Arrays.stream(strs)
                    .collect(Collectors.groupingBy(str->{
                        char[] chars = str.toCharArray();
                        Arrays.sort(chars);
                        return new String(chars);
                    }))
                    .entrySet()
                    .stream()
                    .map(entry->entry.getValue())
                    .forEach(System.out::println);
    }
}
