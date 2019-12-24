package com.ics.cifato_delivery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ics.cifato_delivery.AppUtils.AppPrefrences;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_fragment extends Fragment
{
    TextView name , mobile , mail, area;
    CircleImageView imgprofile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        name = view.findViewById(R.id.tx_pname);
        mobile = view.findViewById(R.id.tx_pmobile);
        mail = view.findViewById(R.id.tx_pmail);
        imgprofile = view.findViewById(R.id.img_profile);
        area = view.findViewById(R.id.tx_area);

        name.setText(""+ AppPrefrences.getName(getActivity()));
        mobile.setText(""+AppPrefrences.getMobile(getActivity()));
        mail.setText(""+AppPrefrences.getMail(getActivity()));
        area.setText(""+AppPrefrences.getArea(getActivity()));

        Glide.with(getActivity())
                .load(BaseUrl.IMG_BASEURL+"uploads/user_upload/"+AppPrefrences.getImage(getActivity()))
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(imgprofile);
        Log.e(" Image Name is","  "+AppPrefrences.getImage(getActivity()));



        return view;
    }
}
