package com.example.serviceandroid;

import static com.example.serviceandroid.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;


public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Program", "Service onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra("inputExtra");
        sendNotification(text);

        return START_NOT_STICKY;
    }

    private void sendNotification(String text) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(1 , notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Program", "Service onDestroy");
    }
}
