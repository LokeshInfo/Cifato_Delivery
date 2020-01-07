package com.ics.cifato_delivery.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ics.cifato_delivery.Adapter.Delivered_List_Adapter;
import com.ics.cifato_delivery.Adapter.Delivery_List_Adapter;
import com.ics.cifato_delivery.AppUtils.Api_Parameter;
import com.ics.cifato_delivery.AppUtils.AppPrefrences;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.Model.Delivery_Responce;
import com.ics.cifato_delivery.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivered_fragment extends Fragment
{
    Delivered_List_Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Api_Parameter ApiService;
    ImageView imgNothing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivered_details,container,false);

        recyclerView = view.findViewById(R.id.recyclervu);
        imgNothing = view.findViewById(R.id.img_nothing);

        ApiService = BaseUrl.getAPIService();
        call_List();

        return view;
    }

    private void call_List(){

        final ProgressDialog dialog;
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Processing");
        dialog.setCancelable(true);
        dialog.show();

        ApiService.DELIVERED_CALL(AppPrefrences.getUserid(getActivity())).enqueue(new Callback<Delivery_Responce>() {
            @Override
            public void onResponse(Call<Delivery_Responce> call, Response<Delivery_Responce> response) {
                dialog.dismiss();
                Log.e("DELIVERED_CALL .", "" + new Gson().toJson(response.body()));
                Log.e("DELIVERED_CALL .", "-------------------------------------------------");
                if (response.body().getResponce()){
                    imgNothing.setVisibility(View.GONE);
                    adapter = new Delivered_List_Adapter(getActivity(),response.body().getData());
                    linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
                else if (!response.body().getResponce()){
                    //Toast.makeText(getActivity(), "No Data False", Toast.LENGTH_SHORT).show();
                    imgNothing.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getActivity(), "No Data (False)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Delivery_Responce> call, Throwable t) {
                dialog.dismiss();
                Log.e("DELIVERED_CALL Error" ,""+t.getStackTrace().toString());
                Log.e("DELIVERED_CALL Error" ,""+t.getMessage());
                Log.e("DELIVERED_CALL Error" ,""+t.getCause());
                Log.e("DELIVERED_CALL Error" ,""+t.getLocalizedMessage());
            }
        });
    }

}
