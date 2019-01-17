package com.example.contentprovider;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView lv ;
    private List<Person> persons ;
 //   @SuppressLint("HandlerLeak")  //黄黄的一片代表可以优化
    private Handler handler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {  //听说书上写的会造成内存泄露
            switch(msg.what){
                //如果查询数据完毕
                case 100 :
                    lv.setAdapter(new MyAdapter());
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv) ;
        //创建子线程完成耗时操作
        new Thread(){
            public void run(){
                //添加数据
                addData() ;
                //获取person集合
                getPersons() ;
                //if查询到数据则发送msg消息
                if (persons.size() > 0 ){
                    handler.sendEmptyMessage(100) ;
                }
            }
        }.start();

    }

    public void addData(){
        PersonDAO2 dao =  new PersonDAO2(this) ;
        Random ran = new Random() ;
        for (int i= 0  ; i < 10 ; i++){
            dao.add("lin"+i,"1326667124"+ran.nextInt(10)) ;
        }
    }

    public void getPersons(){
        String path = "content://cn.itcast.db.personprovider/query" ;
        Uri uri = Uri.parse(path) ;
        // 利用Resolver获得Provider的数据
        ContentResolver cr = getContentResolver() ;
        Cursor cursor = cr.query(uri,null,null,null,null) ;
        if(cursor == null) return ;
        persons  = new ArrayList<Person>() ;
        //移动到下一个指针，到末尾返回false？，为什么有可能一半boolean一半数据的值都能做判断
        while(cursor.moveToNext()){
            int id = cursor.getInt(0) ;
            String name = cursor.getString(1) ;
            String number = cursor.getString(2) ;

            Person per = new Person(id,name,number);
            persons.add(per) ;
        }
        cursor.close();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        //Adaper.getView()都要经历，根据context，布局创建View视图，找view视图里的控件,设置控件数据
        public View getView(int position, View convertView, ViewGroup parent) {
            //获得当前条目信息
            Person person = persons.get(position) ;

            //遵循重用原则
            View view = convertView == null ? View.inflate(MainActivity.this, R.layout.item,null) : convertView ;

            TextView tv_name = (TextView)view.findViewById(R.id.tv_name) ;
            TextView tv_tel = (TextView)view.findViewById(R.id.tv_tel) ;

            tv_name.setText(person.getName());
            tv_tel.setText(person.getNumber());

            return view; //这个getView相当于设置单个条目，循环getView就能将全部数据展示到list上

        }
    }


}




















