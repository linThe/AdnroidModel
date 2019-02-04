package com.example.bindservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private MyService.MyBinder myBinder ;
    private MyConn myConn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn_bind(View view){
        if(myConn == null){
            myConn = new MyConn() ;
        }
        Intent intent = new Intent(this,MyService.class) ;
        bindService(intent,myConn,BIND_AUTO_CREATE) ;
    }

    public  void  btn_unbind(View view){
        if(myConn != null){
            unbindService(myConn);
            myConn = null ;
        }
    }

    public  void btn_call(View view){
        myBinder.callMethodINService();
    }

    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder)service ;
            Log.i("MainActivity","服务绑定成功"+myBinder.toString()) ;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MainActivity","已解绑") ;
        }
    }


}
