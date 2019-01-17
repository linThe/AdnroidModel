package com.example.sharepreferenceqq;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_name,et_psw ;
    private Button btn ;
    private Utils utils ;
    private String name,psw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialia() ;

        Map<String,?> map = utils.getUserInfo(this) ;
        if (map != null){
            name = (String)map.get("name") ;
            psw = (String)map.get("psw") ;
            et_name.setText(name);
            et_psw.setText(psw);
        }
    }

    private void initialia() {
        et_name = (EditText)findViewById(R.id.et_name) ;
        et_psw = (EditText)findViewById(R.id.et_psw) ;

        btn = (Button)findViewById(R.id.btn_login) ;
        btn.setOnClickListener(this);
        //初始化data

    }

    @Override
    public void onClick(View v) {
        name = et_name.getText().toString().trim() ;
        psw = et_psw.getText().toString().trim() ;
        if (name.isEmpty() && psw.isEmpty()){
            Toast.makeText(this,"密码或账号错",Toast.LENGTH_SHORT).show(); ;
        }
        if(utils.saveUserInfo(this,name,psw) ){
            Toast.makeText(this,"澈哥哥",Toast.LENGTH_SHORT).show(); ;
        }else{
            Toast.makeText(this,"错le",Toast.LENGTH_SHORT).show(); ;
        }
    }
}
