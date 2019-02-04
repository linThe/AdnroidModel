package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    class MyBinder extends Binder{
        public void callMethodINService(){ //回调方法
            Log.i("流程","序1") ;
            methodInServiece();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("这是","onCreate") ;
    }

    private IBinder methodInServiece() {
        Log.i("这是","自定义方法") ;
        return new MyBinder() ;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("这是","onBing()绑定服务方法") ;
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("这是","onDestroy()") ;
    }
}
