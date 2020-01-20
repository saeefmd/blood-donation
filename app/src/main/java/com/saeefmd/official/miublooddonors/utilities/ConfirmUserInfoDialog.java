package com.saeefmd.official.miublooddonors.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saeefmd.official.miublooddonors.activity.ProfileActivity;
import com.saeefmd.official.miublooddonors.data.Variables;
import com.saeefmd.official.miublooddonors.model.UserInfo;
import com.saeefmd.official.miublooddonors.R;

import static android.content.Context.MODE_PRIVATE;

public class ConfirmUserInfoDialog extends Dialog {

    private Context context;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userDepartment;
    private String userMobile;
    private String userBatch;
    private String userStudentId;

    private TextView userNameTv;
    private TextView userLocationTv;
    private TextView userBloodGroupTv;
    private TextView userDepartmentTv;
    private TextView userMobileTv;

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDatabase;

    WaitAlertDialog mWaitAlertDialog;

    public ConfirmUserInfoDialog(Context context,String userName, String userLocation, String userBloodGroup,
                                 String userDepartment, String userMobile, String userBatch, String userStudentId) {
        super(context);
        this.context = context;
        this.userName = userName;
        this.userLocation = userLocation;
        this.userBloodGroup = userBloodGroup;
        this.userDepartment = userDepartment;
        this.userMobile = userMobile;
        this.userBatch = userBatch;
        this.userStudentId = userStudentId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_user_info);

        userNameTv = findViewById(R.id.my_info_name_tv);
        userLocationTv = findViewById(R.id.my_info_location_tv);
        userBloodGroupTv = findViewById(R.id.my_info_blood_group_tv);
        userDepartmentTv = findViewById(R.id.my_info_department_tv);
        userMobileTv = findViewById(R.id.my_info_mobile_tv);

        mWaitAlertDialog = new WaitAlertDialog(context);

        firebaseDatabase = FirebaseDatabase.getInstance();

        setUserData();

        Button cancelBt = findViewById(R.id.my_info_cancel_bt);
        Button saveBt = findViewById(R.id.my_info_ok_bt);

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bloodGroupText = bloodGroupInText(userBloodGroup);
                firebaseReference = firebaseDatabase.getReference(bloodGroupText);

                mWaitAlertDialog.show();

                saveCurrentUser();
                inputUser();
                dismiss();
            }
        });
    }

    private void setUserData() {

        userNameTv.setText(userName);
        userLocationTv.setText("Location: " + userLocation);
        userBloodGroupTv.setText("Blood Group: " + userBloodGroup);
        userDepartmentTv.setText("Department of " + userDepartment);
        userMobileTv.setText("Mobile: " + userMobile);
    }

    private void inputUser() {

        UserInfo userInfo = new UserInfo(userName, userDepartment, userStudentId, userBatch, userLocation, userMobile, userBloodGroup);

        firebaseReference.child(userMobile).setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    mWaitAlertDialog.dismiss();
                    Toast.makeText(context, "Your Information Stored Successfully. Thank You", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
                    editor.putBoolean(Variables.FIRST_TIME_FLAG, false);
                    editor.apply();

                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                } else {
                    mWaitAlertDialog.dismiss();
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveCurrentUser() {

        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
        editor.putString(Variables.CURRENT_USER_NAME, userName);
        editor.putString(Variables.CURRENT_USER_DEPARTMENT, userDepartment);
        editor.putString(Variables.CURRENT_USER_BATCH, userBatch);
        editor.putString(Variables.CURRENT_USER_STUDENT_ID, userStudentId);
        editor.putString(Variables.CURRENT_USER_BLOOD_GROUP, userBloodGroup);
        editor.putString(Variables.CURRENT_USER_MOBILE, userMobile);
        editor.putString(Variables.CURRENT_USER_LOCATION, userLocation);
        editor.apply();
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
}
