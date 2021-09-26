package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.utilities.AboutDialog;
import com.saeefmd.official.blood_donation.utilities.DeveloperInfoDialog;
import com.saeefmd.official.blood_donation.utilities.MyInfoAlertDialog;

import java.util.Random;

public class ProfileActivity extends AppCompatActivity {

    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

    private String requiredBlood;
    private String preferredLocation;

    private TextView quoteTv;
    private TextView authorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bloodGroupsSpinner = findViewById(R.id.profile_spinner_blood_groups);
        locationsSpinner = findViewById(R.id.profile_spinner_locations);

        setSpinners();

        FloatingActionButton searchFab = findViewById(R.id.profile_search_fab);

        quoteTv = findViewById(R.id.quote_tv);
        authorTv = findViewById(R.id.quote_author_tv);

        setQuote();

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requiredBlood = bloodGroupsSpinner.getSelectedItem().toString();
                preferredLocation = locationsSpinner.getSelectedItem().toString();

                if (!requiredBlood.equals("Select") && !preferredLocation.equals("Select")) {

                    if (isNetworkAvailable()) {

                        Intent intent = new Intent(ProfileActivity.this, BloodResultActivity.class);
                        intent.putExtra("bloodGroup", requiredBlood);
                        intent.putExtra("location", preferredLocation);
                        startActivity(intent);
                    } else {

                        alertDialogBuilder();
                    }
                } else {

                    Toast.makeText(ProfileActivity.this, "Please select valid blood group and location", Toast.LENGTH_SHORT).show();
                }


            }
        });
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

    private void setQuote() {

        String[] quotes = getResources().getStringArray(R.array.quotes_array);
        String[] authors = getResources().getStringArray(R.array.authors_array);

        int quotesCount = quotes.length;

        Random random = new Random();
        int randomValue = random.nextInt(quotesCount);

        quoteTv.setText(quotes[randomValue]);
        authorTv.setText(authors[randomValue]);
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
                AboutDialog aboutDialog = new AboutDialog(ProfileActivity.this);
                aboutDialog.show();
                break;

            case R.id.menu_contact_developer:
                DeveloperInfoDialog developerInfoDialog = new DeveloperInfoDialog(ProfileActivity.this);
                developerInfoDialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}