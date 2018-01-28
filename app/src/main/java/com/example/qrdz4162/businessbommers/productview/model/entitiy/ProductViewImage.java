package com.example.qrdz4162.businessbommers.productview.model.entitiy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductViewImage {


    @SerializedName("id")
    private int id;

    @SerializedName("path")
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
