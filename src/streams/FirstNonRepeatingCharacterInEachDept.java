package streams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacterInEachDept {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Aman", "IT"),
                new Employee(2, "Akash", "IT"),
                new Employee(3, "Neha", "HR"),
                new Employee(4, "Nitin", "HR"),
                new Employee(5, "Riya", "Finance")
        );

        Map<String, Character> res = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.collectingAndThen(
                        Collectors.mapping(Employee::name, Collectors.joining()),
                        names ->{
                            Map<Character,Long> charCount = names.chars()
                                    .mapToObj(c -> (char) c)
                                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

                            return charCount.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == 1)
                                    .map(Map.Entry::getKey)
                                    .findFirst()
                                    .orElse(null);
                        }
                )));
        res.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        System.out.println("-----------------------------");
        // Second approach using LinkedHashMap to maintain insertion order
        Map<String, Character> res2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.collectingAndThen(
                        Collectors.mapping(Employee::name, Collectors.joining()),
                        names -> {
                            Map<Character, Long> charCount = names.chars()
                                    .mapToObj(c -> (char) c)
                                    .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));

                            return charCount.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == 1)
                                    .map(Map.Entry::getKey)
                                    .findFirst()
                                    .orElse(null);
                        }
                )));
         res2.forEach((k, v) ->
                System.out.println(k + " -> " + v));
    }

    record Employee(int id , String name, String department) {}
}
