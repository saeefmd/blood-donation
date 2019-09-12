package com.saeefmd.official.miublooddonors.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saeefmd.official.miublooddonors.Model.UserInfo;
import com.saeefmd.official.miublooddonors.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    private Spinner departmentsSpinner;
    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

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

    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departmentsSpinner = view.findViewById(R.id.spinner_departments);
        bloodGroupsSpinner = view.findViewById(R.id.spinner_blood_groups);
        locationsSpinner = view.findViewById(R.id.spinner_locations);

        firstNameEt = view.findViewById(R.id.first_name_et);
        lastNameEt = view.findViewById(R.id.last_name_et);
        batchEt = view.findViewById(R.id.batch_no_et);
        studentIdEt = view.findViewById(R.id.student_id_et);
        mobileEt = view.findViewById(R.id.mobile_et);

        firebaseDatabase = FirebaseDatabase.getInstance();


        continueBt = view.findViewById(R.id.user_info_continue_bt);

        setSpinners();

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = firstNameEt.getText().toString() + " " + lastNameEt.getText().toString();
                userBatch = batchEt.getText().toString();
                userMobile = mobileEt.getText().toString();
                userStudentId = studentIdEt.getText().toString();

                userBloodGroup = bloodGroupsSpinner.getSelectedItem().toString();
                String bloodGroupText = bloodGroupInText(userBloodGroup);
                userDepartment = departmentsSpinner.getSelectedItem().toString();
                userLocation = locationsSpinner.getSelectedItem().toString();

                Log.i("Check", userName + "," + userDepartment + "," +
                        userBatch + "," + userStudentId + "," + userBloodGroup + "," +
                        userLocation + "," + userMobile);

                firebaseReference = firebaseDatabase.getReference(bloodGroupText);

                inputUser(userName, userDepartment, userStudentId, userBatch, userLocation,
                        userMobile, userBloodGroup);
            }
        });
    }

    private void inputUser(String name, String department, String studentId, String batch, String location, String mobile, String bloodGroup) {

        UserInfo userInfo = new UserInfo(name, department, studentId, batch, location, mobile, bloodGroup);

        firebaseReference.setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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

        ArrayAdapter<CharSequence> departmentsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_departments, android.R.layout.simple_spinner_item);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(departmentsAdapter);


        ArrayAdapter<CharSequence> bloodGroupsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_blood_groups, android.R.layout.simple_spinner_item);
        bloodGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupsSpinner.setAdapter(bloodGroupsAdapter);


        ArrayAdapter<CharSequence> locationsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_locations, android.R.layout.simple_spinner_item);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationsSpinner.setAdapter(locationsAdapter);
    }
}
