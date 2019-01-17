package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "PersonSQLiteOpenHelper" ;

    public PersonSQLiteOpenHelper(Context context){
        super(context, "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS person (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name VARCHAR(20) NOT NULL ," +
                "number VARCHAR(20) NOT NULL )" ;

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"数据库版本更新了") ;
    }
}
