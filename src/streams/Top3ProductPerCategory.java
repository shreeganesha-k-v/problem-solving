package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Top3ProductPerCategory {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, List.of(
                        new OrderItem("Electronics","Laptop",1,70000),
                        new OrderItem("Electronics","Mouse",2,500),
                        new OrderItem("Books","Java",3,600)
                )),
                new Order(2, List.of(
                        new OrderItem("Electronics","Laptop",2,70000),
                        new OrderItem("Books","Spring",2,800),
                        new OrderItem("Books","Java",1,600)
                )),
                new Order(3, List.of(
                        new OrderItem("Electronics","Keyboard",5,1200),
                        new OrderItem("Books","DSA",4,500)
                ))
        );

        // Top 3 item per category
        Map<String, List<String>> result = orders.stream()

                // Convert Stream<Order> -> Stream<OrderItem>
                .flatMap(order -> order.items().stream())

                // Group by category
                .collect(Collectors.groupingBy(
                        OrderItem::category,

                        // After grouping by category
                        Collectors.collectingAndThen(

                                // Group by product and calculate total revenue
                                Collectors.groupingBy(
                                        OrderItem::product,
                                        Collectors.summingDouble(item ->
                                                item.quantity() * item.price())
                                ),

                                // Convert Map<Product, Revenue> -> Top 3 product names
                                productRevenueMap -> productRevenueMap.entrySet()
                                        .stream()

                                        // Revenue descending
                                        .sorted(
                                                Comparator.<Map.Entry<String, Double>>comparingDouble(Map.Entry::getValue)
                                                        .reversed()

                                                        // Product name ascending if revenue ties
                                                        .thenComparing(Map.Entry::getKey)
                                        )

                                        // Top 3
                                        .limit(3)

                                        // Keep only product names
                                        .map(Map.Entry::getKey)

                                        .toList()
                        )
                ));

        System.out.println(result);
    }

    record Order(int id , List<OrderItem> items){}
    record OrderItem(String category,
                     String product,
                     int quantity,
                     double price){}
}
