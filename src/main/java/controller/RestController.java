package controller;

import model.Student;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RestController implements Controller {

    private StudentService service;

    public RestController(StudentService studentService) {

        this.service = studentService;
    }


    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mnv = null;
        Map<String, String[]> params = request.getParameterMap();

        switch (request.getMethod()) {

            case "GET":

                mnv = new ModelAndView(new MappingJackson2JsonView());
                if (params.isEmpty()) {

                    for (Student st : service.getStudents()) {
                        mnv.addObject("student" + st.getId(), st);
                    }

                } else {

                    Student st = null;
                    try {
                        st = service.getStudentById(
                          Integer.valueOf(
                                  request.getParameter("id")));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if (st != null)
                        mnv.addObject(st);
                }
                break;
            case "PUT":

                response.setContentType("text/plain");

                if (params.containsKey("id") && params.containsKey("name") && params.containsKey("group")) {

                    try {

                        int id = Integer.valueOf(params.get("id")[0]);
                        String name = params.get("name")[0];
                        String group = params.get("group")[0];

                        response.getWriter().write(
                                String.valueOf(
                                        service.addStudent(new Student(id, name, group))));

                    } catch (Exception e) {
                        response.getWriter().write("0");
                        e.printStackTrace();
                    }

                } else
                    response.getWriter().write("0");

                break;


            case "DELETE":
                response.setContentType("text/plain");

                if(params.containsKey("id")){

                    try {
                        int id = Integer.valueOf(params.get("id")[0]);
                        response.getWriter().write(
                                String.valueOf(
                                        service.removeStudent(id)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("0");
                    }

                }
                else
                    response.getWriter().write("0");

                break;
            case "POST":

                response.setContentType("text/plain");

                if (params.containsKey("id") && params.containsKey("name") && params.containsKey("group")) {

                    try {

                        int id = Integer.valueOf(params.get("id")[0]);
                        String name = params.get("name")[0];
                        String group = params.get("group")[0];

                        response.getWriter().write(
                                String.valueOf(
                                        service.updateStudent(new Student(id, name, group))));

                    } catch (Exception e) {
                        response.getWriter().write("0");
                        e.printStackTrace();
                    }

                } else
                    response.getWriter().write("0");

                break;
        }
        
        return mnv;
    }
}

