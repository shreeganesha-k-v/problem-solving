package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HighestSalaryEmpInEachDept {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "HR", 50000),
                new Employee("Bob", "HR", 60000),
                new Employee("Charlie", "IT", 70000),
                new Employee("David", "IT", 80000),
                new Employee("Eve", "Finance", 55000)
        );

        employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.maxBy(Comparator.comparingInt(Employee::salary))))
                .forEach((dept, emp) -> System.out.println("Department: " + dept + ", Employee: " + emp.orElse(null)));

        // Approach 2 using Collectors.collectingAndThen
        employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,Collectors.collectingAndThen(Collectors.maxBy((e1,e2)->Integer.compare(e1.salary(),e2.salary())),emp -> emp.orElse(null))))
                .forEach((dept, emp) -> System.out.println("Department: " + dept + ", Employee: " + emp));
    }
    record Employee(String name, String dept, int salary){}
}
