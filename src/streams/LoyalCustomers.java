package streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoyalCustomers {
    public static void main(String[] args) {
        List<Order> orders = List.of(

                new Order(
                        1,
                        "Alice",
                        List.of(
                                new OrderItem("Laptop", 1, 50000),
                                new OrderItem("Mouse", 2, 800)
                        )
                ),

                new Order(
                        2,
                        "Bob",
                        List.of(
                                new OrderItem("Phone", 1, 30000),
                                new OrderItem("Case", 2, 1000)
                        )
                ),

                new Order(
                        3,
                        "Alice",
                        List.of(
                                new OrderItem("Keyboard", 1, 2000),
                                new OrderItem("Laptop", 1, 50000)
                        )
                ),

                new Order(
                        4,
                        "Charlie",
                        List.of(
                                new OrderItem("Book", 5, 500),
                                new OrderItem("Pen", 10, 50)
                        )
                )
        );

        Map<String, CustomerStats> map =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::customer,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {

                                            double totalSpent =
                                                    list.stream()
                                                            .flatMap(order -> order.items().stream())
                                                            .mapToDouble(item -> item.quantity() * item.price())
                                                            .sum();

                                            long distinctProducts =
                                                    list.stream()
                                                            .flatMap(order -> order.items().stream())
                                                            .map(OrderItem::product)
                                                            .distinct()
                                                            .count();

                                            return new CustomerStats(totalSpent, distinctProducts);
                                        }
                                )
                        ));

        List<String> loyalCustomers =
                map.entrySet()
                        .stream()
                        .filter(e -> e.getValue().totalSpent() > 50000)
                        .filter(e -> e.getValue().distinctProduct() >= 3)
                        .sorted((a, b) -> Double.compare(
                                b.getValue().totalSpent(),
                                a.getValue().totalSpent()))
                        .map(Map.Entry::getKey)
                        .toList();

        loyalCustomers.forEach(System.out::println);

    }

    record Order(int orderId,String customer , List<OrderItem> items){}
    record OrderItem(String product, int quantity, double price){}
    record CustomerStats(double totalSpent, long distinctProduct){}
}
