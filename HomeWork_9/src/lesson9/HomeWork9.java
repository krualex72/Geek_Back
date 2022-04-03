package lesson9;

import java.util.*;
import java.util.stream.Collectors;

class HomeWork9 {
    public static void main(String[] args) {
        List<Student> studentsList = getStudentsList(); // формирование данных студентов вынесены в отдельный метод
        //System.out.println("Студенты " + studentsList);
        List<String> uniqCoursesList = uniqCourses(studentsList);  // Задание 1
        List<String> hiCurious = hiCurious(studentsList); // Задание 2
        Course selectedCourse = new Course(4, "Intensive ESL");
        List<String> listedStudents = listedStudents(studentsList, selectedCourse); // Задание 3
        System.out.println("Уникальные курсы студентов: " + uniqCoursesList);
        System.out.println("3 наиболее любознательных студента: " + hiCurious);
        System.out.println("На курс Intensive ESL записаны студенты " + listedStudents);

    }

    private static List<Student> getStudentsList() {
        List<Course> courses = new ArrayList();
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Ivanov", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(2, "Mathematics"),
                        new Course(7, "Cosmic Technology")))),
                new Student("Petrov", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(2, "Mathematics"),
                        new Course(3, "New Materials"),
                        new Course(4, "Intensive ESL"),
                        new Course(5, "LifeStyle")))),
                new Student("Sidorov", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(3, "New Materials"),
                        new Course(4, "Intensive ESL"),
                        new Course(7, "Cosmic Technology")))),
                new Student("Pupkin", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(7, "Cosmic Technology")))),
                new Student("Bobtchenko", courses = new ArrayList<>(Arrays.asList(
                        new Course(2, "Mathematics"),
                        new Course(4, "Intensive ESL"),
                        new Course(7, "Cosmic Technology")))),
                new Student("Kolomiets", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(2, "Mathematics"),
                        new Course(4, "Intensive ESL"),
                        new Course(6, "Biology")))),
                new Student("Vasiliev", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(2, "Mathematics"),
                        new Course(3, "New Materials"),
                        new Course(4, "Intensive ESL"),
                        new Course(5, "LifeStyle"),
                        new Course(6, "Biology"),
                        new Course(7, "Cosmic Technology")))),
                new Student("Shvonder", courses = new ArrayList<>(Arrays.asList(
                        new Course(1, "Physics"),
                        new Course(7, "Cosmic Technology"))))
        ));
        return students;
    }
    static List<String> uniqCourses (List<Student> students) {
        return students.stream() // стрим из List
                .map(Student::getAllCourses) // списки курсов
                .flatMap(List::stream)
                .map(Course::getCourseName)// достаем названия курсов
                .distinct() // удаляем дубликаты
                .collect(Collectors.toList()); // собираем коллекцию
    }

    static List<String> hiCurious (List<Student> students) {
        return students.stream() // стрим из Lista студентов
                .sorted(Comparator.comparingInt(o -> o.getAllCourses().size() * -1)) // сортируем по количеству курсов
                .limit(3) // устанавливаем лимит 3
                .map(Student::getName) // преобразуем в имена студентов
                .collect(Collectors.toList()); // собираем коллекцию
    }

    static List<String> listedStudents(List<Student> students, Course selectedCourse) {
        return students.stream()// стрим из Lista студентов
                .filter(student -> student.getAllCourses() // намудрил :о)
                        .stream().anyMatch(course -> course.getCourseName() == selectedCourse.getCourseName()))
                .map(Student::getName)
                .collect(Collectors.toList()); // собираем коллекцию
    }
}
