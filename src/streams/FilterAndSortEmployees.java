package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*Using Java Streams, find the top 2 highest-paid employees in each department, subject to these rules:

Employee must have Java as a skill.
Sort employees within each department by:
Salary descending
If salary is equal → name alphabetically
Return: Map<String, List<String>>*/
public class FilterAndSortEmployees {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", 90000,
                        List.of("Java", "Spring", "Docker")),

                new Employee(2, "Bob", "IT", 120000,
                        List.of("Java", "Kafka", "AWS")),

                new Employee(3, "Charlie", "IT", 120000,
                        List.of("Java", "Spring", "AWS")),

                new Employee(4, "David", "HR", 85000,
                        List.of("Excel", "Recruitment")),

                new Employee(5, "Eva", "HR", 95000,
                        List.of("Excel", "Java", "Analytics")),

                new Employee(6, "Frank", "Finance", 110000,
                        List.of("Java", "SQL", "Excel")),

                new Employee(7, "Grace", "Finance", 125000,
                        List.of("Java", "SQL", "Python")),

                new Employee(8, "Henry", "Finance", 125000,
                        List.of("Java", "Python", "AWS")),

                new Employee(9, "Ivy", "IT", 105000,
                        List.of("Java", "Docker", "Kubernetes"))
        );

        Map<String, List<String>> result = employees.stream()
                .filter(e -> !e.skills().isEmpty() && e.skills().contains("Java"))
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(
                                                Comparator.comparingDouble(Employee::salary)
                                                        .reversed()
                                                        .thenComparing(Employee::name)
                                        )
                                        .limit(2)
                                        .map(Employee::name)
                                        .toList()
                        )
                ));

        result.forEach((department, names) ->
                System.out.println(department + " : " + names)
        );
    }

    record Employee(
            int id,
            String name,
            String department,
            double salary,
            List<String> skills
    ) {}
}
