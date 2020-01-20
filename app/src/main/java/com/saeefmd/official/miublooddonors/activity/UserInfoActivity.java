package com.saeefmd.official.miublooddonors.activity;

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
import com.saeefmd.official.miublooddonors.R;
import com.saeefmd.official.miublooddonors.utilities.ConfirmUserInfoDialog;
import com.saeefmd.official.miublooddonors.utilities.WaitAlertDialog;

public class UserInfoActivity extends AppCompatActivity {

    private Spinner departmentsSpinner;
    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

    private String firstName;
    private String lastName;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userDepartment;
    private String userBatch;
    private String userStudentId;
    private String userMobile;

    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText batchEt;
    private EditText studentIdEt;
    private EditText mobileEt;

    private Button continueBt;

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDatabase;

    private WaitAlertDialog mWaitAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        departmentsSpinner = findViewById(R.id.spinner_departments);
        bloodGroupsSpinner = findViewById(R.id.spinner_blood_groups);
        locationsSpinner = findViewById(R.id.spinner_locations);

        firstNameEt = findViewById(R.id.first_name_et);
        lastNameEt = findViewById(R.id.last_name_et);
        batchEt = findViewById(R.id.batch_no_et);
        studentIdEt = findViewById(R.id.student_id_et);
        mobileEt = findViewById(R.id.mobile_et);

        firebaseDatabase = FirebaseDatabase.getInstance();

        mWaitAlertDialog = new WaitAlertDialog(UserInfoActivity.this);

        continueBt = findViewById(R.id.user_info_continue_bt);

        setSpinners();

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = firstNameEt.getText().toString().trim();
                lastName = lastNameEt.getText().toString().trim();

                userName = firstName + " " + lastName;
                userBatch = batchEt.getText().toString();
                userMobile = mobileEt.getText().toString();
                userStudentId = studentIdEt.getText().toString();

                userBloodGroup = bloodGroupsSpinner.getSelectedItem().toString();
                userDepartment = departmentsSpinner.getSelectedItem().toString();
                userLocation = locationsSpinner.getSelectedItem().toString();

                if (!userBloodGroup.equals("Select") && !userDepartment.equals("Select") && !userLocation.equals("Select")) {

                    String bloodGroupText = bloodGroupInText(userBloodGroup);

                    Log.i("Check", userName + "," + userDepartment + "," +
                            userBatch + "," + userStudentId + "," + userBloodGroup + "," +
                            userLocation + "," + userMobile);

                    if (checkTextFields()) {

                        //saveCurrentUser();

                        ConfirmUserInfoDialog confirmUserInfoDialog = new ConfirmUserInfoDialog(UserInfoActivity.this, userName,
                                userLocation, userBloodGroup, userDepartment, userMobile, userBatch, userStudentId);
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

    /*private void inputUser(String name, String department, String studentId, String batch, String location, String mobile, String bloodGroup) {

        UserInfo userInfo = new UserInfo(name, department, studentId, batch, location, mobile, bloodGroup);

        firebaseReference.child(userMobile).setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    mWaitAlertDialog.dismiss();
                    Toast.makeText(UserInfoActivity.this, "Your Information Stored Successfully. Thank You", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
                    editor.putBoolean(Variables.FIRST_TIME_FLAG, false);
                    editor.apply();

                    Intent intent = new Intent(UserInfoActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    mWaitAlertDialog.dismiss();
                    Toast.makeText(UserInfoActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveCurrentUser() {

        SharedPreferences.Editor editor = getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_NAME, userName);
        editor.putString(Variables.CURRENT_USER_DEPARTMENT, userDepartment);
        editor.putString(Variables.CURRENT_USER_BATCH, userBatch);
        editor.putString(Variables.CURRENT_USER_STUDENT_ID, userStudentId);
        editor.putString(Variables.CURRENT_USER_BLOOD_GROUP, userBloodGroup);
        editor.putString(Variables.CURRENT_USER_MOBILE, userMobile);
        editor.putString(Variables.CURRENT_USER_LOCATION, userLocation);
        editor.apply();
    }*/

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

        ArrayAdapter<CharSequence> departmentsAdapter = ArrayAdapter.createFromResource(UserInfoActivity.this,
                R.array.array_departments, android.R.layout.simple_spinner_item);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(departmentsAdapter);


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
                !userDepartment.isEmpty() && !userBloodGroup.isEmpty() && !userLocation.isEmpty()) {

            return true;
        } else {

            return false;
        }
    }
}
