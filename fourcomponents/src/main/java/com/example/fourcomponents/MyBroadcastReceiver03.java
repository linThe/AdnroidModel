package com.example.fourcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver03 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "我还是接收到了广播 : " + intent.getStringExtra("name");
        Log.i("Receiver01", msg) ;
        Log.i("Receiver01", intent.getAction()) ;
        if (intent.getStringExtra("name").equals("10086")){
            Log.e("R3","我不给你发10086的信息") ;
        }
    }
}
