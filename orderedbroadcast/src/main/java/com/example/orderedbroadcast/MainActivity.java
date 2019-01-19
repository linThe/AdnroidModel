package com.example.orderedbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void click (View view){
        Intent intent = new Intent() ;
        intent.setAction("www.linyongshao.club") ;
        MyOrderedBroadcastReceiver3 receiver3 = new MyOrderedBroadcastReceiver3() ;
        sendOrderedBroadcast(intent,null,receiver3,null,0,null,null);

    }
}
