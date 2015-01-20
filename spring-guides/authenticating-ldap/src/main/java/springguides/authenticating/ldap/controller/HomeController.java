package springguides.authenticating.ldap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mengf on 1/20/2015.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Welcome to the home page";
    }
}
