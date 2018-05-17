package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebPageController {

    @RequestMapping("/mypage")
    public String  showPage(){

        return "mypage";
    }

}
