package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class show_register extends AppCompatActivity {

    private  TextView tv_name ;
    private  TextView tv_password;
    private TextView tv_sex ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_register);

        tv_name = (TextView)findViewById(R.id.tv_name) ;
        tv_password = (TextView)findViewById(R.id.tv_password) ;
        tv_sex = (TextView)findViewById(R.id.tv_sex) ;

        Intent intent = getIntent() ;
        tv_name.setText(intent.getStringExtra("name") );
        tv_password.setText(intent.getStringExtra("password"));
        tv_sex.setText(intent.getStringExtra("sex"));
    }
}
