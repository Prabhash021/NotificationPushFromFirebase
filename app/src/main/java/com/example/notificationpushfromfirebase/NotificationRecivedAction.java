package com.example.notificationpushfromfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationRecivedAction extends AppCompatActivity {

    TextView titleTxt, bodyTxt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_recived_action);

        titleTxt = findViewById(R.id.title);
        bodyTxt = findViewById(R.id.body);
        String s1 = "hello";
        bodyTxt.setText("new String"+ s1);
    }
}