package streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongestWordInEachSentence {
    public static void main(String[] args) {
        List<String> sentences = List.of("Java streams are powerful",
                "Functional programming is elegant",
                "Practice makes perfect",
                "Code everys single day");

        sentences.stream()
                .collect(Collectors.toMap(Function.identity(),
                        sentence -> {
                            List<String> words = Arrays.stream(sentence.split("\\s+")).toList();

                            int maxLen = words.stream().mapToInt(String::length).max().orElse(0);

                            return words.stream().filter(s->s.length()==maxLen).toList();

                        },
                        (a, b)-> a,
                        LinkedHashMap::new
                        ))
                .forEach((sentence, words) ->
                        System.out.println(sentence + " -> " + words));



    }
}
