package com.example.contentobserver;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    Uri uri = Uri.parse("content://sms/") ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv) ;

        ContentResolver cr = getContentResolver() ;
        cr.registerContentObserver(uri,true,new MyObserver(new Handler()));

    }
    private class MyObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Toast.makeText(MainActivity.this, "变化了", Toast.LENGTH_LONG).show();

            ContentResolver cr = getContentResolver() ;
            Cursor cs = cr.query(uri,new String[]{"address","date"},null,null,null) ;
            cs.moveToFirst() ;
            String add = cs.getString(0) ;
            long date = cs.getInt(1) ;

            tv.setText(add+date);
            cs.close();
        }
    }
}
