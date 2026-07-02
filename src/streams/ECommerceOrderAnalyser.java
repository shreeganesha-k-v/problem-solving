package streams;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ECommerceOrderAnalyser {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, "Alice", List.of(
                        new Item("Electronics", "Laptop", 80000, 1),
                        new Item("Books", "Java", 500, 2)
                )),

                new Order(2, "Bob", List.of(
                        new Item("Electronics", "Phone", 30000, 1),
                        new Item("Groceries", "Rice", 1000, 5)
                )),

                new Order(3, "Alice", List.of(
                        new Item("Books", "Spring Boot", 700, 1),
                        new Item("Electronics", "Mouse", 1000, 2)
                ))
        );

        // Total amount spend by each customer
        Map<String, Double> totalSpentByCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::customer,
                                Collectors.summingDouble(order ->
                                        order.items().stream()
                                                .mapToDouble(item ->
                                                        item.price() * item.quantity())
                                                .sum()
                                )
                        ));
        totalSpentByCustomer.forEach((k,v)-> System.out.println("Customer "+ k + " total spend "+ v));

        //Find the most expensive product purchased in each category.
        Map<String, String> mostExpensiveProduct = orders.stream()
                .flatMap(order -> order.items().stream())
                .collect(Collectors.groupingBy(Item::category,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Item::price)),
                                opt-> opt.map(Item::productName).orElse(null))));

        System.out.println(mostExpensiveProduct);

        // Group product names by category and sort them by price descending
        Map<String, List<String>> productsByCategory = orders.stream()
                .flatMap(order-> order.items().stream())
                .collect(Collectors.groupingBy(
                        Item::category,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list-> list.stream()
                                        .sorted(Comparator.comparingDouble(Item::price).reversed())
                                        .map(Item::productName)
                                        .toList())
                ));
        System.out.println(productsByCategory);

        // Top 2 customer by spending
        /*orders.stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.summingDouble(Order::total)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(2)
                .toList();*/
    }
    record Item( String category,String productName, double price, int quantity){}
    record Order( int orderId, String customer, List<Item>items){}
}
