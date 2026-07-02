package streams;

import java.util.*;
import java.util.stream.Collectors;

public class EmpSalaryGreaterThanDeptAvg {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 90000),
                new Employee(2, "Bob", "IT", 120000),
                new Employee(3, "Charlie", "IT", 70000),

                new Employee(4, "David", "HR", 60000),
                new Employee(5, "Eva", "HR", 80000),

                new Employee(6, "Frank", "Finance", 150000),
                new Employee(7, "George", "Finance", 100000),
                new Employee(8, "Helen", "Finance", 90000)
        );
        Map<String, Double> deptAvg = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.averagingDouble(Employee::salary)));

        Map<String, List<Employee>> res = employees.stream()
                .filter(emp -> emp.salary() > deptAvg.get(emp.department()))
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .collect(Collectors.groupingBy(Employee::department, LinkedHashMap::new, Collectors.toList()));

        res.forEach((dept, empList) -> {
            System.out.println("Department: " + dept);
            empList.forEach(emp -> System.out.println("ID: " + emp.id + ", Name: " + emp.name + ", Salary: " + emp.salary));
        });

    }
    record Employee(int id, String name, String department, double salary){}
}
