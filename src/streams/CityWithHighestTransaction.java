package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityWithHighestTransaction {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("Alice", "Bangalore", 2023, 1000),
                new Transaction("Alice", "Bangalore", 2024, 2500),
                new Transaction("Alice", "Bangalore", 2022, 2500),
                new Transaction("Bob", "Mumbai", 2023, 4000),
                new Transaction("Bob", "Mumbai", 2024, 1000),
                new Transaction("Charlie", "Bangalore", 2024, 3000),
                new Transaction("Charlie", "Bangalore", 2023, 1500),
                new Transaction("David", "Mumbai", 2022, 2000),
                new Transaction("Bob", "Mumbai", 2022, 5000),
                new Transaction("Alice", "Bangalore", 2021, 800)
        );

        // City with highest total transaction
        String topCity = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::city,
                        Collectors.summingInt(Transaction::value)
                ))
                .entrySet()
                .stream()
                .max(
                        Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                                .thenComparing(Map.Entry::getKey, Comparator.reverseOrder())
                )
                .map(Map.Entry::getKey)
                .orElseThrow();
        System.out.println("Top city is : " + topCity);

        // Step 2: In that city, find trader with highest total value
        String topTrader = transactions.stream()
                .filter(t -> t.city().equals(topCity))
                .collect(Collectors.groupingBy(
                        Transaction::trader,
                        Collectors.summingInt(Transaction::value)
                ))
                .entrySet()
                .stream()
                .max(
                        Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                                .thenComparing(Map.Entry::getKey, Comparator.reverseOrder())
                )
                .map(Map.Entry::getKey)
                .orElseThrow();

        // Step 3: Get top 5 transactions of that trader
        Map<String, List<Transaction>> result = Map.of(
                topTrader,
                transactions.stream()
                        .filter(t -> t.trader().equals(topTrader))
                        .sorted(
                                Comparator.comparingInt(Transaction::value)
                                        .reversed()
                                        .thenComparing(Transaction::year)
                        )
                        .limit(5)
                        .toList()
        );

        result.forEach((trader, txns) -> {
            System.out.println("Trader: " + trader);
            txns.forEach(System.out::println);
        });
    }

    record Transaction(String trader,
            String city,
            int year,
            int value){}
}
