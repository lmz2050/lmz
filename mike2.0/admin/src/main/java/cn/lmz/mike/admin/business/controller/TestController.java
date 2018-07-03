package cn.lmz.mike.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String test(Model model){

        model.addAttribute("title","test");
        return "test";
    }


}
