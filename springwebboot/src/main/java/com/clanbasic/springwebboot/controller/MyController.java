package com.clanbasic.springwebboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/message")
    public String message() {

        return "showMessage";
    }
}
