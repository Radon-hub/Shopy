package org.radon.shopy.shared.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/public")
    public String landing() {
        return "Landing.html";
    }

    @RequestMapping("/public/home")
    public String home() {
        return "Home.html";
    }

}
