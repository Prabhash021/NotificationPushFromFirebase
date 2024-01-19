package com.example.notificationpushfromfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.security.PrivateKey;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MA";

    private static final int NOTIFICATION_PERMISSION_CODE = 102;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        getFcmToken();
        Log.e(TAG, "InMainActivity");



        if(getIntent().getExtras()!=null){
            Log.e(TAG,"in Main Activity intent > " + getIntent().getExtras().getString(""));



        }



    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, MainActivity.NOTIFICATION_PERMISSION_CODE);

        }else if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please provide permission")
                    .setTitle("Need Permission")
                    .setPositiveButton("Ok",((dialog, which) -> {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, MainActivity.NOTIFICATION_PERMISSION_CODE);
                        dialog.dismiss();
                    }))
                    .setNegativeButton("Cancel",((dialog, which) -> dialog.dismiss()));
            builder.show();
        }else {
            Log.e("MA","Permission given");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, MainActivity.NOTIFICATION_PERMISSION_CODE);
        }


    }

    private void getFcmToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
               if(task.isSuccessful()){
                   String token = task.getResult();
                   Log.e(TAG, token);
               }
            }
        });
    }


}