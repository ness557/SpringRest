package controller;

import model.Student;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


        ModelAndView mnv = new ModelAndView("studentform", "student message", "Hello");
        mnv.addObject("student", new Student(1, "example name", "example group"));
        mnv.addObject("abc", "abc");


        return mnv;
    }
}
