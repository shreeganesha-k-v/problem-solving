package streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicatesByMailAndSortBySalary {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(

                new Employee("Ganesh", "a@gmail.com", 90000),
                new Employee("Rahul", "b@gmail.com", 70000),
                new Employee("Amit", "a@gmail.com", 120000),
                new Employee("Sneha", "c@gmail.com", 95000),
                new Employee("Kiran", "b@gmail.com", 85000),
                new Employee("Arun", "d@gmail.com", 65000)
        );

        LinkedHashMap<String,Employee> res = employees.stream()
                .collect(Collectors.toMap(Employee::email, emp->emp,
                        (emp1,emp2)->emp1.salary()>emp2.salary()?emp1:emp2,
                        LinkedHashMap::new))
                .entrySet()
                .stream()
                .sorted((e1,e2)->Double.compare(e2.getValue().salary(),e1.getValue().salary()))
                .collect(Collectors.toMap(
                        e->e.getKey(),
                        e->e.getValue(),
                        (emp1,emp2)->emp1,
                        LinkedHashMap::new
                ));
        res.forEach((email,employee)-> System.out.println("Email: " + email + ", Employee: " + employee.name() + ", Salary: " + employee.salary()));
    }

    record Employee(String name, String email, double salary){}
}
