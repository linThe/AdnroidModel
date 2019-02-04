package com.example.fourcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver01 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "接收到了广播 : " + intent.getStringExtra("name");
        Log.i("Receiver01", msg) ;
        Log.i("Receiver01", intent.getAction()) ;

    }
}
