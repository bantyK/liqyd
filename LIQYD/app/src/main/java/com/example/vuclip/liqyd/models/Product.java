package com.example.vuclip.liqyd.models;

/**
 * Created by Banty on 15/01/18.
 */

public class Product {
    public int drawableImage;
    public String name;
    public String price;

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
}
