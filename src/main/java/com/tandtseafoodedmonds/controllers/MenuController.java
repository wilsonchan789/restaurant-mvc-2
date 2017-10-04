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
public class MenuController {

    private MenuItemData menuItemData = MenuItemData.getInstance();

    // The detail display for a given Menu at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        //get the Menu with the given ID and pass it into the view
        model.addAttribute("menu", menuItemData.findById(id));
        return "menu-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new MenuForm());
        return "new-menu";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid MenuForm menuForm, Errors errors) {

        // Validate the MenuForm model, and if valid, create a
        // new Menu and add it to the menuItemData data store. Then
        // redirect to the menu detail view for the new Menu.

        if (errors.hasErrors()) {
            model.addAttribute(menuForm);
            return "new-menu";
        }

        Menu newMenu = new Menu(menuForm.getName(),
                menuItemData.getCategories().findById(menuForm.getCategoryId()),
                menuItemData.getPrices().findById(menuForm.getPriceId()),
                menuItemData.getSpicys().findById(menuForm.getSpicyId()),
                menuItemData.getPounds().findById(menuForm.getPoundId()));
        menuItemData.add(newMenu);
        return "redirect:/menu/?id=" + Integer.toString(newMenu.getId());

    }
}
