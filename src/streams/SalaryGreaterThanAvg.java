package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryGreaterThanAvg {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(

                new Employee(1, "Alice", "IT", 90000),
                new Employee(2, "Bob", "IT", 70000),
                new Employee(3, "Charlie", "IT", 80000),

                new Employee(4, "David", "HR", 60000),
                new Employee(5, "Eva", "HR", 75000),

                new Employee(6, "Frank", "Finance", 85000),
                new Employee(7, "George", "Finance", 65000)
        );

        Map<String,Double> deptAvgSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.averagingDouble(Employee::salary)));

        employees.stream()
                .filter(emp -> emp.salary() > deptAvgSalary.get(emp.dept()))
                .forEach(emp -> System.out.println(emp.name() + " from " + emp.dept() + " has salary greater than average: " + emp.salary()));
    }
    record Employee(int id, String name, String dept,  int salary){}
}
