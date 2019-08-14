package com.clanbasic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddController {
    @RequestMapping("/add")
    public String add() {
        System.out.println("adding");
        return "display.jsp";
    }
}
