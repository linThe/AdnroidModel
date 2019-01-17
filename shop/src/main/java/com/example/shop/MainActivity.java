package com.example.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar ProgressBar1 ;
    private ProgressBar ProgressBar2 ;
    private ProgressBar ProgressBar3 ;
    private TextView tv_life ;
    private TextView tv_attack ;
    private TextView tv_agile ;
    private Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationData();
    }

    private void initializationData() {
        tv_life = (TextView)findViewById(R.id.tv_life) ;
        tv_agile = (TextView)findViewById(R.id.tv_agile) ;
        tv_attack = (TextView)findViewById(R.id.tv_attack) ;


        btn = (Button)findViewById(R.id.button) ;
        btn.setOnClickListener(this);

        ProgressBar1 = (ProgressBar) findViewById(R.id.progressBar1) ;
        ProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2) ;
        ProgressBar3 = (ProgressBar) findViewById(R.id.progressBar3) ;

        ProgressBar1.setMax(50);
    }

    public void  onClick(View v){
        Log.i("提示","到了") ;
        Intent intent = new Intent(this,shopActivity.class) ;
        startActivityForResult(intent,1024);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 1024){
            iteminfo i = (iteminfo) data.getSerializableExtra("op") ;
            int p2 = ProgressBar2.getProgress() ;
            int p1 = ProgressBar1.getProgress() ;
            int p3 = ProgressBar3.getProgress() ;
            Log.i("提示","空指针yichang") ;
            ProgressBar2.setProgress(p2+i.getAttack());
            ProgressBar1.setProgress(p1+i.getLife());
            ProgressBar3.setProgress(p3+i.getAgile());
            tv_life.setText(ProgressBar1.getProgress()+"");
            tv_attack.setText(ProgressBar2.getProgress()+"");
            tv_agile.setText(ProgressBar3.getProgress()+"");
        }
    }
}










