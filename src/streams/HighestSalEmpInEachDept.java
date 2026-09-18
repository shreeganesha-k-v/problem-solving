package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HighestSalEmpInEachDept {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(new Employee("Alice", "HR", 50000.0),
                new Employee("Bob", "IT", 60000.0),
                new Employee("Charlie", "HR", 55000.0),
                new Employee("David", "IT", 65000.0),
                new Employee("Eve", "Finance", 70000.0));

        employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.maxBy(
                                Comparator.comparingDouble(Employee::salary)
                                        .thenComparing(
                                                Employee::name,
                                                Comparator.reverseOrder()
                                        )
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println("Department: " + entry.getKey());

                    entry.getValue().ifPresent(employee ->
                            System.out.println(
                                    "Employee: " + employee.name()
                                            + ", Salary: " + employee.salary()
                            )
                    );
                });

        // Simple or cleaner approach
        Map<String, Employee> result = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::dept,
                        e -> e,
                        (e1, e2) -> {
                            if (e1.salary() > e2.salary()) {
                                return e1;
                            }

                            if (e2.salary() > e1.salary()) {
                                return e2;
                            }

                            return e1.name().compareTo(e2.name()) < 0 ? e1 : e2;
                        }
                ));
    }
    record Employee(String name , String dept , Double salary){}
}
