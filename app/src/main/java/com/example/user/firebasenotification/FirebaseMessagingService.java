package com.example.user.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    public Context context;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            context = getApplicationContext();
            if (remoteMessage.getData().size() > 0) { // Data mesajı içeriyor mu
                sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            }
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendNotification(String title, String body) {

        try {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            long[] pattern = {500, 500, 500, 500};//Titreşim ayarı

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.rocket)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            try {
                Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + this.getPackageName() + "/raw/notification");
                Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
