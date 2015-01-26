package springguides.hateoas.controller;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springguides.hateoas.model.Greeting;

/**
 * Created by meng on 1/26/15.
 */
@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name){
        Greeting greeting = new Greeting("Hello " + name);
        greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());
        return greeting;
    }
}
