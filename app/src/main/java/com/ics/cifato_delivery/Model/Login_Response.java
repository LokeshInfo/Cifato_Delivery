package com.ics.cifato_delivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Response
{
    @SerializedName("responce")
    @Expose
    private boolean responce;

    @SerializedName("data")
    @Expose
    Login_Data Data;


    // Getter Methods

    public boolean getResponce() {
        return responce;
    }

    // Setter Methods

    public void setResponce( boolean responce ) {
        this.responce = responce;
    }

    public Login_Data getData() {
        return Data;
    }

    public void setData(Login_Data data) {
        Data = data;
    }
}
