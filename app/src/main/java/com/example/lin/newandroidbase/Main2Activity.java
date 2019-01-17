package com.example.lin.newandroidbase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i("Main2Activity","onCreate()") ;

        Button intentb  = (Button)findViewById(R.id.intentb) ;
        intentb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("就是一个人的时候用的事件","匿名内部类");
            }
        });

    }

    protected void onStart(){
        super.onStart();
        Log.i("Main2Activity","onStart()") ;
    }

    protected  void onResume(){
        super.onResume();
        Log.i("Main2Activity","onResume()") ;
    }

    protected void  onPause(){
        super.onPause();
        Log.i("Main2Activity","onPause()") ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main2Activity","onStop()") ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main2Activity","onDestroy()") ;
    }

    public void  click(View view){
//        创建一个Intent对象，通过该对象开启第二个Activity
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
