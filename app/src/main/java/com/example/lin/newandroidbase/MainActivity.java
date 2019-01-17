package com.example.lin.newandroidbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate(*****************************************************)") ;
        initialization();
        super.onCreate(savedInstanceState);
    }

    public void initialization() {
        Button btn1 = (Button)findViewById(R.id.btn1) ;
        Button btn2 = (Button)findViewById(R.id.btn2) ;
        btn2.setOnClickListener(new MyButton());
        btn1.setOnClickListener(new MyButton());

        Button intent = (Button)findViewById(R.id.in2) ;
        intent.setOnClickListener(this);
    }
//    主类中实现点击事件
    public void  onClick(View view){
        Log.i("why","点le");
        switch (view.getId())
        {
            case R.id.in2:
                Log.i("why","跳转le");
                Intent in = new Intent(this,Main2Activity.class) ;
                startActivity(in);
                break;
        }

    }
//    内部类监听按钮点击事件
    public  class MyButton implements View.OnClickListener {
        public void onClick(View view){
           switch(view.getId()){
               case R.id.btn1:
                   Log.i("内部类实现","点击了btn1");
                   System.out.println(view);
                   System.out.println(view.getId()+"=?="+R.id.btn1);
                   break;
                   case R.id.btn2:
                       Log.i("内部类实现","点击了btn2") ;
                       break;
           }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        Toast.makeText(this, "按键按下", Toast.LENGTH_SHORT).show();
        System.out.println(keyCode);
        System.out.println(event);
        return super.onKeyDown(keyCode,event) ;
    }

    public boolean onKeyUp(int keyCode,KeyEvent event){
        Toast.makeText(this, "按键弹起", Toast.LENGTH_SHORT).show();
        return super.onKeyUp(keyCode,event) ;
    }

    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX() ;
        float y =  event.getY() ;
        Toast.makeText(this,"当前坐标为：("+x+","+y+")" ,Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event) ;
    }

    public  void  onStart(){
        Log.i("MainActivity","onStart()") ;
        super.onStart();
    }

    public  void  onResume(){
        Log.i("MainActivity","onResume()") ;
        super.onResume();
    }
    public  void  onPause(){
        Log.i("MainActivity","onPause()") ;
        super.onPause();
    }

    public  void  onStop(){
        Log.i("MainActivity","onStop()") ;
        super.onStop();
    }

    public  void onDestroy(){
        Log.i("MainActivity","onDestroy()") ;
        super.onDestroy() ;
    }
}
