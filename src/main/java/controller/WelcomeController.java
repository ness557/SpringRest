package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) {

        return new ModelAndView("page-mypage", "mypage message", "Hello");
    }
}
