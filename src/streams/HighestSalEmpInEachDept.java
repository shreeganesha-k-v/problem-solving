package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HighestSalEmpInEachDept {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(new Employee("Alice", "HR", 50000.0),
                new Employee("Bob", "IT", 60000.0),
                new Employee("Charlie", "HR", 55000.0),
                new Employee("David", "IT", 65000.0),
                new Employee("Eve", "Finance", 70000.0));

        employeeList.stream()
                .collect(Collectors.groupingBy(Employee::dept,Collectors.maxBy(Comparator.comparingDouble(Employee::salary))))
                .entrySet()
                .stream()
                .sorted((a,b)->a.getKey().compareTo(b.getKey()))
                .forEach(entry->{
                    System.out.println("Department: " + entry.getKey());
                    entry.getValue().ifPresent(employee -> System.out.println("Employee: " + employee.name() + ", Salary: " + employee.salary()));
                });
    }
    record Employee(String name , String dept , Double salary){}
}
