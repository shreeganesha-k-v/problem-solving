package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostProfitableProductPerRegion {
    public static void main(String[] args) {
        List<Sale> sales = List.of(

                // North
                new Sale("North", "Alice", "Laptop", 2, 60000),
                new Sale("North", "Bob", "Laptop", 1, 60000),
                new Sale("North", "Alice", "Mouse", 20, 700),
                new Sale("North", "David", "Monitor", 5, 15000),

                // South
                new Sale("South", "John", "Mobile", 10, 35000),
                new Sale("South", "Sam", "Mobile", 5, 35000),
                new Sale("South", "John", "Keyboard", 25, 2000),
                new Sale("South", "Sam", "Laptop", 2, 60000),

                // East
                new Sale("East", "Kevin", "TV", 8, 50000),
                new Sale("East", "Kevin", "AC", 6, 42000),
                new Sale("East", "Mary", "TV", 2, 50000),
                new Sale("East", "Mary", "Mouse", 30, 700),

                // West
                new Sale("West", "Steve", "AC", 10, 42000),
                new Sale("West", "Steve", "Laptop", 4, 60000),
                new Sale("West", "Chris", "AC", 3, 42000),
                new Sale("West", "Chris", "Chair", 50, 2500)
        );


        sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::region,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Sale::product,
                                        Collectors.summingDouble(s -> s.quantity() * s.price())
                                ),
                                map -> map.entrySet()
                                        .stream()
                                        .max(Map.Entry.comparingByValue())
                                        .orElseThrow()
                        )
                )).forEach((k,v)-> System.out.println("Region : " + k + " -> " + "product " + v));
    }

    record Sale(String region,
                String salesperson,
                String product,
                int quantity,
                double price){}
}
