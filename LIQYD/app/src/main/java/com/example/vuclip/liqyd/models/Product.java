package com.example.vuclip.liqyd.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Banty on 15/01/18.
 */

public class Product implements Parcelable {
    public int drawableImage;
    public String name;
    public String price;

    public Product() {

    }

    public int getDrawableImage() {
        return drawableImage;
    }

    public void setDrawableImage(int drawableImage) {
        this.drawableImage = drawableImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(Parcel in) {
        drawableImage = in.readInt();
        name = in.readString();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(drawableImage);
        dest.writeString(name);
        dest.writeString(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}