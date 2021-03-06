package springguides.validation.forminput.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springguides.validation.forminput.model.Person;

import javax.validation.Valid;

/**
 * Created by meng on 1/20/15.
 */
@Controller
public class WebController extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Person person){
        return "form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
        return "redirect:/results";
    }
}
