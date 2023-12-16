package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    @RequestMapping("/")
    public String index(Map<String,Object> model){
        model.put("variable","My Thymelaef variable");
        model.put("one",2);
        model.put("two",2);
        model.put("tree",2);
        return "index";
    }
}
