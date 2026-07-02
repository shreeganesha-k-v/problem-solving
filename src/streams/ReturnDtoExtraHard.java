package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReturnDtoExtraHard {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 28, 120000,
                        List.of("Java", "Spring", "AWS")),

                new Employee("Bob", "Engineering", 35, 150000,
                        List.of("Java", "Kafka", "Docker")),

                new Employee("Charlie", "HR", 30, 70000,
                        List.of("Communication", "Recruitment")),

                new Employee("David", "Engineering", 40, 180000,
                        List.of("Java", "Kubernetes", "AWS")),

                new Employee("Eve", "Finance", 32, 95000,
                        List.of("Excel", "Accounting")),

                new Employee("Frank", "Finance", 29, 110000,
                        List.of("Excel", "SQL"))
        );

        // 1. Department stats
        Map<String, DepartmentStats> depStats = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            double avgSalary = list.stream().mapToDouble(Employee::salary).average().orElse(0);
                            String highestPaid = list.stream().max(Comparator.comparingDouble(Employee::salary)).map(Employee::name).orElse("");
                            long count = list.size();
                            return new DepartmentStats(avgSalary, highestPaid, count);
                        })));
        depStats.forEach((k,v)-> System.out.println("Department : " + k + " Stats " + v));

        // 2. Top 3 most common skills
        List<Map.Entry<String, Long>> topSkills = employees.stream()
                .flatMap(e-> e.skills().stream())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<String, Long>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .limit(3)
                .toList();
        System.out.println("\nTop Skills:");
        topSkills.forEach(System.out::println);

        // ---------------------------------------------------
        // 3. Partition employees by salary >= 100000
        // ---------------------------------------------------

        Map<Boolean, List<String>> partitioned =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                emp -> emp.salary() >= 100000,
                                Collectors.mapping(
                                        Employee::name,
                                        Collectors.toList()
                                )
                        ));

        System.out.println("\nPartitioned:");
        partitioned.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        // ---------------------------------------------------
        // Bonus:
        // Map<Department, Map<Salary>=100000, Names>>
        // ---------------------------------------------------

        Map<String, Map<Boolean, List<String>>> result =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::department,
                                Collectors.partitioningBy(
                                        emp -> emp.salary() >= 100000,
                                        Collectors.mapping(
                                                Employee::name,
                                                Collectors.toList()
                                        )
                                )
                        ));

        System.out.println("\nDepartment + Salary Partition:");

        result.forEach((dept, map) -> {
            System.out.println(dept);
            map.forEach((key, names) ->
                    System.out.println("   " + key + " -> " + names));
        });

    }
    record Employee(String name, String department, int age,
                    double salary, List<String> skills){}
    record DepartmentStats(
            double averageSalary,
            String highestPaidEmployee,
            long employeeCount) {
    }

}
