package ness.controller;

import ness.model.Student;
import ness.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StudentRestController {

    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // students
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Object getStudent(@RequestParam(value = "id", required = false) Integer id) {

        if (id != null)
            return studentService.getStudentById(id);
        else
            return studentService.getStudents();

    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity putStudent(@RequestBody Student student) {

        if(studentService.addStudent(student) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/students", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@RequestParam(value = "id") int id){

        if(studentService.removeStudent(id) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);

    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity updateStudent(@RequestBody Student student){

        if(studentService.updateStudent(student) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}