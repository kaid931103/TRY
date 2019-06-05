package com.example.swipetodelete.model;

import java.util.ArrayList;

public class Shop {
    private String Name;
    private ArrayList<Drink> menu;

    private ArrayList<Shop> shops;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Drink> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Drink> menu) {
        this.menu = menu;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }
}
