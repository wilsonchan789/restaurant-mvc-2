package com.tandtseafoodedmonds.models.forms;

import com.tandtseafoodedmonds.models.Pound;
import com.tandtseafoodedmonds.models.data.MenuItemData;
import com.tandtseafoodedmonds.models.Spicy;
import com.tandtseafoodedmonds.models.Category;
import com.tandtseafoodedmonds.models.Price;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class MenuForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int categoryId;

    @NotNull
    private int priceId;

    @NotNull
    private int spicyId;

    @NotNull
    private int poundId;

    private ArrayList<Category> categories;
    private ArrayList<Price> prices;
    private ArrayList<Spicy> calories;
    private ArrayList<Pound> pounds;

    public MenuForm() {

        MenuItemData menuItemData = MenuItemData.getInstance();

        /*
            populate the other ArrayList collections needed in the view
        */

        categories = menuItemData.getCategories().findAll();
        prices = menuItemData.getPrices().findAll();
        calories = menuItemData.getSpicys().findAll();
        pounds = menuItemData.getPounds().findAll();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
    }

    public ArrayList<Spicy> getCalories() {
        return calories;
    }

    public void setCalories(ArrayList<Spicy> calories) {
        this.calories = calories;
    }

    public ArrayList<Pound> getPounds() {
        return pounds;
    }

    public void setPounds(ArrayList<Pound> pounds) {
        this.pounds = pounds;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getSpicyId() {
        return spicyId;
    }

    public void setSpicyId(int spicyId) {
        this.spicyId = spicyId;
    }

    public int getPoundId() {
        return poundId;
    }

    public void setPoundId(int poundId) {
        this.poundId = poundId;
    }
}
