package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
* Find the word that:

Appears in the maximum number of different sentences
Count a word only once per sentence
Ignore case sensitivity
Ignore punctuation
If multiple words have same frequency, return lexicographically smallest word
* */
public class MostFrequentNonRepeatingWord {
    public static void main(String[] args) {

        List<String> sentences = List.of(
                "Java is great",
                "I love java streams",
                "Streams make JAVA powerful",
                "Java streams are great"
        );

        Map<String, Long> frequencyMap = sentences.stream()

                // normalize sentence
                .map(sentence -> sentence
                        .toLowerCase()
                        .replaceAll("[^a-z ]", ""))

                // unique words per sentence
                .map(sentence -> Arrays.stream(sentence.split("\\s+"))
                        .distinct()
                        .toList())

                // flatten
                .flatMap(List::stream)

                // count occurrences across sentences
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        Map.Entry<String, Long> result = frequencyMap.entrySet()
                .stream()
                .sorted((a, b) -> {

                    // descending frequency
                    int freqCompare = Long.compare(
                            b.getValue(),
                            a.getValue()
                    );

                    // lexicographically smallest
                    if (freqCompare == 0) {
                        return a.getKey().compareTo(b.getKey());
                    }

                    return freqCompare;
                })
                .findFirst()
                .orElse(null);

        System.out.println(result.getKey() + " : " + result.getValue());

    }
}
