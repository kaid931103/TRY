package com.example.swipetodelete.model;

import java.util.ArrayList;

public class Shop {
    private int picture;
    public String name;
    private ArrayList<Drink> menu;

    private ArrayList<Shop> shops;

    public Shop(String name,int picture, ArrayList<Drink> menu){
        this.name = name;
        this.picture = picture;
        this.menu = menu;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
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
