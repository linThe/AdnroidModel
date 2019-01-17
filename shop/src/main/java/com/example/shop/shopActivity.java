package com.example.shop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class shopActivity extends AppCompatActivity implements View.OnClickListener {

    private  iteminfo ItemInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ItemInfo = new iteminfo("大剑",10,20,30) ;
        findViewById(R.id.button2).setOnClickListener(this);

       TextView name = (TextView) findViewById(R.id.tv_name);
        TextView life = (TextView)  findViewById(R.id.tv_life);
        TextView attack = (TextView)  findViewById(R.id.tv_attack);
        TextView agile = (TextView)  findViewById(R.id.tv_agile);

        name.setText("name"+ItemInfo.getName());
        life.setText("life"+ItemInfo.getLife());
        attack.setText("attack"+ItemInfo.getAttack());
        agile.setText("agile"+ItemInfo.getAgile());

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button2:
                Intent intent = new Intent() ;
                intent.putExtra("op",ItemInfo) ;
                setResult(1512,intent);
                finish();
                break;
        }
    }
}






















