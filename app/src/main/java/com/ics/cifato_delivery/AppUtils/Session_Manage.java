package com.ics.cifato_delivery.AppUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ics.cifato_delivery.View.Main_Activity;

public class Session_Manage
{
    SharedPreferences prefs;

    SharedPreferences.Editor editor;

    public static final String MyPREFERENCES  = "RIGHT_CHOICE";
    public static final String USERNAME = "username";
    public static final String MOBILE_NO = "mobileNo";


    Context context;

    int PRIVATE_MODE = 0;

    public Session_Manage(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(BaseUrl.PREFS_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }


    public void create_Login_Session(String user_id, String name, String email, String image, String phone){

        editor.putBoolean(BaseUrl.IS_LOGIN, true);
        editor.putString(BaseUrl.KEY_ID, user_id);
        editor.putString(BaseUrl.KEY_EMAIL, email);
        editor.putString(BaseUrl.KEY_NAME, name);
        editor.putString(BaseUrl.KEY_MOBILE,phone);
        editor.putString(BaseUrl.KEY_IMAGE, image);
        editor.commit();

    }


    public void logoutSession() {
        editor.clear();
        editor.commit();
        editor.putBoolean(BaseUrl.IS_LOGIN ,false);

        Intent logout = new Intent(context, Main_Activity.class);
        // Closing all the Activities
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(logout);
    }


    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, 0);
        return preferences.getString(USERNAME, "");
    }
    public static void setUsername(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, value);
        editor.commit();
    }


    public static String getMobileNo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, 0);
        return preferences.getString(MOBILE_NO, "");
    }
    public static void setMobileNo(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(MOBILE_NO, value);
        editor.commit();
    }


    // Get Login State
    public boolean isLoggedIn() {
        return prefs.getBoolean(BaseUrl.IS_LOGIN, false);
    }

}
