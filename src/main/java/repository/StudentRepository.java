package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {

    int addStudent(Student student);
    int updateStudent(Student student);
    int removeStudent(Student student);
    int removeStudent(int id);

    List<Student> query(StudentSpecification specification);
}
