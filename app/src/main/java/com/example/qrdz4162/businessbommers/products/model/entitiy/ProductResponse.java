package com.example.qrdz4162.businessbommers.products.model.entitiy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductResponse {

    @SerializedName("page")
    int page;
    @SerializedName("limit")
    int limit;
    @SerializedName("pages")
    int pages;
    @SerializedName("total")
    int total;
    @SerializedName("_embedded")
    Embedded embedded;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }
}
