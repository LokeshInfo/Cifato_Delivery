package com.ics.cifato_delivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Confirm_Order_Responce {

    @SerializedName("responce")
    @Expose
    private Boolean response;

    @SerializedName("data")
    @Expose
    private String message;

    public Confirm_Order_Responce(Boolean response, String message) {
        this.response = response;
        this.message = message;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
