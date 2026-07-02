package streams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatCharacter {
    public static void main(String[] args) {
        String s = "swiss";

        //Approach 1: Using indexOf and lastIndexOf
        char ch = s.chars()
                .mapToObj(c->(char)c)
                .filter(c->s.indexOf(c)==s.lastIndexOf(c))
                .findFirst()
                .orElse('#');

        System.out.println("First non-repeating character: " + ch);

        //Approach 2 : Using groupingBy and LinkedHashMap to maintain order
        ch = s.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue()==1)
                .map(e->e.getKey())
                .findFirst()
                .orElse('#');
        System.out.println("First non-repeating character: " + ch);

        // Non repeat character in a list of Strings
        List<String> words = List.of(
                "apple",
                "banana",
                "orange"
        );

        String joined = String.join("", words);

        Optional<Character> nonRepeat = joined.chars()
                .mapToObj(c->(char)c)
                .filter(c-> joined.indexOf(c)==joined.lastIndexOf(c))
                .findFirst();

        nonRepeat.ifPresent(System.out::println);



    }
}
