package com.example.finaltranquilproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginPage extends AppCompatActivity {

    EditText mEmail, mPassword;
    MaterialButton mLogInBtn;
    MaterialButton mSignUpBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);
        fAuth = FirebaseAuth.getInstance();
        mLogInBtn = findViewById(R.id.buttonLogin);
        mSignUpBtn = findViewById(R.id.signUpText);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        mLogInBtn.setOnClickListener(view -> {
            String email = mEmail.getText().toString().trim();
            String password =  mPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)){
                mEmail.setError("Email is required");
                return;
            }

            if(TextUtils.isEmpty(password)) {
                mPassword.setError("Password is required");
                return;
            }

            if (password.length() <6) {
                mPassword.setError("Password must be >=6 characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // authenticate the User

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginPage.this, "Logged In Successfully.",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), BarcodeScanner.class));
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }


        mSignUpBtn.setOnClickListener(v -> {
            Intent intent=new Intent(LoginPage.this, SignUpPage.class);
            startActivity(intent);
        });

    }
}