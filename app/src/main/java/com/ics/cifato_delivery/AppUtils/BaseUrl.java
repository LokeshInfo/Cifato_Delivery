package com.ics.cifato_delivery.AppUtils;

public class BaseUrl
{
    //public static final String BASEURL = "https://ihisaab.in/MyTukari/index.php/Api/";
    //public static final String BASEURL = "http://jntrcpl.com/MyTukari/index.php/Api/";
    //public static final String IMG_BASEURL = "https://ihisaab.in/MyTukari/";

    public static final String BASEURL = "http://cifato.com/cifato_store/index.php/api/";

    public static final String IMG_BASEURL = "http://cifato.com/cifato_store/";

    //http://infocentroid.us/My_Tukuri_Live/

    public static Api_Parameter getAPIService(){
        return Retro_Client.getClient(BASEURL).create(Api_Parameter.class);
    }

    public static final String PREFS_NAME = "Delivery_Prefs";

    public static final String IS_LOGIN = "isLogin";

    public static final String KEY_NAME = "user_fullname";

    public static final String KEY_EMAIL = "user_email";

    public static final String KEY_ID = "user_id";

    public static final String KEY_MOBILE = "user_phone";

    public static final String KEY_IMAGE = "user_image";

    public static final String KEY_PINCODE = "pincode";

    public static final String KEY_SOCITY_ID = "Socity_id";

    public static final String KEY_SOCITY_NAME = "socity_name";

    public static final String KEY_HOUSE = "house_no";

    public static final String KEY_DATE = "date";

    public static final String KEY_TIME = "time";

    public static final String KEY_PASSWORD = "password";
}
