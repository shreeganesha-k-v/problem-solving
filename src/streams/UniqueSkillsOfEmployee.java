package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UniqueSkillsOfEmployee {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", 90000,
                        List.of("Java", "Spring", "Docker")),

                new Employee(2, "Bob", "IT", 120000,
                        List.of("Java", "Kafka", "AWS")),

                new Employee(3, "Charlie", "IT", 120000,
                        List.of("Java", "Spring", "AWS")),

                new Employee(4, "David", "HR", 85000,
                        List.of("Excel", "Recruitment", "Communication")),

                new Employee(5, "Eva", "HR", 95000,
                        List.of("Excel", "Java", "Analytics")),

                new Employee(6, "Frank", "Finance", 110000,
                        List.of("Java", "SQL", "Excel")),

                new Employee(7, "Grace", "Finance", 125000,
                        List.of("Java", "Python", "AWS")),

                new Employee(8, "Henry", "Finance", 125000,
                        List.of("Java", "Python", "AWS", "Docker"))
        );

        // Find the highest-paid employee in each department.

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.maxBy(
                                Comparator.comparingDouble(Employee::salary)
                                        .thenComparing(
                                                Employee::name,
                                                Comparator.reverseOrder()
                                        )
                        )
                ))
                .forEach((department, employee) ->
                        System.out.println(department + " : " + employee.get()));

        //For the selected employee, list their skills that no other employee in the same department has.
        //Sort those unique skills alphabetically.
        //If there are no unique skills, use an empty list [].

        Map<String, String> result = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,

                        Collectors.collectingAndThen(
                                Collectors.toList(),

                                departmentEmployees -> {

                                    // 1. Find highest-paid employee
                                    Employee topEmployee = departmentEmployees.stream()
                                            .max(
                                                    Comparator.comparingDouble(Employee::salary)
                                                            .thenComparing(
                                                                    Employee::name,
                                                                    Comparator.reverseOrder()
                                                            )
                                            )
                                            .orElseThrow();

                                    // 2. Find skills that belong only to top employee
                                    List<String> uniqueSkills = topEmployee.skills()
                                            .stream()
                                            .filter(skill ->
                                                    departmentEmployees.stream()
                                                            .filter(e -> e.id() != topEmployee.id())
                                                            .noneMatch(e -> e.skills().contains(skill))
                                            )
                                            .sorted()
                                            .toList();

                                    // 3. Create required output
                                    return topEmployee.name()
                                            + " : "
                                            + topEmployee.salary()
                                            + " : "
                                            + uniqueSkills;
                                }
                        )
                ));

        result.forEach((department, value) ->
                System.out.println(department + " -> " + value)
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
