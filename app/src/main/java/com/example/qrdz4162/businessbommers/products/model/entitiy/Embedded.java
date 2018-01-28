package com.example.qrdz4162.businessbommers.products.model.entitiy;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class Embedded {

    @SerializedName("items")
    private ArrayList<ProductItem> items;

    public ArrayList<ProductItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ProductItem> items) {
        this.items = items;
    }
}
