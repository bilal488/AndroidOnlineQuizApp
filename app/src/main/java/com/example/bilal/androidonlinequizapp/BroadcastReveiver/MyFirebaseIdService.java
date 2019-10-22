package com.example.bilal.androidonlinequizapp.BroadcastReveiver;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Bilal on 2/21/2018.
 */

//T7-->Start//
public class MyFirebaseIdService extends FirebaseInstanceIdService {

    // Press Ctrl+O

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        Log.d("TOKEN",refreshedToken);
    }
}
//T7-->End//