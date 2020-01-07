package com.ics.cifato_delivery.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ics.cifato_delivery.AppUtils.AppPrefrences;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.Fragment.Order_details;
import com.ics.cifato_delivery.Model.Confirm_Order_Responce;
import com.ics.cifato_delivery.Model.Delivery_data;
import com.ics.cifato_delivery.Model.Order_Finish;
import com.ics.cifato_delivery.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivery_List_Adapter  extends RecyclerView.Adapter<Delivery_List_Adapter.ViewHolder>
{
    List<Delivery_data> dataList;
    Activity mactivity;

    public Delivery_List_Adapter(Activity mactivity, List<Delivery_data> dataList)
    {
        this.mactivity=mactivity;
        this.dataList=dataList;
    }


    @Override
    public Delivery_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_delivery,viewGroup,false);
        return new Delivery_List_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Delivery_List_Adapter.ViewHolder viewHolder,final int i) {

        if (viewHolder != null) {

            final Delivery_data dob = dataList.get(i);

            //viewHolder.name.setText(dob.getUserFullname());
            viewHolder.oid.setText(""+dob.getOrderId());
            viewHolder.name.setText(""+dob.getUserFullname());
            viewHolder.mobile.setText(""+dob.getUserPhone());
            //viewHolder.paymode.setText(""+dob.getPaymentMode()+" -- Rs."+dob.getTotalAmount());
            viewHolder.paymode.setText("Rs."+dob.getTotalAmount());
            viewHolder.date.setText(""+dob.getOrderDate()+"\n"+dob.getDeliveryTimeFrom()+" -- "+dob.getDeliveryTimeTo());
            //viewHolder.amount.setText("Rs. "+dob.getTotalAmount());
            viewHolder.address.setText(""+dob.getDeliveryAddress());

            int status = Integer.parseInt(dob.getStatus());
            if (status==0){
                viewHolder.confirm_order.setVisibility(View.VISIBLE);
                viewHolder.finishb.setVisibility(View.GONE);
                viewHolder.cancelled_order.setVisibility(View.GONE);
            }
            else if (status == 3){
                viewHolder.cancelled_order.setVisibility(View.VISIBLE);
                viewHolder.confirm_order.setVisibility(View.GONE);
                viewHolder.finishb.setVisibility(View.GONE);
            }
            else{
                viewHolder.finishb.setVisibility(View.VISIBLE);
                viewHolder.confirm_order.setVisibility(View.GONE);
                viewHolder.cancelled_order.setVisibility(View.GONE);
            }

            viewHolder.call_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +dob.getUserPhone()));
                        mactivity.startActivity(intent);}
                    catch (Exception ex)
                    {   ex.printStackTrace();
                        Toast.makeText(mactivity, "Error No Sim Card Found......", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            viewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(mactivity, Order_details.class);
                    in.putExtra("o_id",dob.getOrderId());
                    mactivity.startActivity(in);

                }
            });

            viewHolder.finishb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        alert_box(i);
                }
            });

            viewHolder.confirm_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert_box_confirm(i);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView oid,name, mobile, address, paymode, amount, details, finishb, date, confirm_order, cancelled_order;
        ImageView icmap, call_user;

        public ViewHolder(View itemview) {
            super(itemview);

            oid = itemview.findViewById(R.id.p_oidd);
            name = itemview.findViewById(R.id.p_nmm);
            mobile = itemview.findViewById(R.id.p_phone);
            address = itemview.findViewById(R.id.p_addr);
            paymode = itemview.findViewById(R.id.p_paymnt);
            date = itemview.findViewById(R.id.p_dte);
            details = itemview.findViewById(R.id.details_buttn);
            finishb = itemview.findViewById(R.id.finish_buttn);
            icmap = itemview.findViewById(R.id.icon_map);
            call_user = itemview.findViewById(R.id.call_user);
            confirm_order = itemview.findViewById(R.id.confirm_order);
            cancelled_order = itemview.findViewById(R.id.order_cancel);
        }
    }

    private void alert_box(final int value)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    CALL_API(value);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dlg = builder.create();
        //dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
        dlg.show();
        dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setPadding(0,0,10,0);
        dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mactivity.getResources().getColor(R.color.grey));
        dlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mactivity.getResources().getColor(R.color.colorPrimary));
    }


    private void CALL_API(final int val){
        BaseUrl.getAPIService().ORDER_FINISH(dataList.get(val).getOrderId()).enqueue(new Callback<Order_Finish>() {
            @Override
            public void onResponse(Call<Order_Finish> call, Response<Order_Finish> response) {
                if (response.body().getResponce()) {
                    dataList.remove(dataList.get(val));
                    notifyDataSetChanged();
                    Toast.makeText(mactivity, "Order finished / Delivered...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mactivity, "Order not finished...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Order_Finish> call, Throwable t) {

            }
        });
    }

    private void alert_box_confirm(final int vallue)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mactivity);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CONFIRM_ORDER(vallue);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dlg = builder.create();
        //dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
        dlg.show();
        dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setPadding(0,0,10,0);
        dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mactivity.getResources().getColor(R.color.grey));
        dlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mactivity.getResources().getColor(R.color.colorPrimary));
    }

    private void CONFIRM_ORDER(final int vval){

        final ProgressDialog dialog;
        dialog = new ProgressDialog(mactivity);
        dialog.setCancelable(true);
        dialog.show();

        BaseUrl.getAPIService().CONFIRM_ORDER(dataList.get(vval).getOrderId()).enqueue(new Callback<Confirm_Order_Responce>() {
            @Override
            public void onResponse(Call<Confirm_Order_Responce> call, Response<Confirm_Order_Responce> response) {
                if (response.body().getResponse()) {
                    //dataList.remove(dataList.get(val));
                    dataList.get(vval).setStatus("1");
                    notifyDataSetChanged();
                    Toast.makeText(mactivity, " Order "+dataList.get(vval).getOrderId() +" Confirmed...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mactivity, "Order not finished...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Confirm_Order_Responce> call, Throwable t) {
                Log.e("Confirm Order Error "," "+t.getLocalizedMessage()+"\n"+t.getMessage()+"\n"+t.getCause());
            }
        });

    }


}
