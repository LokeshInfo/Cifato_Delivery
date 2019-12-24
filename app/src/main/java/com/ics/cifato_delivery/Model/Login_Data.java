package com.ics.cifato_delivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Data
{
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_fullname")
    @Expose
    private String user_fullname;

    @SerializedName("user_status")
    @Expose
    private String user_status;

    @SerializedName("user_image")
    @Expose
    private String user_image;

    @SerializedName("user_email")
    @Expose
    private String user_email;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    @SerializedName("area")
    @Expose
    private String area;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
