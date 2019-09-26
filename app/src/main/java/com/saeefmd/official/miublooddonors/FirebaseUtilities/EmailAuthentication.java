package com.saeefmd.official.miublooddonors.FirebaseUtilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.saeefmd.official.miublooddonors.Activity.ProfileActivity;

import androidx.annotation.NonNull;

public class EmailAuthentication {

    private String userEmail;
    private String userPassword;
    private Context context;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public EmailAuthentication(String userEmail, String userPassword, Context context) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.context = context;
    }

    public void createAccount() {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                            sendEmailVerification();

                        } else {

                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendEmailVerification() {

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]

                        if (task.isSuccessful()) {

                            Toast.makeText(context, "Verification link sent. Please check your email.", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    public void signIn() {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser().isEmailVerified()) {

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.i("Name: ", user.getEmail());


                            } else {

                                Toast.makeText(context, "Please verify your email", Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }
}
