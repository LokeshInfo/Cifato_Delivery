package com.ics.cifato_delivery.View;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.ics.cifato_delivery.AppUtils.Api_Parameter;
import com.ics.cifato_delivery.AppUtils.AppPrefrences;
import com.ics.cifato_delivery.AppUtils.BaseUrl;
import com.ics.cifato_delivery.AppUtils.Internet_Connectivity;
import com.ics.cifato_delivery.AppUtils.Session_Manage;
import com.ics.cifato_delivery.Model.Login_Response;
import com.ics.cifato_delivery.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login_activity extends AppCompatActivity
{
    private Button login;
    private EditText edusernm, edpassoword;
    private String user_id, password;
    private ImageView deli_img,deli_img2;
    private FrameLayout parent;
    private int sh=0;
    private Api_Parameter ApiService;
    Session_Manage session_manage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.button_login);

        edusernm = (EditText) findViewById(R.id.ed_email);
        edpassoword = (EditText) findViewById(R.id.ed_password);
        deli_img = (ImageView) findViewById(R.id.img_delivery);
        deli_img2 = (ImageView) findViewById(R.id.img_delivery2);
        parent = (FrameLayout) findViewById(R.id.img_lyt);

        ApiService = BaseUrl.getAPIService();

        session_manage = new Session_Manage(Login_activity.this);

        Click_Listeners();

        if (Internet_Connectivity.isConnected(Login_activity.this))
        { }
        else
        {   SweetAlertDialog pDialog = new SweetAlertDialog(Login_activity.this, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("No Internet Connection !");
            pDialog.show();       }

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = deli_img.getWidth();
                final float translationX = width * progress;
                deli_img.setTranslationX(translationX);
                deli_img2.setTranslationX(translationX - width);
            }
        });
        animator.start();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


    private void Click_Listeners(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Call_LOGIN();
                }
                else{

                }
            }
        });

        edpassoword.setLongClickable(false);

        edpassoword.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edpassoword.getRight() - edpassoword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (sh==0)    {
                            edpassoword.setTransformationMethod(null);
                            edpassoword.setSelection(edpassoword.length());
                            Log.e("---","."+edpassoword.getText().toString());
                            sh=1;
                        }
                        else{
                            edpassoword.setTransformationMethod(new PasswordTransformationMethod());
                            edpassoword.setSelection(edpassoword.length());
                            sh=0;
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private boolean validate()
    {
        user_id = edusernm.getText().toString().trim();
        password = edpassoword.getText().toString().trim();

        if (user_id.isEmpty() && password.isEmpty()){
            Toast.makeText(Login_activity.this, "Please enter data correctly...", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (user_id.isEmpty()){
            edusernm.setError("Required Field");
            return false;
        }

        else if(password.isEmpty()){
            edpassoword.setError("Required Field");
            return false;
        }

        else{
            return true;
        }
    }


    private void Call_LOGIN(){

        final ProgressDialog dialog;
        dialog = new ProgressDialog(Login_activity.this);
        dialog.setMessage("Processing");
        dialog.setCancelable(true);
        dialog.show();

        ApiService.LOGIN_CALL(user_id, password).enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                dialog.dismiss();
                Log.e("LOGIN_CALL RESPONSE.", "" + new Gson().toJson(response.body()));
                Log.e("LOGIN_CALL RESPONSE.", "-------------------------------------------------");

                if (response.body().getResponce())
                {
                    session_manage.create_Login_Session(response.body().getData().getUser_id() ,
                            response.body().getData().getUser_fullname(),
                            response.body().getData().getUser_email(),
                            response.body().getData().getUser_image(),
                            response.body().getData().getUser_phone()
                            );

                    AppPrefrences.setUserid(Login_activity.this, response.body().getData().getUser_id());
                    AppPrefrences.setName(Login_activity.this, response.body().getData().getUser_fullname());
                    AppPrefrences.setMobile(Login_activity.this, response.body().getData().getUser_phone());
                    AppPrefrences.setMail(Login_activity.this, response.body().getData().getUser_email());
                    AppPrefrences.setAddress(Login_activity.this, response.body().getData().getAddress());
                    AppPrefrences.setImage(Login_activity.this, response.body().getData().getUser_image());
                    AppPrefrences.setArea(Login_activity.this, response.body().getData().getArea());

                    Intent in = new Intent(Login_activity.this, Main_Activity.class);
                    startActivity(in);
                    finish();
                }
                else if (!response.body().getResponce()){
                    SweetAlertDialog pDialog = new SweetAlertDialog(Login_activity.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Oops...");
                    pDialog.setContentText("Invalid User or Password !");
                    pDialog.show();
                }

            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                dialog.dismiss();
                Log.e("LOGIN_CALL Error ..." ,""+t.getStackTrace().toString());
                Log.e("LOGIN_CALL Error ..." ,""+t.getMessage());
                Log.e("LOGIN_CALL Error ..." ,""+t.getCause());
                Log.e("LOGIN_CALL Error ..." ,""+t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));    }
}
