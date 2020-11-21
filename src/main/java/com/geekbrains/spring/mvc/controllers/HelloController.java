package com.geekbrains.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello()
    {
        return "index";
    }
//    public String hello(Model model){
//        model.addAttribute("message", "new world");
//        return "hello";
//    }

    @RequestMapping(value = "/redirectToIndex",method = RequestMethod.GET )
    public String redirect(){
        return "redirect:/index";
    }
}
