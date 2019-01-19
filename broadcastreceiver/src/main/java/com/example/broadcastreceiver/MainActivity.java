package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et ;
    SharedPreferences sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        et = (EditText)findViewById(R.id.et_ipnumber) ;
        sp = getSharedPreferences("config",MODE_PRIVATE) ;

        et.setText(sp.getString("ipnumber",""));
    }

    public void click(View view){
        String ipnumber = et.getText().toString().trim() ;
        if (ipnumber.isEmpty()){
            ipnumber = "10086" ;
        }
        SharedPreferences.Editor  editor = sp.edit() ;
            editor.putString("ipnumber",ipnumber) ;
            editor.commit() ;
        Toast.makeText(this,"yes",Toast.LENGTH_LONG).show();
    }
}
