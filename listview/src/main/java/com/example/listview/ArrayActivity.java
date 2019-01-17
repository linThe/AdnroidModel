package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ArrayActivity extends AppCompatActivity {
    private String[] spNames = {"京东","QQ","QQ斗地主","新浪微博","网易云","天猫","UC浏览器","微信","淘宝","华为","小米"} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);
        //你这个activity要在哪个itemlayout放什么数据
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.arrlistlayout,R.id.arr_tv,spNames) ;
        ListView listView = (ListView)findViewById(R.id.arrlistview);
        listView.setAdapter(aa);
    }
}
