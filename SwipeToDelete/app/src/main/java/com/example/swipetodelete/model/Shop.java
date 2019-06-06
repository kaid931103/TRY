package com.example.swipetodelete.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Shop implements Parcelable {
    private int picture;
    public String name;
    private ArrayList<Drink> menu;

    private ArrayList<Shop> shops;

    public Shop(String name, int picture, ArrayList<Drink> menu) {
        this.name = name;
        this.picture = picture;
        this.menu = menu;
    }

    public Shop() {
        menu = new ArrayList<>();
    }

    private Shop(Parcel in) {
        picture = in.readInt();
        name = in.readString();
        menu = in.createTypedArrayList(Drink.CREATOR);
        shops = in.createTypedArrayList(Shop.CREATOR);
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

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
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(picture);
        dest.writeString(name);
        dest.writeTypedList(menu);
    }
}
