package com.saeefmd.official.blood_donation.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.data.CurrentUser;
import com.saeefmd.official.blood_donation.utilities.MyInfoAlertDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    private SearchableSpinner bloodGroupsSpinner;
    private SearchableSpinner locationsSpinner;

    private String requiredBlood;
    private String preferredLocation;

    private Button donationChartBt;
    private Button donationFactorsBt;

    private TextView profileNameTv;
    private TextView profileLocationTv;
    private TextView profileLastDonateDateTv;
    private TextView profileBloodGroupTv;
    private TextView profileMobileTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        setViews();

        donationChartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DonationChartActivity.class);
                startActivity(intent);
            }
        });

        donationFactorsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DonorRiskActivity.class);
                startActivity(intent);
            }
        });

        setSpinners();

        //getFcmToken();

        FloatingActionButton searchFab = findViewById(R.id.profile_search_fab);

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requiredBlood = bloodGroupsSpinner.getSelectedItem().toString();
                preferredLocation = locationsSpinner.getSelectedItem().toString();

                if (!requiredBlood.equals("Select")) {

                    if (isNetworkAvailable()) {

                        Intent intent = new Intent(ProfileActivity.this, BloodResultActivity.class);
                        intent.putExtra("bloodGroup", requiredBlood);
                        intent.putExtra("location", preferredLocation);
                        startActivity(intent);
                    } else {

                        alertDialogBuilder();
                    }
                } else {

                    Toast.makeText(ProfileActivity.this, "Please select valid blood group", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void setViews() {
        profileNameTv.setText(CurrentUser.getUserName(this));
        profileLocationTv.setText("Location: " + CurrentUser.getUserLocation(this));
        profileLastDonateDateTv.setText("Last Donate: " + CurrentUser.getLastDonateDate(this));
        profileBloodGroupTv.setText(CurrentUser.getUserBloodGroup(this));
        profileMobileTv.setText("Mobile: " + CurrentUser.getUserMobile(this));
    }

    private void initViews() {
        bloodGroupsSpinner = findViewById(R.id.profile_spinner_blood_groups);
        locationsSpinner = findViewById(R.id.profile_spinner_locations);

        donationChartBt = findViewById(R.id.donation_chart_bt);
        donationFactorsBt = findViewById(R.id.donor_factors_bt);

        profileBloodGroupTv = findViewById(R.id.profile_blood_group_text_view);
        profileNameTv = findViewById(R.id.profile_name_text_view);
        profileLastDonateDateTv = findViewById(R.id.profile_last_donate_date_text_view);
        profileLocationTv = findViewById(R.id.profile_location_text_view);
        profileMobileTv = findViewById(R.id.profile_mobile_text_view);
    }

    private void checkFirebaseAuth() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Log.d(TAG, user.getEmail());
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

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(ProfileActivity.this)
                .setTitle("Exit Application")
                .setMessage("Are you sure you want to exit?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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
                .setMessage("You need to turn on the Internet. You can turn wifi on from here or manually")

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_current_user_info:
                MyInfoAlertDialog myInfoAlertDialog = new MyInfoAlertDialog(ProfileActivity.this);
                myInfoAlertDialog.show();
                break;

            case R.id.menu_help_option:
                break;

            case R.id.menu_contact_developer:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getFcmToken(String bloodGroup) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        Log.d("FCM Token: ", token);

                        subscribeToFcm(bloodGroup);

                        // Log and toast
                        /*String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();*/
                    }
                });
    }

    private void subscribeToFcm(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Topic Subscription Failed");
                        }

                        if (task.isComplete()) {
                            Toast.makeText(ProfileActivity.this, "Subscription Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
