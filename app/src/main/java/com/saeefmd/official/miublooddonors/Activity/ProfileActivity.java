package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.saeefmd.official.miublooddonors.R;

public class ProfileActivity extends AppCompatActivity {

    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

    private String requiredBlood;
    private String preferredLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bloodGroupsSpinner = findViewById(R.id.profile_spinner_blood_groups);
        locationsSpinner = findViewById(R.id.profile_spinner_locations);

        setSpinners();

        Button searchBt = findViewById(R.id.profile_search_bt);

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requiredBlood = bloodGroupInText(bloodGroupsSpinner.getSelectedItem().toString());
                preferredLocation = locationsSpinner.getSelectedItem().toString();

                if (isNetworkAvailable()) {

                    Intent intent = new Intent(ProfileActivity.this, BloodResultActivity.class);
                    intent.putExtra("bloodGroup", requiredBlood);
                    intent.putExtra("location", preferredLocation);
                    startActivity(intent);
                } else {

                    alertDialogBuilder();
                }
            }
        });
    }

    private String bloodGroupInText(String group) {

        switch (group) {
            case "A+":
                return "A_Positive";
            case "A-":
                return "A_Negative";
            case "B+":
                return "B_Positive";
            case "B-":
                return "B_Negative";
            case "O+":
                return "O_Positive";
            case "O-":
                return "O_Negative";
            case "AB+":
                return "AB_Positive";
            case "AB-":
                return "AB_Negative";
            default:
                return null;
        }
    }

    private void setSpinners() {

        ArrayAdapter<CharSequence> bloodGroupsAdapter = ArrayAdapter.createFromResource(ProfileActivity.this,
                R.array.array_blood_groups, android.R.layout.simple_spinner_item);
        bloodGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupsSpinner.setAdapter(bloodGroupsAdapter);

        ArrayAdapter<CharSequence> locationsAdapter = ArrayAdapter.createFromResource(ProfileActivity.this,
                R.array.array_locations, android.R.layout.simple_spinner_item);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationsSpinner.setAdapter(locationsAdapter);
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void alertDialogBuilder() {

        new AlertDialog.Builder(ProfileActivity.this)
                .setTitle("No Internet")
                .setMessage("You need to turn on the Internet. You can turn wifi from here or manually")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Turn On Wifi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifi.setWifiEnabled(true);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
