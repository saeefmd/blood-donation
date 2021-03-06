package com.saeefmd.official.blood_donation.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saeefmd.official.blood_donation.activity.ProfileActivity;
import com.saeefmd.official.blood_donation.activity.UserInfoActivity;
import com.saeefmd.official.blood_donation.data.CurrentUser;
import com.saeefmd.official.blood_donation.data.Variables;
import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.utilities.NetworkCheck;
import com.saeefmd.official.blood_donation.utilities.WaitAlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class LogInFragment extends Fragment {

    private static final String TAG = "LogInFragment";

    private RelativeLayout loginLayout;

    private EditText loginEmailEt;
    private EditText loginPasswordEt;

    private String userMail;
    private String userPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private WaitAlertDialog mWaitAlertDialog;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();

        loginLayout = view.findViewById(R.id.login_layout);
        loginEmailEt = view.findViewById(R.id.login_email_et);
        loginPasswordEt = view.findViewById(R.id.login_password_et);
        Button loginBt = view.findViewById(R.id.log_in_bt);
        TextView loginSignUpTv = view.findViewById(R.id.login_sign_up_tv);
        TextView forgotPasswordTv = view.findViewById(R.id.forgot_password_tv);

        mWaitAlertDialog = new WaitAlertDialog(getContext());

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userMail = loginEmailEt.getText().toString().trim();
                userPassword = loginPasswordEt.getText().toString().trim();

                NetworkCheck networkCheck = new NetworkCheck(getContext());

                if (checkTextFields()) {
                    if (networkCheck.isNetworkAvailable()) {
                        mWaitAlertDialog.show();

                        signIn(userMail, userPassword);
                    } else {
                        Snackbar snackbar = Snackbar.make(loginLayout, "No Internet!", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
            }
        });

        forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToPasswordResetFragment();
            }
        });

        loginSignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToSignUpFragment();
            }
        });
    }

    private void switchToSignUpFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new SignUpFragment()).addToBackStack(null).commit();
    }

    private void switchToPasswordResetFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new PasswordResetFragment()).addToBackStack(null).commit();
    }

    private void signIn(String mail, String password) {

        try {
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                mWaitAlertDialog.dismiss();

                                if (mAuth.getCurrentUser().isEmailVerified()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.i("Name: ", user.getEmail());

                                    CurrentUser.setUserEmail(getContext(), mail);

                                    Intent intent = new Intent(getContext(), UserInfoActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                } else {

                                    Toast.makeText(getContext(), "Please verify your email", Toast.LENGTH_SHORT).show();
                                }

                            } else {

                                mWaitAlertDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    private boolean checkTextFields() {

        if (userMail.isEmpty()) {
            loginEmailEt.setError("Empty");
        }

        if (userPassword.isEmpty()) {
            loginPasswordEt.setError("Empty");
        }

        if (!userMail.isEmpty() && !userPassword.isEmpty()) {

            return true;

        } else {

            return false;
        }
    }
}
