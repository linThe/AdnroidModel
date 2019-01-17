package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.sql.RowId;
import java.util.Random;

public class user_register extends AppCompatActivity {

    private EditText et_name;
    private EditText et_passwrod;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        et_name = (EditText)findViewById(R.id.et_name) ;
        et_passwrod = (EditText)findViewById(R.id.et_password) ;
         radioMale = (RadioButton)findViewById(R.id.radioMale) ;
         radioFemale  = (RadioButton)findViewById(R.id.radioFemale) ;
         ok = (Button)findViewById(R.id.ok) ;
         ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(new Random().nextInt(2) == 1) {
                     passDate();
                 }else {
                     passDateForResult();
                 }
             }
         });
    }
    //传递数据
    private void passDate() {
        Intent intent = new Intent(this,show_register.class) ;
        intent.putExtra("name",et_name.getText().toString().trim()) ;
        intent.putExtra("password",et_passwrod.getText().toString().trim()) ;
        if (radioMale.isChecked()){
            intent.putExtra("sex",radioMale.getText().toString().trim());
        }else{
            intent.putExtra("sex",radioFemale.getText().toString().trim());
        }
        startActivity(intent);
    }
    private void passDateForResult(){
        
    }
}
