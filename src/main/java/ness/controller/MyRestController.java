package ness.controller;

import ness.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import ness.service.StudentService;

//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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


//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        ModelAndView mnv = null;
//        Map<String, String[]> params = request.getParameterMap();
//
//        switch (request.getMethod()) {
//
//            case "GET":
//
//                mnv = new ModelAndView(new MappingJackson2JsonView());
//                if (params.isEmpty()) {
//
//                    for (Student st : service.getStudents()) {
//                        mnv.addObject("student" + st.getId(), st);
//                    }
//
//                } else {
//
//                    Student st = null;
//                    try {
//                        st = service.getStudentById(
//                          Integer.valueOf(
//                                  request.getParameter("id")));
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    if (st != null)
//                        mnv.addObject(st);
//                }
//                break;
//            case "PUT":
//
//                response.setContentType("text/plain");
//
//                if (params.containsKey("id") && params.containsKey("name") && params.containsKey("group")) {
//
//                    try {
//
//                        int id = Integer.valueOf(params.get("id")[0]);
//                        String name = params.get("name")[0];
//                        String group = params.get("group")[0];
//
//                        response.getWriter().write(
//                                String.valueOf(
//                                        service.addStudent(new Student(id, name, group))));
//
//                    } catch (Exception e) {
//                        response.getWriter().write("0");
//                        e.printStackTrace();
//                    }
//
//                } else
//                    response.getWriter().write("0");
//
//                break;
//
//
//            case "DELETE":
//                response.setContentType("text/plain");
//
//                if(params.containsKey("id")){
//
//                    try {
//                        int id = Integer.valueOf(params.get("id")[0]);
//                        response.getWriter().write(
//                                String.valueOf(
//                                        service.removeStudent(id)));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        response.getWriter().write("0");
//                    }
//
//                }
//                else
//                    response.getWriter().write("0");
//
//                break;
//            case "POST":
//
//                response.setContentType("text/plain");
//
//                if (params.containsKey("id") && params.containsKey("name") && params.containsKey("group")) {
//
//                    try {
//
//                        int id = Integer.valueOf(params.get("id")[0]);
//                        String name = params.get("name")[0];
//                        String group = params.get("group")[0];
//
//                        response.getWriter().write(
//                                String.valueOf(
//                                        service.updateStudent(new Student(id, name, group))));
//
//                    } catch (Exception e) {
//                        response.getWriter().write("0");
//                        e.printStackTrace();
//                    }
//
//                } else
//                    response.getWriter().write("0");
//
//                break;
//        }
//
//        return mnv;
//    }
}

