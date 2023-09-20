package com.example.finaltranquilproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayingBarcodeData extends AppCompatActivity {

    TextView receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying_barcode_data);

        receiver_msg = (TextView) findViewById(R.id.received_data);

        Intent intent = getIntent();

        String str = intent.getStringExtra("message_key");

        receiver_msg.setText(str);


    }
}