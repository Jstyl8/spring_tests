package com.clanbasic;

import com.clanbasic.service.AddService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddController {
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {

        int i = Integer.parseInt(request.getParameter("t1"));
        int j = Integer.parseInt(request.getParameter("t2"));

        AddService addService = new AddService();
        int k = addService.add(i, j);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("display");
        mv.addObject("result", k);
        return mv;
    }
}
