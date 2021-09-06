package com.saeefmd.official.blood_donation.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CurrentUser {

    private Context context;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userDepartment;
    private String userMobile;

    public CurrentUser(Context context) {
        this.context = context;
    }

    public void getCurrentUser() {

        SharedPreferences mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        userName = mSharedPref.getString(Variables.CURRENT_USER_NAME, "Empty");
        userLocation = mSharedPref.getString(Variables.CURRENT_USER_LOCATION, "Empty");
        userBloodGroup = mSharedPref.getString(Variables.CURRENT_USER_BLOOD_GROUP, "Empty");
        userDepartment = mSharedPref.getString(Variables.CURRENT_USER_DEPARTMENT, "Empty");
        userMobile = mSharedPref.getString(Variables.CURRENT_USER_MOBILE, "Empty");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
