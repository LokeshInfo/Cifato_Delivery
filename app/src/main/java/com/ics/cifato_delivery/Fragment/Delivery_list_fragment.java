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
import com.ics.cifato_delivery.Adapter.Delivery_List_Adapter;
import com.ics.cifato_delivery.AppUtils.Api_Parameter;
import com.ics.cifato_delivery.AppUtils.AppPrefrences;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.AppUtils.Internet_Connectivity;
import com.ics.cifato_delivery.Model.Delivery_Responce;
import com.ics.cifato_delivery.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivery_list_fragment extends Fragment
{
    RecyclerView recyclerView;
    Api_Parameter ApiService;
    Delivery_List_Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    ImageView imgnot;
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery_details,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.delivery_recycler);
        imgnot = (ImageView) view.findViewById(R.id.img_nothing1);

        ApiService = BaseUrl.getAPIService();

        if (Internet_Connectivity.isConnected(getActivity())){
            call_Delivery_List();
        }
        else{
            SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("No Internet Connection !");
            pDialog.show();
        }



        return view;
    }

    private void call_Delivery_List(){

        final ProgressDialog dialog;
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Processing");
        dialog.setCancelable(true);
        dialog.show();
        String user_id = AppPrefrences.getUserid(getActivity());

        ApiService.DELIVERY_LIST_CALL(user_id).enqueue(new Callback<Delivery_Responce>() {
            @Override
            public void onResponse(Call<Delivery_Responce> call, Response<Delivery_Responce> response) {
                dialog.dismiss();
                Log.e("DELIVERY_LIST_CALL .", "" + new Gson().toJson(response.body()));
                Log.e("DELIVERY_LIST_CALL .", "-------------------------------------------------");
                if (response.body().getResponce()){
                    imgnot.setVisibility(View.GONE);
                    adapter = new Delivery_List_Adapter(getActivity(),response.body().getData());
                    linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
                else if (!response.body().getResponce()){
                    imgnot.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "No Data False", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "No Data (False)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Delivery_Responce> call, Throwable t) {
                dialog.dismiss();
                Log.e("DELIVERY_LIST_ Error" ,""+t.getStackTrace().toString());
                Log.e("DELIVERY_LIST_ Error" ,""+t.getMessage());
                Log.e("DELIVERY_LIST_ Error" ,""+t.getCause());
                Log.e("DELIVERY_LIST_ Error" ,""+t.getLocalizedMessage());
            }
        });

    }

}
