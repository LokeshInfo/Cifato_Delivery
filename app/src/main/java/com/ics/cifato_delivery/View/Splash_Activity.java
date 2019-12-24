package com.ics.cifato_delivery.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ics.cifato_delivery.AppUtils.Session_Manage;
import com.ics.cifato_delivery.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Splash_Activity extends AppCompatActivity
{

    Session_Manage session_management;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        session_management = new Session_Manage(Splash_Activity.this);

        Thread backgound = new Thread(){
            public void run()
            { try{
                sleep(2 * 1000);
                go_next();

            }      catch(Exception e)
            {      e.printStackTrace();            } }
        };

        backgound.start();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Kollektif-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));    }

    private void go_next()
    {
        if (session_management.isLoggedIn()){
            Intent in = new Intent(Splash_Activity.this, Main_Activity.class);
            startActivity(in);
            finish();
        }
        else
        {
            Intent in = new Intent(Splash_Activity.this, Login_activity.class);
            startActivity(in);
            finish();
        }
    }
}
