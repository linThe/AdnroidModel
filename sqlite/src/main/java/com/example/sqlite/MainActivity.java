package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    PersonSQLiteOpenHelper persqlite = new PersonSQLiteOpenHelper(this) ; // java.lang.StackOverflowError栈溢出，一直递归
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Boolean male = persqlite.find("lin2","male");
       Boolean person = persqlite.find("lin1_1","person");

        TextView tv =  (TextView)findViewById(R.id.tv) ;
        tv.setText(male+":"+person);

    }
}
