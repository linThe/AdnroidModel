package com.example.antivirus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Antivirus extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //开机子自启动,接收到开机广播启动跳转
        Intent intent1 = new Intent(context,MainActivity.class) ;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        context.startActivity(intent1);
    }
}
