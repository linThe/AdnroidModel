package com.example.fourcomponents;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    //父类有getReadableDB（）所以不用给到实例
    public MyOpenHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql1 = "DROP TABLE IF EXISTS test" ;
        sqLiteDatabase.execSQL(sql1);

        String sql = "CREATE TABLE test (" +
                "id INTEGER  PRIMARY KEY AUTOINCREMENT ," +
                "name VARCHAR(25) NOT NULL" +
                ") ;"  ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
