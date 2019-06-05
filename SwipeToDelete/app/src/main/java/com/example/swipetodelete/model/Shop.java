package com.example.swipetodelete.model;

import java.util.ArrayList;

public class Shop {
    public String name;
    private ArrayList<Drink> menu;

    private ArrayList<Shop> shops;

    public Shop(String name, ArrayList<Drink> menu){
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
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
