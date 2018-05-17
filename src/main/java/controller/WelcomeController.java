package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) {

        return new ModelAndView("mypage", "mypage message", "Hello");
    }
}
