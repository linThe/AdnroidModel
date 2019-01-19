package com.example.orderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyOrderedBroadcastReceiver3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Test3"," 我是最小的《Priority：400") ;

        Log.i("test3","你有张良计策，我有过墙梯:在发送广播的时候打好关系") ;
    }
}
