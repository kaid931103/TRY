package com.example.swipetodelete.model;

import java.util.ArrayList;

public class Drink {
    private String drinkName;
    private String midPrice;
    private String bigPrice;

    private ArrayList<Drink> drinks;

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(String midPrice) {
        this.midPrice = midPrice;
    }

    public String getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(String bigPrice) {
        this.bigPrice = bigPrice;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }
}
