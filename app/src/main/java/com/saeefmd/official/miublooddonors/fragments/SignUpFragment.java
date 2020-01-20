package com.saeefmd.official.miublooddonors.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saeefmd.official.miublooddonors.R;
import com.saeefmd.official.miublooddonors.utilities.NetworkCheck;
import com.saeefmd.official.miublooddonors.utilities.WaitAlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private LinearLayout signupLayout;

    private EditText signupEmailEt;
    private EditText signupPasswordEt;
    private EditText signupConfirmPasswordEt;

    private String userEmail;
    private String userPassword;
    private String confirmPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private WaitAlertDialog mWaitAlertDialog;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupLayout = view.findViewById(R.id.signup_layout);
        signupEmailEt = view.findViewById(R.id.signup_email_et);
        signupPasswordEt = view.findViewById(R.id.signup_password_et);
        signupConfirmPasswordEt = view.findViewById(R.id.signup_confirm_password_et);
        Button signUpBt = view.findViewById(R.id.sign_up_bt);

        mWaitAlertDialog = new WaitAlertDialog(getContext());

        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = signupEmailEt.getText().toString().trim();
                userPassword = signupPasswordEt.getText().toString().trim();
                confirmPassword = signupConfirmPasswordEt.getText().toString().trim();

                NetworkCheck networkCheck = new NetworkCheck(getContext());

                if (checkTextFields()) {

                    if (networkCheck.isNetworkAvailable()) {
                        mWaitAlertDialog.show();

                        createAccount(userEmail, userPassword);
                    } else {

                        Snackbar snackbar = Snackbar.make(signupLayout, "No Internet!", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }

            }
        });
    }

    private void switchToLogInFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new LogInFragment()).addToBackStack(null).commit();
    }

    private boolean checkTextFields() {

        if (userEmail.isEmpty()) {
            signupEmailEt.setError("Empty");
        }

        if (userPassword.isEmpty()) {
            signupPasswordEt.setError("Empty");
        }

        if (confirmPassword.isEmpty()) {
            signupConfirmPasswordEt.setError("Empty");
        }

        if (!userPassword.equals(confirmPassword)) {
            signupConfirmPasswordEt.setError("Not Matched");
        }

        if (!userEmail.isEmpty() && !userPassword.isEmpty() && !confirmPassword.isEmpty()) {
            if (userPassword.equals(confirmPassword)) {
                return true;
            }
        }

        return false;
    }

    public void createAccount(String mail, String password) {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            mWaitAlertDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                            sendEmailVerification();
                            switchToLogInFragment();

                        } else {
                            mWaitAlertDialog.dismiss();

                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendEmailVerification() {

        // Send verification userEmail
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]

                        if (task.isSuccessful()) {

                            Toast.makeText(getContext(), "Verification link sent. Please check your Email.", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
}
