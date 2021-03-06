package com.saeefmd.official.blood_donation.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.saeefmd.official.blood_donation.data.Variables;
import com.saeefmd.official.blood_donation.R;

public class LauncherActivity extends Activity {

    private boolean firstTimeFlag;
    private boolean userSignedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        SharedPreferences mSharedPref = getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE);
        firstTimeFlag = mSharedPref.getBoolean(Variables.FIRST_TIME_FLAG, true);
        userSignedIn = mSharedPref.getBoolean(Variables.USER_SIGNED_IN, false);

        Log.i("Flag Value: ", firstTimeFlag + " , " + userSignedIn);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent;
                if (firstTimeFlag) {
                    intent = new Intent(LauncherActivity.this, LogInActivity.class);
                } else {
                    intent = new Intent(LauncherActivity.this, ProfileActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}
