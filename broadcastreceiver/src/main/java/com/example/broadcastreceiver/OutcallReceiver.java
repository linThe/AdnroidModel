package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutcallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取刚刚拨打的电话
        String outcallnumber = getResultData() ;
        //被监听的Context获取sp对象
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String ipnumber = sp.getString("ipnumber","") ;
//        设置号码
        setResultData(ipnumber+outcallnumber);

    }
}
