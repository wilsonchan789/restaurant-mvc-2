package com.tandtseafoodedmonds.models;

public class Cart {

    private int id;
    private static int nextId = 1;

    private String name;
    private Price price;
    private Pound pound;

    public Cart() {
        id = nextId;
        nextId++;
    }

    public Cart(String aName, Price aPrice, Pound aPound) {

        this();

        name = aName;
        price = aPrice;
        pound = aPound;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Pound getPound() {
        return pound;
    }

    public void setPound(Pound pound) {
        this.pound = pound;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
