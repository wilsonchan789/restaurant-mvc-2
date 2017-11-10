package com.tandtseafoodedmonds.controllers;

import com.tandtseafoodedmonds.models.Cart;
import com.tandtseafoodedmonds.models.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "cart")
public class CartController extends AbstractController {

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, Menu menu) {
        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("menu", menu);
        return "cart/index";
    }


    @RequestMapping(value="total", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request, Cart cart) {
        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("cart", cart);
        return "cart/index";
    }


}
