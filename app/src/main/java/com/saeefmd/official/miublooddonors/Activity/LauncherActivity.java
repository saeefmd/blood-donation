package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.saeefmd.official.miublooddonors.Data.Variables;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mSharedPref = getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        boolean firstTimeFlag = mSharedPref.getBoolean(Variables.FIRST_TIME_FLAG, true);
        boolean userSignedIn = mSharedPref.getBoolean(Variables.USER_SIGNED_IN, false);

        Log.i("Flag Value: ", firstTimeFlag + " , " + userSignedIn);

        if (firstTimeFlag && !userSignedIn) {

            Intent intent = new Intent(LauncherActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();

        } else if (firstTimeFlag && userSignedIn) {

            Intent intent = new Intent(LauncherActivity.this, UserInfoActivity.class);
            startActivity(intent);
            finish();

        } else {

            Intent intent = new Intent(LauncherActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
