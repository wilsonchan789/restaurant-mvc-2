package com.tandtseafoodedmonds.controllers;

import com.tandtseafoodedmonds.models.Menu;
import com.tandtseafoodedmonds.models.data.MenuItemData;
import com.tandtseafoodedmonds.models.forms.MenuForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController extends AbstractController {

    private MenuItemData menuItemData = MenuItemData.getInstance();

    // The detail display for a given Menu at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        //get the Menu with the given ID and pass it into the view
        model.addAttribute("menu", menuItemData.findById(id));
        return "menu-detail";
    }
}
