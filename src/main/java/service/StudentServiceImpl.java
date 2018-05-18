package service;

import model.Student;
import repository.StudentRepository;
import repository.StudentSpecification;
import repository.StudentSpecificationAll;
import repository.StudentSpecificationById;

import java.util.List;
import java.util.logging.Logger;

public class StudentServiceImpl implements StudentService {

    private StudentRepository repository;
    private Logger logger = Logger.getLogger(getClass().getName());

    public StudentServiceImpl(StudentRepository repository) {

        this.repository = repository;
    }

    @Override
    public int addStudent(Student student) {
        logger.info("Trying to add student " + student);

        int result = repository.addStudent(student);

        if (result == 1) {
            logger.info("Student added");
        } else {
            logger.info("Student NOT added");
        }
        return result;
    }

    @Override
    public int updateStudent(Student student) {
        logger.info("Trying to update student " + student);

        int result = repository.updateStudent(student);

        if (result == 1) {
            logger.info("Student updated");
        } else {
            logger.info("Student NOT updated");
        }
        return result;
    }

    @Override
    public int removeStudent(Student student) {
        logger.info("Trying to delete student " + student);

        int result = repository.removeStudent(student);

        if (result == 1) {
            logger.info("Student deleted");
        } else {
            logger.info("Student NOT deleted");
        }
        return result;
    }

    @Override
    public int removeStudent(int id) {
        logger.info("Trying to delete student by id = " + id);

        int result = repository.removeStudent(id);

        if (result == 1) {
            logger.info("Student deleted");
        } else {
            logger.info("Student NOT deleted");
        }
        return result;
    }

    @Override
    public Student getStudentById(int id) {

        logger.info("Trying to find student by id = " + id);

        StudentSpecification specification = new StudentSpecificationById(id);
        List<Student> returnedList = repository.query(specification);

        if (!returnedList.isEmpty()) {

            Student student = returnedList.get(0);
            logger.info("Student found: " + student);
            return student;
        }
        logger.info("Student NOT found");
        return null;
    }

    @Override
    public List<Student> getStudents() {

        logger.info("Trying to get student list");

        List<Student> list = repository.query(new StudentSpecificationAll());

        if (list.isEmpty())
            logger.info("List is empty");
        else
            logger.info("Got list");


        return list;
    }
}
