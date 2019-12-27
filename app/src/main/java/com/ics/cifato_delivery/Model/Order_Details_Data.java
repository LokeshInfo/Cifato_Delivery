package com.ics.cifato_delivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_Details_Data {

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("qty_in_kg")
    @Expose
    private String qty_in_kg;

    @SerializedName("unit_value")
    @Expose
    private String unit_value;

    @SerializedName("unit")
    @Expose
    private String unit;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("restaurant")
    @Expose
    private String restaurant;


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty_in_kg() {
        return qty_in_kg;
    }

    public void setQty_in_kg(String qty_in_kg) {
        this.qty_in_kg = qty_in_kg;
    }

    public String getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(String unit_value) {
        this.unit_value = unit_value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}



/*
@SerializedName("sale_item_id")
    @Expose
    private String sale_item_id;

    @SerializedName("sale_id")
    @Expose
    private String sale_id;

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("qty")
    @Expose
    private String quantity;

    @SerializedName("unit")
    @Expose
    private String unit;

    @SerializedName("unit_value")
    @Expose
    private String unit_value;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("qty_in_kg")
    @Expose
    private String qty_in_kg;

    public String getSale_item_id() {
        return sale_item_id;
    }

    public void setSale_item_id(String sale_item_id) {
        this.sale_item_id = sale_item_id;
    }

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_value() {
        return unit_value;
    }

    public void setUnit_value(String unit_value) {
        this.unit_value = unit_value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty_in_kg() {
        return qty_in_kg;
    }

    public void setQty_in_kg(String qty_in_kg) {
        this.qty_in_kg = qty_in_kg;
    }


 */