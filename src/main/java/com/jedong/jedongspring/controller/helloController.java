package com.jedong.jedongspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("name", name);

        return "hello-mvc";
    }

    @GetMapping("hello-response-body")
    @ResponseBody
    public String helloResponseBody(@RequestParam String name) {

        return "hello responseBody - name: " + name;
    }
}
