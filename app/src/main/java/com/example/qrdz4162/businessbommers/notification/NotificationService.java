package com.example.qrdz4162.businessbommers.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.productview.ProductViewActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by qrdz4162 on 1/28/2018.
 */

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent notificationIntent = new Intent(getApplicationContext(), ProductViewActivity.class);
        notificationIntent.putExtra("product_code", "MAN10003");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

        Notification notification = builder.setContentTitle("BusinessBommers Notification")
                .setContentText(remoteMessage.getNotification().getBody())
                .setTicker("New Message Alert!")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_product_placeholder).build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);


    }

}
