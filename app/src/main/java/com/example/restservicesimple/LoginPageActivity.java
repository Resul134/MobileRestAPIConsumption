package com.example.restservicesimple;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "authetication";
    Button bookVisibility;
    Button loginButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_main);
        bookVisibility = findViewById(R.id.bookButton);
        loginButton = findViewById(R.id.loginPageButton);
        mAuth = FirebaseAuth.getInstance();
    }


    public void doLogin(View view)
    {
        EditText emailField = findViewById(R.id.mainEmailField);
        EditText passwordField = findViewById(R.id.mainPasswordField);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        Log.d("Email", email);
        Log.d("Password", password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("email", "signInUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            bookVisibility.setVisibility(View.VISIBLE);
                            loginButton.setVisibility(View.GONE);
                            startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
                            Log.d(TAG, user.getEmail());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("emailFail", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPageActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            //updateUI(null);
                            Log.e(TAG, "login failed");
                        }

                        // ...
                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        //Log.d(TAG, currentUser.toString());
    }


}
