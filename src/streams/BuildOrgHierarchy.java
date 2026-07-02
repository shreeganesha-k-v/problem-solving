package streams;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildOrgHierarchy {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "CEO", null),
                new Employee(2, "CTO", 1),
                new Employee(3, "CFO", 1),
                new Employee(4, "Dev1", 2),
                new Employee(5, "Dev2", 2),
                new Employee(6, "QA1", 2),
                new Employee(7, "Accountant", 3)
        );

        // 1. Manager -> Reports map

        Map<Integer, List<Employee>> hierarchy = employees.stream()
                .filter(e -> e.managerId() != null)
                .collect(Collectors.groupingBy(Employee::managerId));

        hierarchy.forEach((managerId, reports) ->
                System.out.println(managerId + " -> " + reports));

        System.out.println("-----------------------------------------------");

        //2. Find Employees who donot manage anyone
        List<Employee> nonManagers = employees.stream()
                .filter(e->!hierarchy.containsKey(e.id()))
                .toList();
        System.out.println(nonManagers);
        System.out.println("-----------------------------------------------");

        //Print hierarchy tree
        Employee ceo = employees.stream()
                .filter(e->e.managerId()==null)
                .findFirst().orElseThrow();

        printHierarchy(ceo, hierarchy, "");

        //max depth
        int depth = getDepth(ceo, hierarchy);

        System.out.println("\nMaximum Hierarchy Depth = " + depth);


    }

    private static int getDepth(Employee emp, Map<Integer,List<Employee>> hierarchy){
        List<Employee> reports =
                hierarchy.getOrDefault(emp.id(), Collections.emptyList());

        if (reports.isEmpty()) {
            return 1;
        }

        return 1 + reports.stream()
                .mapToInt(report -> getDepth(report, hierarchy))
                .max()
                .orElse(0);
    }

    private static void printHierarchy(Employee emp,
                                       Map<Integer, List<Employee>> hierarchy,
                                       String indent) {

        System.out.println(indent + emp.name());

        for (Employee report :
                hierarchy.getOrDefault(emp.id(), Collections.emptyList())) {

            printHierarchy(report, hierarchy, indent + "    ");
        }
    }
    record Employee(int id , String name , Integer managerId){}
}
