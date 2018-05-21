package ness.controller;

import ness.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ness.service.StudentService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    private StudentService service;

    @Autowired
    public MyRestController(StudentService studentService) {

        this.service = studentService;
    }


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudent(@RequestParam(value = "id", required = false) Integer id) {

        if (id != null)
            return new LinkedList<Student>() {{
                add(service.getStudentById(id));
            }};
        else
            return service.getStudents();

    }

    @RequestMapping(value = "students", method = RequestMethod.PUT)
    public int putStudent(@RequestParam(value = "id") int id,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "group") String group) {

        return service.addStudent(new Student(id, name, group));
    }

    @RequestMapping(value = "students", method = RequestMethod.DELETE)
    public int deleteStudent(@RequestParam(value = "id") int id){

        return service.removeStudent(id);

    }

    @RequestMapping(value = "students", method = RequestMethod.POST)
    public int updateStudent(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "group") String group){

        return service.updateStudent(new Student(id, name, group));

    }
}

