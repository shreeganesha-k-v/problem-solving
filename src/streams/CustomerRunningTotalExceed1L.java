package streams;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerRunningTotalExceed1L {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("Alice", LocalDate.of(2025,1,5), 20000),
                new Transaction("Bob", LocalDate.of(2025,1,7), 50000),
                new Transaction("Alice", LocalDate.of(2025,1,10), 30000),
                new Transaction("Charlie", LocalDate.of(2025,1,12), 40000),
                new Transaction("Alice", LocalDate.of(2025,2,1), 60000),
                new Transaction("Bob", LocalDate.of(2025,2,5), 40000),
                new Transaction("Charlie", LocalDate.of(2025,2,15), 70000)
        );

        /*
        Sorting: The transactions are sorted by date using Comparator.comparing(Transaction::date).
        Short-Circuiting: The takeWhile operation ensures that the stream stops processing as soon as a winner is found.
        Processing: The forEachOrdered operation iterates over the transactions in chronological order, updating the running total for each customer in the runningTotal map.
        Winner Selection: If a customer's running total exceeds 100,000, their name is stored in the winner using winner.set(t.customer()).
        */

        Map<String, Double> runningTotal = new HashMap<>();
        AtomicReference<String> winner = new AtomicReference<>(); // Using atomic refrence so as to modify within lambda function , normal variables cant be modified as they have to be effectively final

        transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .takeWhile(t -> winner.get() == null)
                .forEachOrdered(t -> {
                    double total = runningTotal.getOrDefault(t.customer(),0.0) + t.amount();
                    runningTotal.put(t.customer(), total);

                    if(total > 100000){
                        winner.set(t.customer());
                    }
                });
        System.out.println(winner.get());
    }

    record Transaction( String customer,
                        LocalDate date,
                        double amount){}
}
