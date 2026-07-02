package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DuplicateEmployeeAcrossProject {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Alice", 100000);
        Employee e2 = new Employee(2, "Bob", 80000);
        Employee e3 = new Employee(3, "Charlie", 90000);
        Employee e4 = new Employee(4, "David", 70000);
        Employee e5 = new Employee(5, "Eve", 95000);

        List<Project> projects = List.of(
                new Project("A", Arrays.asList(e1, e2, e3)),

                new Project("B", Arrays.asList(e2, e4)),

                new Project("C", Arrays.asList(e1, e3, e5))
        );

        List<EmployeeStats> result = projects.stream()
                .flatMap(project-> project.employeeList().stream())
                .collect(Collectors.groupingBy(Employee::id))
                .values()
                .stream()
                .filter(e->e.size()>1)
                .map(list -> {
                    Employee emp = list.getFirst();
                    return new EmployeeStats(emp.name(), list.size(), list.stream().mapToInt(Employee::salary).sum());
                })
                .sorted(Comparator.comparingInt(EmployeeStats::projectCount).reversed().thenComparing(EmployeeStats::salary,
                        Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println(result);
    }

    record Employee(int id , String name, int salary){}

    record Project(String name, List<Employee> employeeList){}

    record EmployeeStats(String name, int projectCount, int salary){
        @Override
        public String toString(){
            return "EmployeeStats{" +
                    "name='" + name + '\'' +
                    ", projectCount=" + projectCount +
                    ", salary=" + salary +
                    '}';
        }
    }
}
