package com.example.notificationpushfromfirebase;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "MFMS";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.e(TAG, "Title " + Objects.requireNonNull(message.getNotification().getTitle()));
        Log.e(TAG, "tag " + Objects.requireNonNull(message.getNotification().getTag()));
        Log.e(TAG, "id " + Objects.requireNonNull(message.getNotification().getChannelId()));
        Log.e(TAG, "body " + Objects.requireNonNull(message.getData().get("type")));
        getFirebaseMessage(Objects.requireNonNull(message.getNotification()).getTitle(), message.getNotification().getBody(), Objects.requireNonNull(message.getData().get("type")));

        Intent intent = new Intent();
        intent.putExtra("title" ,message.getNotification().getTitle());

    }


    private void getFirebaseMessage(String title, String body, String type) {
        int openType = Integer.parseInt(type);
        
        Intent intent = new Intent(getApplicationContext(), NotificationRecivedAction.class);
        /*intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);*/
        intent.putExtra("title", title);
        intent.putExtra("", body);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, getIntent(openType), PendingIntent.FLAG_IMMUTABLE);
        Log.e(TAG, "Pending");
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "notify")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(title)
                .setContentText(body)
                .setContentIntent(pendingIntent);

        nm.createNotificationChannel(new NotificationChannel("notify", "New Channel", NotificationManager.IMPORTANCE_HIGH));
        nm.notify(103, notification.build());
        Log.e(TAG, "Notification Check");

    }

    public Intent getIntent(int openType) {
        Intent intent = new Intent();
        switch (openType){
            case 1:
                intent = new Intent(MyFirebaseMessagingService.this, NotificationRecivedAction.class);
                Log.e(TAG, "in switch"+ intent);
                return intent;
            case 2:
                Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show();
        }
        return intent;
    }
}
