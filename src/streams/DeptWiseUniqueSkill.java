package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DeptWiseUniqueSkill {
    public static void main(String[] args) {
        List<Employee> employees = List.of(

                new Employee("Alice", "Engineering",
                        List.of("Java", "Spring", "Docker")),

                new Employee("Bob", "Engineering",
                        List.of("Java", "Kafka", "Docker")),

                new Employee("Charlie", "HR",
                        List.of("Recruitment", "Excel")),

                new Employee("David", "HR",
                        List.of("Excel", "Communication")),

                new Employee("Eva", "Engineering",
                        List.of("Spring", "AWS"))
        );

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .flatMap(e -> e.skills().stream())
                                        .distinct()
                                        .sorted()
                                        .collect(Collectors.toList())
                        )))
                .forEach((k, v) -> System.out.println(k + " : " + v));

        // Solution 2 using Collectors.flatMapping
        Map<String, List<String>> result =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::department,
                                Collectors.flatMapping(
                                        e -> e.skills().stream(),
                                        Collectors.collectingAndThen(
                                                Collectors.toCollection(TreeSet::new),
                                                ArrayList::new
                                        )
                                )
                        ));
        result.forEach((k, v) -> System.out.println(k + " : " + v));
    }
    record Employee(
            String name,
            String department,
            List<String> skills) {
    }
}
