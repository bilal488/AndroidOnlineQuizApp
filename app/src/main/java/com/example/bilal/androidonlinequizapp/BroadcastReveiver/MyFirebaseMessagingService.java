package com.example.bilal.androidonlinequizapp.BroadcastReveiver;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.bilal.androidonlinequizapp.Common.Common;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Bilal on 2/21/2018.
 */

//T7-->Start//
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        handleNotification(remoteMessage.getNotification().getBody());
    }

    private void handleNotification(String body) {
        Intent pushNotification = new Intent(Common.STR_PUSH);
        pushNotification.putExtra("message",body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

    }
}
//T7-->End//
