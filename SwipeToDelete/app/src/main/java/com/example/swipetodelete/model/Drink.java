package com.example.swipetodelete.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Drink implements Parcelable {
    private int position;
    private String drinkName;
    private String midPrice;
    private String bigPrice;

    private ArrayList<Drink> drinks;

    public Drink(int position,String drinkName, String midPrice, String bigPrice){
        this.position = position;
        this.drinkName = drinkName;
        this.midPrice = midPrice;
        this.bigPrice = bigPrice;
    }

    public Drink(){
//        drinks = new ArrayList<>();
    }

    private Drink(Parcel in) {
        position = in.readInt();
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeString(drinkName);
        dest.writeString(midPrice);
        dest.writeString(bigPrice);
    }
}
