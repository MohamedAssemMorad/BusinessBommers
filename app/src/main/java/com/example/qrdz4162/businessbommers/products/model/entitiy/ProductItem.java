package com.example.qrdz4162.businessbommers.products.model.entitiy;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductItem {

    @SerializedName("name")
    String name;
    @SerializedName("id")
    int id;
    @SerializedName("code")
    String code;
    @SerializedName("averageRating")
    int averageRating;

    @SerializedName("images")
    ArrayList<ProductImage> images;

    @SerializedName("barcode")
    String barcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public ArrayList<ProductImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ProductImage> images) {
        this.images = images;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
