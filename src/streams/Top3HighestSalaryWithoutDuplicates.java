package streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Top3HighestSalaryWithoutDuplicates {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(

                new Employee(1, "Rohit", "IT", 120000),
                new Employee(2, "Aman", "IT", 95000),
                new Employee(3, "Neha", "IT", 150000),
                new Employee(4, "Kiran", "IT", 99000),
                new Employee(5, "Simran", "IT", 88000),

                new Employee(6, "Raj", "HR", 70000),
                new Employee(7, "Pooja", "HR", 85000),
                new Employee(8, "Ankit", "HR", 65000),
                new Employee(9, "Zara", "HR", 92000),

                new Employee(1, "Rohit", "IT", 120000)
        );

        Collection<Employee> uniqueEmployees = employees.stream().collect(Collectors.toMap(Employee::id, emp->emp, (e1,e2)->e1)).values();

        uniqueEmployees.stream()
                .sorted(Comparator.comparingInt(Employee::salary).reversed().thenComparing(Employee::dept))
                .limit(3)
                .forEach(emp -> System.out.println("ID: " + emp.id + ", Name: " + emp.name + ", Department: " + emp.dept + ", Salary: " + emp.salary));
    }
    record Employee(int id, String name,String dept, int salary){  }
}
