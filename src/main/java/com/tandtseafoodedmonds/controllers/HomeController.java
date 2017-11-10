package com.tandtseafoodedmonds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController extends AbstractController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "T and T Seafood Restaurant");
        return "index";
    }

}
