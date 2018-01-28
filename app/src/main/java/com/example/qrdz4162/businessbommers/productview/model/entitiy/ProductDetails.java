package com.example.qrdz4162.businessbommers.productview.model.entitiy;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductDetails {

    @SerializedName("name")
    String name;
    @SerializedName("id")
    int id;
    @SerializedName("code")
    String code;
    @SerializedName("averageRating")
    int averageRating;
    @SerializedName("barcode")
    String barcode;
    @SerializedName("images")
    ArrayList<ProductViewImage> images;

    public ArrayList<ProductViewImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ProductViewImage> images) {
        this.images = images;
    }

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public ProductDetails(String name, int id, String code, int averageRating, String barcode){
        this.name = name;
        this.id = id;
        this.code =code;
        this.averageRating = averageRating;
        this.barcode = barcode;
    }
}
