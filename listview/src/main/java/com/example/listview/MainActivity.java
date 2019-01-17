package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView list ;
    private ImageView imgv ;
    private TextView tv ;
    private String[] names = {"京东","QQ","QQ斗地主","新浪微博","网易云","天猫","UC浏览器","微信","淘宝","小米","华为"} ;
    private int[] icons = {R.drawable.dig,R.drawable.dig1,R.drawable.dig2,R.drawable.dig3,R.drawable.ewm,
            R.drawable.gu2,R.drawable.gu3,R.drawable.gu4,R.drawable.gu5,R.drawable.gy1,R.drawable.phone};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization() ;
    }

    private void initialization() {
        list = (ListView)findViewById(R.id.listview) ;
        list.setAdapter(new MBaseAdapter());

    }

    class MBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this,R.layout.item_layout,null) ;
            imgv = (ImageView)view.findViewById(R.id.imageView) ;
            tv = (TextView)view.findViewById(R.id.textView) ;

            tv.setText(names[position]);
            imgv.setImageResource(icons[position]);

            return view ;
        }
    }
}
