package com.example.fourcomponents;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText et_del_id ;
    EditText et_add_data ;
    EditText et_up_data ;
    EditText et_up_id ;

    Button bt_add ;
    Button bt_up ;
    Button bt_del ;
    Button bt_query ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        *   Create ContentProvider 提供数据
        *   ContentResolver 操作数据
        *   ContentobServer 监听改变
        *   OrderBroadcastReceive   处理信息
        * */
        initleView() ;
        befordate() ;  //为毛删不掉test表，导致数据无上限的增加

        Uri uri = Uri.parse("content://this.is.my.first.test/insert") ;

        ContentResolver cr = getContentResolver() ;
        cr.registerContentObserver(uri,true,new MyContentOBserver01(new Handler()));
        Log.i("CR","注册了ContentObserver") ;
        Toast.makeText(this,"主方法加载完毕",Toast.LENGTH_LONG).show();
    }

    private void initleView() {
        et_add_data = (EditText)findViewById(R.id.et_add_data) ;
        et_up_data = (EditText)findViewById(R.id.et_up_data) ;
        et_up_id = (EditText)findViewById(R.id.et_up_id) ;
        et_del_id = (EditText)findViewById(R.id.et_del_id) ;

        bt_add = (Button)findViewById(R.id.et_add) ;
        bt_del = (Button)findViewById(R.id.et_del) ;
        bt_up = (Button)findViewById(R.id.et_up) ;
        bt_query = (Button)findViewById(R.id.U)  ;

        bt_add.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_up.setOnClickListener(this);
        bt_query.setOnClickListener(this);
    }

    private void befordate() {
        Uri uri = Uri.parse("content://this.is.my.first.test/insert") ;
        ContentResolver contentResolver = getContentResolver() ;

        for (int i = 0 ; i < 10 ; i++){
            ContentValues cv = new ContentValues() ;
            cv.put("name","lin" + i);
            contentResolver.insert(uri,cv) ;
        }
    }


    @Override
    public void onClick(View view) {
        Log.i("click","进来了") ;
        Toast.makeText(this,"点击了",Toast.LENGTH_LONG).show();
        switch(view.getId()){
            case R.id.et_add :
                add() ;
                break;
            case  R.id.et_del :
                del() ;
                break;
            case  R.id.et_up :
                up() ;
                break ;
                default:
                    myQuery() ;
                    break;
        }
    }

    private void up() {
        Log.i("UP","点击了更新按钮") ;

        Uri uri = Uri.parse("content://this.is.my.first.test/update") ;

        ContentResolver cr  = getContentResolver() ;
        ContentValues cv = new ContentValues() ;

        String[] id = {et_up_id.getText().toString().trim()} ;
        cv.put("name",et_up_data.getText().toString().trim());

        cr.update(uri,cv,"id = ? ",id ) ;
        Log.i("UP","更新完成了") ;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void myQuery() {
        Log.i("Query","点击了查询") ;

        Uri uri = Uri.parse("content://this.is.my.first.test/query") ;

        ContentResolver cr  = getContentResolver() ;
        Cursor cursor =  cr.query(uri,null,null,null,null,null) ;
        while(cursor.moveToNext()) {
            Log.i("Query", cursor.getString(1));
        }
        cursor.close();
    }

    private void del() {
        Log.i("Del","点击了删除") ;

        Uri uri = Uri.parse("content://this.is.my.first.test/delete") ;

        ContentResolver cr  = getContentResolver() ;
        String id = et_del_id.getText().toString().trim() ;
        if (id.isEmpty()){
            id = "7" ;
        }
        cr.delete(uri,"id =  "+ id  ,null) ;
        Log.i("Del","已删除id = " + id + "的值") ;
        cr.delete(uri,"id > 10",null) ;
        Log.i("Del","不知道为什么表不会重建，为防止数据无限增加，将删除id超过10的记录") ;
    }

    private void add() {
        Log.i("Add","点击了添加按钮") ;

        Uri uri = Uri.parse("content://this.is.my.first.test/insert") ;

        ContentResolver cr  = getContentResolver() ;
        ContentValues cv = new ContentValues() ;
        cv.put("name",et_add_data.getText().toString().trim());

        cr.insert(uri,cv) ;
        Log.i("ADD",et_add_data.getText().toString().trim() + "添加完成了") ;
    }

    public class MyContentOBserver01 extends ContentObserver {
        MyContentOBserver01(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.i("Observer","hello 我接收到了provider更改的信息") ;
            String [] i = {
                    et_add_data.getText().toString().trim(),
                    et_del_id.getText().toString().trim(),
                    et_up_data.getText().toString().trim(),
                    et_up_id.getText().toString().trim()
            } ;
            for (String a : i) {
                if (!a.isEmpty()){
                    //发送有序广播
                    MyBroadcastReceiver03 r3 = new MyBroadcastReceiver03() ;
                    Intent intent = new Intent() ;
                    intent.putExtra("name",a) ;
                    intent.setAction("I.am.Action") ;  //广播发出去，需要用action来识别是否要接收
                    sendOrderedBroadcast(intent,null,r3,null,0,null,null);
                }
            }
        }

    }

}
