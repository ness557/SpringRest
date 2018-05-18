package service;

import model.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);
    int updateStudent(Student student);
    int removeStudent(Student student);
    int removeStudent(int id);
    Student getStudentById(int id);
    List<Student> getStudents();

}
