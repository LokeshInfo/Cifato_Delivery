package com.ics.cifato_delivery.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ics.cifato_delivery.Model.Delivery_data;
import com.ics.cifato_delivery.Model.Order_Details_Data;
import com.ics.cifato_delivery.R;

import java.util.List;

public class Order_Details_Adapter extends RecyclerView.Adapter<Order_Details_Adapter.ViewHolder>
{
    List<Order_Details_Data> dataList;
    Activity mactivity;


    public Order_Details_Adapter(Activity mactivity, List<Order_Details_Data> dataList)
    {
        this.mactivity=mactivity;
        this.dataList=dataList;
    }


    @Override
    public Order_Details_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_details,viewGroup,false);
        return new Order_Details_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Order_Details_Adapter.ViewHolder viewHolder, int i) {

        if (viewHolder != null) {

            Order_Details_Data dob = dataList.get(i);

         /*   viewHolder.datax.setText(" "+dob.getProduct_name()+"\n   "+dob.getQty_in_kg()
                    +"\n   "+dob.getPrice()+"\n   "+dob.getUnit()+"\n   "+dob.getUnit_value()
                    +"\n   "+dob.getSale_id()+"\n   "+dob.getSale_item_id());*/
         viewHolder.name.setText(""+dob.getProduct_name());
         viewHolder.qty.setText(""+dob.getQty_in_kg()+" Items"+"  Rs. "+dob.getPrice());
         viewHolder.area.setText(""+dob.getArea());
         viewHolder.shopname.setText(""+dob.getRestaurant());

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, qty, area, shopname;

        public ViewHolder(View itemview) {
            super(itemview);

            shopname = itemview.findViewById(R.id.shopname);
            name = itemview.findViewById(R.id.p_name);
            qty = itemview.findViewById(R.id.p_qty);
            area = itemview.findViewById(R.id.p_area);

        }
    }


}
