package com.example.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyOrderBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        Log.i("OrderBroadcast2","我是老二，priority：600") ;
        abortBroadcast();
        Log.i("hhh","老二干点坏事，用abortBroadcast截断老三消息，呵呵呵") ;
    }
}
