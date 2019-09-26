package com.example.dailyworkscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoStart extends BroadcastReceiver {

    public static final String TAG = "rajasuba";
    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.i("")

        try {
            Log.i(TAG, "Inside on receive of AutoStart receiver");
            context.startService(new Intent(context, MyService.class));
        } catch (Exception ex) {
            Log.e(TAG, "Exception is : " + ex);
        }
    }
}
