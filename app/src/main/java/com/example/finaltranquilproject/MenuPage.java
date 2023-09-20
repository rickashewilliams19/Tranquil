package com.example.finaltranquilproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        MaterialButton LogIN = (MaterialButton) findViewById(R.id.LogIN);
        MaterialButton signup = (MaterialButton) findViewById(R.id.signup);

        LogIN.setOnClickListener(v -> {
            Intent intent=new Intent(MenuPage.this, LoginPage.class);
            startActivity(intent);
        });

        signup.setOnClickListener(v -> {
            Intent intent=new Intent(MenuPage.this, SignUpPage.class);
            startActivity(intent);
        });
    }
}