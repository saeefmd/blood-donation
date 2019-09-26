package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(LauncherActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }
}
