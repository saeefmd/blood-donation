package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.data.CurrentUser;
import com.saeefmd.official.blood_donation.utilities.ConfirmUserInfoDialog;
import com.saeefmd.official.blood_donation.utilities.DatePickerDialog;
import com.saeefmd.official.blood_donation.utilities.WaitAlertDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class UserInfoActivity extends AppCompatActivity {

    private SearchableSpinner bloodGroupsSpinner;
    private SearchableSpinner locationsSpinner;
    private SearchableSpinner genderSpinner;

    private String firstName;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userMobile;
    private String userAge;
    private String userGender;
    private String lastDonateDate;

    private EditText firstNameEt;
    private EditText mobileEt;
    private EditText ageEt;

    private TextView lastDonateDateTv;

    private Button continueBt;

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDatabase;

    private WaitAlertDialog mWaitAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initViews();

        firebaseDatabase = FirebaseDatabase.getInstance();

        mWaitAlertDialog = new WaitAlertDialog(UserInfoActivity.this);

        setSpinners();

        setViews();

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstNameEt.getText().toString().trim();

                userName = firstName;
                userMobile = mobileEt.getText().toString();

                userBloodGroup = bloodGroupsSpinner.getSelectedItem().toString();
                userLocation = locationsSpinner.getSelectedItem().toString();
                userGender = genderSpinner.getSelectedItem().toString();

                userAge = ageEt.getText().toString().trim();

                if (!userBloodGroup.equals("Select") && !userLocation.equals("Select") && !userGender.equals("Select")) {

                    String bloodGroupText = bloodGroupInText(userBloodGroup);

                    Log.i("Check", userName + ","  +  userBloodGroup + "," +
                            userLocation + "," + userMobile);

                    if (checkTextFields()) {

                        ConfirmUserInfoDialog confirmUserInfoDialog = new ConfirmUserInfoDialog(UserInfoActivity.this, userName,
                                userLocation, userBloodGroup, userMobile, userGender, userAge, lastDonateDate);
                        confirmUserInfoDialog.show();

                    } else {

                        Toast.makeText(UserInfoActivity.this, "Please provide required information", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(UserInfoActivity.this, "Please select valid values as blood group, location" +
                            " & department", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lastDonateDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserInfoActivity.this, new DatePickerDialog.OnDateSelected() {
                    @Override
                    public void onSaveClicked(String date) {
                        lastDonateDate = date;
                        lastDonateDateTv.setText(date);
                    }
                });
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(UserInfoActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void setViews() {
        if (!CurrentUser.getUserName(this).equals("Empty")) {
            firstNameEt.setText(CurrentUser.getUserName(this));
            mobileEt.setText(CurrentUser.getUserMobile(this));
            ageEt.setText(CurrentUser.getUserAge(this));
            lastDonateDateTv.setText(CurrentUser.getLastDonateDate(this));

            bloodGroupsSpinner.setTitle(CurrentUser.getUserBloodGroup(this));
            bloodGroupsSpinner.setEnabled(false);
        }
    }

    private void initViews() {
        bloodGroupsSpinner = findViewById(R.id.spinner_blood_groups);
        locationsSpinner = findViewById(R.id.spinner_locations);
        genderSpinner = findViewById(R.id.spinner_gender);

        firstNameEt = findViewById(R.id.first_name_et);
        mobileEt = findViewById(R.id.mobile_et);
        ageEt = findViewById(R.id.age_et);
        lastDonateDateTv = findViewById(R.id.last_donate_date_tv);

        continueBt = findViewById(R.id.user_info_continue_bt);
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

        ArrayAdapter<CharSequence> bloodGroupsAdapter = ArrayAdapter.createFromResource(UserInfoActivity.this,
                R.array.array_blood_groups, android.R.layout.simple_spinner_item);
        bloodGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupsSpinner.setAdapter(bloodGroupsAdapter);

        ArrayAdapter<CharSequence> locationsAdapter = ArrayAdapter.createFromResource(UserInfoActivity.this,
                R.array.array_locations, android.R.layout.simple_spinner_item);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationsSpinner.setAdapter(locationsAdapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(UserInfoActivity.this,
                R.array.array_gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
    }

    private boolean checkTextFields() {

        if (firstName.isEmpty()) {
            firstNameEt.setError("Empty");
        }

        if (userMobile.isEmpty()) {
            mobileEt.setError("Empty");
        }

        if (userAge.isEmpty()) {
            ageEt.setError("Empty");
        }

        if (!firstName.isEmpty() && !userMobile.isEmpty() && !userGender.isEmpty() && !userAge.isEmpty() &&
                !userBloodGroup.isEmpty() && !userLocation.isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
}
