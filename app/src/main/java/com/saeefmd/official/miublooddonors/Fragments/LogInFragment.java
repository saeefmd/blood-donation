package com.saeefmd.official.miublooddonors.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saeefmd.official.miublooddonors.Activity.UserInfoActivity;
import com.saeefmd.official.miublooddonors.Data.Variables;
import com.saeefmd.official.miublooddonors.R;
import com.saeefmd.official.miublooddonors.Utilities.NetworkCheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class LogInFragment extends Fragment {

    private LinearLayout loginLayout;

    private EditText loginEmailEt;
    private EditText loginPasswordEt;

    private String userMail;
    private String userPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

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

        loginLayout = view.findViewById(R.id.login_layout);
        loginEmailEt = view.findViewById(R.id.login_email_et);
        loginPasswordEt = view.findViewById(R.id.login_password_et);
        Button loginBt = view.findViewById(R.id.log_in_bt);
        TextView loginSignUpTv = view.findViewById(R.id.login_sign_up_tv);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userMail = loginEmailEt.getText().toString().trim();
                userPassword = loginPasswordEt.getText().toString().trim();

                NetworkCheck networkCheck = new NetworkCheck(getContext());

                if (checkTextFields()) {
                    if (networkCheck.isNetworkAvailable()) {
                        signIn(userMail, userPassword);
                    } else {
                        Snackbar snackbar = Snackbar.make(loginLayout, "No Internet!", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }
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

    private void signIn(String mail, String password) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser().isEmailVerified()) {

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.i("Name: ", user.getEmail());

                                SharedPreferences.Editor editor = getContext().getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
                                editor.putBoolean(Variables.USER_SIGNED_IN, true);
                                editor.apply();

                                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                            } else {

                                Toast.makeText(getContext(), "Please verify your email", Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
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
