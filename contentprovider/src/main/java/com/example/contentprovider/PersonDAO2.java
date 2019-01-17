package com.example.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PersonDAO2 {
    private PersonSQLiteOpenHelper helper ;

    public PersonDAO2(Context context){
        helper = new PersonSQLiteOpenHelper(context) ;
    }
    //int money 就先不写，看起来没用
    public long add(String name , String number){
        SQLiteDatabase db  = helper.getWritableDatabase() ;
        ContentValues cv = new ContentValues() ;
        cv.put("name",name);
        cv.put("number",number);
        long id = db.insert("person",null,cv) ;
        db.close();
        return id ;
    }
}
