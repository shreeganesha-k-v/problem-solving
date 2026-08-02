package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentCourseAnalysis {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "Alice", Arrays.asList(
                        new Course("Java", 40, 12000),
                        new Course("Spring", 35, 15000),
                        new Course("SQL", 20, 6000)
                )),
                new Student(2, "Bob", Arrays.asList(
                        new Course("Java", 40, 12000),
                        new Course("React", 30, 14000)
                )),
                new Student(3, "Charlie", Arrays.asList(
                        new Course("AWS", 50, 20000),
                        new Course("Spring", 35, 15000)
                )),
                new Student(4, "David", Arrays.asList(
                        new Course("Java", 40, 12000),
                        new Course("AWS", 50, 20000),
                        new Course("Docker", 25, 10000)
                ))
        );

        // 1. List all course names (duplicates allowed) , 2 Unique courses
        students.stream()
                .flatMap(student -> student.courses().stream())
                .map(Course::name)
                .distinct() // if u remove this then we can see duplicates
                .forEach(System.out::println);

        // 3. Count total number of course enrollments.
        long total = students.stream()
                .flatMap(student -> student.courses().stream())
                .count();

        System.out.println(total);

        // 4. Find the most expensive course.
        students.stream()
                .flatMap(student -> student.courses().stream())
                .max(Comparator.comparingDouble(Course::fee))
                .ifPresent(System.out::println);

        // 5. Find total fee paid by each student.
        Map<String, Double> totalFee = students.stream()
                .collect(Collectors.toMap(
                        Student::name,
                        student -> student.courses().stream()
                                .mapToDouble(Course::fee)
                                .sum()
                ));

        System.out.println(totalFee);

        // 6. Students enrolled in java
        students.stream()
                .filter(student ->
                        student.courses().stream()
                                .anyMatch(course -> course.name().equals("Java")))
                .map(Student::name)
                .forEach(System.out::println);

        // 7. Students enrolled in each course
        Map<String, Long> count = students.stream()
                .flatMap(student -> student.courses().stream())
                .collect(Collectors.groupingBy(
                        Course::name,
                        Collectors.counting()
                ));

        System.out.println(count);

        // 8 . Average course fee
        double avg = students.stream()
                .flatMap(student -> student.courses().stream())
                .collect(Collectors.averagingDouble(Course::fee));

        System.out.println(avg);

        // 9. Total learning hourse per student
        Map<String, Integer> learningHours = students.stream()
                .collect(Collectors.toMap(
                        Student::name,
                        student -> student.courses().stream()
                                .mapToInt(Course::duration)
                                .sum()
                ));

        System.out.println(learningHours);

        // 10. Student spending highest fee
        students.stream()
                .max(Comparator.comparingDouble(student ->
                        student.courses().stream()
                                .mapToDouble(Course::fee)
                                .sum()))
                .ifPresent(System.out::println);



    }

    record Course(String name , int duration , double fee){}
    record Student(int id, String name, List<Course> courses){}
}
