package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.utilities.ConfirmUserInfoDialog;
import com.saeefmd.official.blood_donation.utilities.WaitAlertDialog;

public class UserInfoActivity extends AppCompatActivity {

    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

    private String firstName;
    private String lastName;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userMobile;
    private String userAge;
    private String userGender;

    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText mobileEt;
    private EditText genderEt;
    private EditText ageEt;

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

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstNameEt.getText().toString().trim();
                lastName = lastNameEt.getText().toString().trim();

                userName = firstName + " " + lastName;
                userMobile = mobileEt.getText().toString();

                userBloodGroup = bloodGroupsSpinner.getSelectedItem().toString();
                userLocation = locationsSpinner.getSelectedItem().toString();

                if (!userBloodGroup.equals("Select") && !userLocation.equals("Select")) {

                    String bloodGroupText = bloodGroupInText(userBloodGroup);

                    Log.i("Check", userName + ","  +  userBloodGroup + "," +
                            userLocation + "," + userMobile);

                    if (checkTextFields()) {

                        //saveCurrentUser();

                        ConfirmUserInfoDialog confirmUserInfoDialog = new ConfirmUserInfoDialog(UserInfoActivity.this, userName,
                                userLocation, userBloodGroup, userMobile, userGender, userAge);
                        confirmUserInfoDialog.show();

                        //mWaitAlertDialog.show();

                        /*assert bloodGroupText != null;
                        firebaseReference = firebaseDatabase.getReference(bloodGroupText);*/

                        //inputUser(userName, userDepartment, userStudentId, userBatch, userLocation,
                                //userMobile, userBloodGroup);
                    } else {

                        Toast.makeText(UserInfoActivity.this, "Please provide required information", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(UserInfoActivity.this, "Please select valid values as blood group, location" +
                            " & department", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initViews() {
        bloodGroupsSpinner = findViewById(R.id.spinner_blood_groups);
        locationsSpinner = findViewById(R.id.spinner_locations);

        firstNameEt = findViewById(R.id.first_name_et);
        lastNameEt = findViewById(R.id.last_name_et);
        mobileEt = findViewById(R.id.mobile_et);
        genderEt = findViewById(R.id.gender_et);
        ageEt = findViewById(R.id.age_et);

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
    }

    private boolean checkTextFields() {

        if (firstName.isEmpty()) {
            firstNameEt.setError("Empty");
        }

        if (lastName.isEmpty()) {
            lastNameEt.setError("Empty");
        }

        if (userMobile.isEmpty()) {
            mobileEt.setError("Empty");
        }

        if (!firstName.isEmpty() && !lastName.isEmpty() && !userMobile.isEmpty() &&
                !userBloodGroup.isEmpty() && !userLocation.isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
}