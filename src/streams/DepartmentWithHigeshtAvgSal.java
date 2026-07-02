package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentWithHigeshtAvgSal {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "HR", 50000.0),
                new Employee("Bob", "IT", 60000.0),
                new Employee("Charlie", "HR", 55000.0),
                new Employee("David", "IT", 65000.0),
                new Employee("Eve", "Finance", 70000.0)
        );

        String dept = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,Collectors.averagingDouble(Employee::salary)))
                .entrySet()
                .stream()
                .max(Comparator.<Map.Entry<String,Double>>comparingDouble(Map.Entry::getValue))
                //.max(Map.Entry.comparingByValue()) like this also works
                .map(Map.Entry::getKey)
                .orElse("No departments found");

        System.out.println("Department with the highest average salary: " + dept);


    }

    record Employee(String name, String dept, Double salary){   }
}
