package by.testJazzTeam.robots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {

        return "index";
    }
   /* @RequestMapping(value = "/1index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }*/
}
