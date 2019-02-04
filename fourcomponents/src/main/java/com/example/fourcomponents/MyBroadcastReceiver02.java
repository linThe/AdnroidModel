package com.example.fourcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver02 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "接收到了广播 : " + intent.getStringExtra("name");
        Log.i("Receiver02", msg) ;
        Log.i("Receiver02", intent.getAction()) ;
        abortBroadcast();
        Log.i("Receiver02","我拦截了广播") ;
    }
}
