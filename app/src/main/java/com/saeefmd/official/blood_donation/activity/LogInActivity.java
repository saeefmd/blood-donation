package com.saeefmd.official.blood_donation.activity;

import android.os.Bundle;

import com.saeefmd.official.blood_donation.fragments.LogInFragment;
import com.saeefmd.official.blood_donation.R;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToLogInFragment();
    }

    private void switchToLogInFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new LogInFragment()).commit();
    }

}
