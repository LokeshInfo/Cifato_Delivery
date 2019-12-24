package com.ics.cifato_delivery.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ics.cifato_delivery.Fragment.Order_details;
import com.ics.cifato_delivery.Model.Delivery_data;
import com.ics.cifato_delivery.R;

import java.util.List;

public class Delivered_List_Adapter  extends RecyclerView.Adapter<Delivered_List_Adapter.ViewHolder>
{
    List<Delivery_data> dataList;
    Activity mactivity;


    public Delivered_List_Adapter(Activity mactivity, List<Delivery_data> dataList)
    {
        this.mactivity=mactivity;
        this.dataList=dataList;
    }


    @Override
    public Delivered_List_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_delivered,viewGroup,false);
        return new Delivered_List_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Delivered_List_Adapter.ViewHolder viewHolder, int i) {

        if (viewHolder != null) {

            final Delivery_data dob = dataList.get(i);
            viewHolder.oid.setText(""+dob.getOrderId());
            viewHolder.name.setText(""+dob.getUserFullname());
            viewHolder.mobile.setText(""+dob.getUserPhone());
            viewHolder.paymode.setText("Rs."+dob.getTotalAmount());
            viewHolder.date.setText(""+dob.getOrderDate());
            //viewHolder.amount.setText("Rs. "+dob.getTotalAmount());
            viewHolder.address.setText(""+dob.getDeliveryAddress());
            viewHolder.details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(mactivity, Order_details.class);
                    in.putExtra("o_id",dob.getOrderId());
                    mactivity.startActivity(in);
                    //Toast.makeText(mactivity, "Clicknig,,,,,", Toast.LENGTH_SHORT).show();
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
        TextView oid,name, mobile, address, paymode, date;
        RelativeLayout details;

        public ViewHolder(View itemview) {
            super(itemview);

            oid = itemview.findViewById(R.id.p_oidd);
            name = itemview.findViewById(R.id.p_nmm);
            mobile = itemview.findViewById(R.id.p_phone);
            address = itemview.findViewById(R.id.p_addr);
            paymode = itemview.findViewById(R.id.p_paymnt);
            date = itemview.findViewById(R.id.p_dte);

            details = itemview.findViewById(R.id.details_buttn2);
        }
    }




}
