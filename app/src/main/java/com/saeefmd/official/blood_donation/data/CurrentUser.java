package com.saeefmd.official.blood_donation.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CurrentUser {

    private Context context;

    private String userEmail;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userMobile;
    private String userAge;
    private String userGender;
    private String lastDonateDate;

    public CurrentUser(Context context) {
        this.context = context;
    }

    public void getCurrentUser() {

        SharedPreferences mSharedPref = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        userName = mSharedPref.getString(Variables.CURRENT_USER_NAME, "Empty");
        userLocation = mSharedPref.getString(Variables.CURRENT_USER_LOCATION, "Empty");
        userBloodGroup = mSharedPref.getString(Variables.CURRENT_USER_BLOOD_GROUP, "Empty");
        userMobile = mSharedPref.getString(Variables.CURRENT_USER_MOBILE, "Empty");
        userAge = mSharedPref.getString(Variables.CURRENT_USER_AGE, "Empty");
        userGender = mSharedPref.getString(Variables.CURRENT_USER_GENDER, "Empty");
        userEmail = mSharedPref.getString(Variables.CURRENT_USER_EMAIL, "Empty");
        lastDonateDate = mSharedPref.getString(Variables.CURRENT_USER_LAST_DONATE_DATE, "Empty");
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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLastDonateDate() {
        return lastDonateDate;
    }

    public void setLastDonateDate(String lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
    }
}
