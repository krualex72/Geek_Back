package lesson9;

import java.util.Objects;

interface ICourse {
    String getCourseName();
    Integer getCourseId();
}

class Course implements ICourse{
    private String courseName;
    private Integer courseId;

    public Course(Integer courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public Integer getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }
}
