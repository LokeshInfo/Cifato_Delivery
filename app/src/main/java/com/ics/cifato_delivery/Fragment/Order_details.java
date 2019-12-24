package com.ics.cifato_delivery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ics.cifato_delivery.Adapter.Delivery_List_Adapter;
import com.ics.cifato_delivery.Adapter.Order_Details_Adapter;
import com.ics.cifato_delivery.AppUtils.Api_Parameter;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.Model.Order_Details_Responce;
import com.ics.cifato_delivery.R;
import com.ics.cifato_delivery.View.Main_Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_details extends AppCompatActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ImageView imgnot, imgback;
    Api_Parameter ApiService;
    String order_id;
    Order_Details_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_details);
        recyclerView = (RecyclerView) findViewById(R.id.order_recycler);
        imgback = findViewById(R.id.imgToolbar);

        ApiService = BaseUrl.getAPIService();

        order_id = getIntent().getStringExtra("o_id");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Call_Data();

    }

    private void Call_Data(){

        ApiService.CALL_DETAILS(order_id).enqueue(new Callback<Order_Details_Responce>() {
            @Override
            public void onResponse(Call<Order_Details_Responce> call, Response<Order_Details_Responce> response) {
                Log.e("CALL_DETAILS RESPONSE.", "" + new Gson().toJson(response.body()));
                Log.e("CALL_DETAILS RESPONSE.", "-------------------------------------------------");
                adapter = new Order_Details_Adapter(Order_details.this,response.body().getData());
                linearLayoutManager = new LinearLayoutManager(Order_details.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Order_Details_Responce> call, Throwable t) {
                Log.e("CALL_DETAILS Error ..." ,""+t.getStackTrace().toString());
                Log.e("CALL_DETAILS Error ..." ,""+t.getMessage());
                Log.e("CALL_DETAILS Error ..." ,""+t.getCause());
                Log.e("CALL_DETAILS Error ..." ,""+t.getLocalizedMessage());
            }
        });

    }

}
