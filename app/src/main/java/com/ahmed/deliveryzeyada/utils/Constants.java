package com.ahmed.deliveryzeyada.utils;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class Constants
{
    public static final String BASE_URL = "http://deliveryzyada.com/";
    public static final String LOGIN_API = "api/auth/login/getToken";
    public static final String RESTURANTS_MAP_API = "/api/Order/GetResturants";

    public static final String CHECK_USER = "/api/User/CheckUserStatus";
    public static final String LOGIN_GRANT_TYPE = "grant_type";
    public static final String LOGIN_EMAIL = "email";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_DEVICE_ID = "device_id";

    public static final String USER_TYPE_PILOT = "Pilot";
    public static final String USER_TYPE_Resturant = "Restaurant";
}
