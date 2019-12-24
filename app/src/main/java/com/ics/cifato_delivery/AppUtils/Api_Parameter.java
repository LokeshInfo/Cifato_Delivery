package com.ics.cifato_delivery.AppUtils;

import com.ics.cifato_delivery.Model.Delivery_Responce;
import com.ics.cifato_delivery.Model.Login_Response;
import com.ics.cifato_delivery.Model.Order_Details_Responce;
import com.ics.cifato_delivery.Model.Order_Finish;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Parameter
{
    @FormUrlEncoded
    @POST("emp_login")
    Call<Login_Response>LOGIN_CALL(
    @Field("username")String usernm,
    @Field("password")String pass
    );


    @FormUrlEncoded
    @POST("emp_delivery_product")
    Call<Delivery_Responce>DELIVERY_LIST_CALL(
            @Field("user_id")String user_id
    );

    @FormUrlEncoded
    @POST("emp_deliverd_pro")
    Call<Delivery_Responce>DELIVERED_CALL(
            @Field("user_id")String user_id
    );

    @FormUrlEncoded
    @POST("order_id_detail")
    Call<Order_Details_Responce>CALL_DETAILS(
            @Field("order_id")String order_id
    );

    @FormUrlEncoded
    @POST("delivered_status")
    Call<Order_Finish>ORDER_FINISH(
            @Field("order_id")String order_id
    );

}
