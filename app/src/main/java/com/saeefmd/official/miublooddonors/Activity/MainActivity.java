package com.saeefmd.official.miublooddonors.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.saeefmd.official.miublooddonors.Fragments.LogInFragment;
import com.saeefmd.official.miublooddonors.Fragments.UserInfoFragment;
import com.saeefmd.official.miublooddonors.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToLogInFragment();
    }

    private void switchToLogInFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new UserInfoFragment()).commit();
    }
}
