package streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeptWiseTopPerformer {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering",
                        List.of(new Project("P1", 8), new Project("P2", 9))),

                new Employee("Bob", "Engineering",
                        List.of(new Project("P3", 10), new Project("P4", 7))),

                new Employee("Charlie", "HR",
                        List.of(new Project("P5", 6), new Project("P6", 8))),

                new Employee("David", "HR",
                        List.of(new Project("P7", 9))),

                new Employee("Eve", "Finance",
                        List.of(new Project("P8", 10), new Project("P9", 10)))
        );

        employees.stream()
                .collect(Collectors.groupingBy(Employee::dept,Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(
                        emp-> emp.projects().stream()
                                .mapToInt(Project::rating)
                                .average()
                                .orElse(0.0)
                )),opt->opt.map(Employee::name).orElse(null))))
                .forEach((k,v)-> System.out.println("Department: " + k + ", Top Performer: " + v));

    }

    record Employee(String name , String dept , List<Project> projects){}

    record Project(String name , int rating){}
}
