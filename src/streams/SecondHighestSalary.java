package streams;

import java.util.List;
import java.util.stream.Collectors;

public class SecondHighestSalary {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "IT", 90000),
                new Employee(2, "Bob", "IT", 80000),
                new Employee(3, "Charlie", "IT", 95000),

                new Employee(4, "David", "HR", 70000),
                new Employee(5, "Eva", "HR", 75000),

                new Employee(6, "Frank", "Finance", 85000),
                new Employee(7, "George", "Finance", 92000),
                new Employee(8, "Helen", "Finance", 88000)
        );

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,

                        Collectors.collectingAndThen(
                                Collectors.toList(),

                                list -> list.stream()
                                        .sorted((e1, e2) ->
                                                Integer.compare(e2.salary, e1.salary))
                                        .skip(1)
                                        .findFirst()
                                        .orElse(null)
                        )
                )).entrySet().forEach((entry) -> {
                    String dept = entry.getKey();
                    Employee secondHighest = entry.getValue();
                    if (secondHighest != null) {
                        System.out.println("Department: " + dept + ", Second Highest Salary: " + secondHighest.salary);
                    } else {
                        System.out.println("Department: " + dept + ", No second highest salary found.");
                    }
                });

    }
    record Employee( int id, String name,String dept, int salary){}
}
