package com.example.contentresolver;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click (View view){
        //逍遥模拟器无法发送短信所以没有信息，不饱错就得了
        Uri uri = Uri.parse("content://sms/") ;
        ContentResolver cr = getContentResolver() ;
        Cursor curs = cr.query(uri,new String[]{"address","date","type","body"},null,null,null) ;
        List<SmsInfo>  listSms = new ArrayList<SmsInfo>() ;
        while(curs.moveToNext()){
            String address = curs.getString(0) ;
            long date = curs.getInt(1) ;
            int type = curs.getInt(2) ;
            String body = curs.getString(3) ;

            SmsInfo sms = new SmsInfo(date,type,body,address) ;
            listSms.add(sms) ;
        }
        curs.close() ;
        SmsUtils.backUpSms(listSms,this) ;
    }
}
