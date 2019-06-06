package com.example.swipetodelete.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Drink implements Parcelable {
    private String drinkName;
    private String midPrice;
    private String bigPrice;

    private ArrayList<Drink> drinks;

    public Drink(String drinkName, String midPrice, String bigPrice){
        this.drinkName = drinkName;
        this.midPrice = midPrice;
        this.bigPrice = bigPrice;
    }

    private Drink(Parcel in) {
        drinkName = in.readString();
        midPrice = in.readString();
        bigPrice = in.readString();
        drinks = in.createTypedArrayList(Drink.CREATOR);
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(drinkName);
        dest.writeString(midPrice);
        dest.writeString(bigPrice);
    }
}
