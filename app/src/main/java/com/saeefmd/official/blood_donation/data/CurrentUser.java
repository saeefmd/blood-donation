package com.saeefmd.official.blood_donation.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CurrentUser {

    private static SharedPreferences mSharedPref;

    public static void setCurrentUser(Context context, String userName, String userLocation, String userBloodGroup,
                                      String userMobile, String userAge, String userGender, String userEmail, String lastDonateDate) {

        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_NAME, userName);
        editor.putString(Variables.CURRENT_USER_LOCATION, userLocation);
        editor.putString(Variables.CURRENT_USER_BLOOD_GROUP, userBloodGroup);
        editor.putString(Variables.CURRENT_USER_MOBILE, userMobile);
        editor.putString(Variables.CURRENT_USER_AGE, userAge);
        editor.putString(Variables.CURRENT_USER_GENDER, userGender);
        editor.putString(Variables.CURRENT_USER_EMAIL, userEmail);
        editor.putString(Variables.CURRENT_USER_LAST_DONATE_DATE, lastDonateDate);
        editor.apply();
    }

    public static String getUserName(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_NAME, "Empty");
    }

    public static void setUserName(String userName, Context context) {

        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_NAME, userName);
        editor.apply();
    }

    public static String getUserLocation(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_LOCATION, "Empty");
    }

    public static void setUserLocation(Context context, String userLocation) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_LOCATION, userLocation);
        editor.apply();
    }

    public static String getUserBloodGroup(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
       return mSharedPref.getString(Variables.CURRENT_USER_BLOOD_GROUP, "Empty");
    }

    public static void setUserBloodGroup(Context context, String userBloodGroup) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_BLOOD_GROUP, userBloodGroup);
        editor.apply();
    }

    public static String getUserMobile(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_MOBILE, "Empty");
    }

    public static void setUserMobile(Context context ,String userMobile) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_MOBILE, userMobile);
        editor.apply();
    }

    public static String getUserAge(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_AGE, "Empty");
    }

    public static void setUserAge(Context context ,String userAge) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_AGE, userAge);
        editor.apply();
    }

    public static String getUserGender(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_GENDER, "Empty");
    }

    public static void setUserGender(Context context ,String userGender) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_GENDER, userGender);
        editor.apply();
    }

    public static String getUserEmail(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_EMAIL, "Empty");
    }

    public static void setUserEmail(Context context, String userEmail) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_EMAIL, userEmail);
        editor.apply();
    }

    public static String getLastDonateDate(Context context) {
        mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        return mSharedPref.getString(Variables.CURRENT_USER_LAST_DONATE_DATE, "Empty");
    }

    public void setLastDonateDate(Context context, String lastDonateDate) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_LAST_DONATE_DATE, lastDonateDate);
        editor.apply();
    }
}
