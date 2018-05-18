package controller;

import model.Student;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class ProcessStudentController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) {


        String[] arrName =  request.getParameterValues("name");
        String[] arrGroup = request.getParameterValues("group");

        String name = null,
                group = null;
        if(arrName.length > 0)
            name = arrName[0];

        if(arrGroup.length > 0)
            group = arrGroup[0];

        Student student = new Student(1, name, group);

        ModelAndView mnv = new ModelAndView("page-studentprocess");
        mnv.addObject("student", student);

        return mnv;
    }
}
