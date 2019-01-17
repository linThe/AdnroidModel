package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {
    PersonSQLiteOpenHelper helper ;
    public PersonSQLiteOpenHelper(@Nullable Context context) {      // java.lang.StackOverflowError
        super(context, "person.db", null, 5);
        helper  = new PersonSQLiteOpenHelper(context) ; //面向对象

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mydb_DAO(db) ;
        db.beginTransaction();
        String updateOut  = "update person set lin1 = lin0 where name = ?" ;
        String updateIn  = "update person set lin0 = lin1_1 where name = ?" ;

        try{
            db.execSQL(updateOut,new String[]{"lin"});
            db.execSQL(updateIn,new String[]{"lin0"});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Error:","处理失败") ;
        }finally{
            db.endTransaction();
            db.close();
        }
    }

    private void mydb_DAO(SQLiteDatabase db) {

        String sqlCT = "create table male "+
                "(id integer primary key autoincrement,"+
                "name verchar(7) not null ,"+
                "age verchar(2) ,"+
                "taller verchar(3))" ;
        String sqlCTX = "create table person "+
                "(id integer primary key autoincrement,"+
                "name verchar(7) not null ,"+
                "age verchar(2) )";

        String sqlII = "insert into male (name,age,taller) values(?,?,?)" ;
        String[][] variable = {{"lin","19","170"},{"lin2","13","140"},{"lin3","69","190"}} ;

        String sqlUp = "update male set lin = yy where [ name = ?]" ;
        String [] varUp  = {"lin"} ;

        String sqlDel = "delete from male where name = yy";

        String sqlRq = "select * from male where name = ?"  ;
        String[] varRq = {"lin2","lin3"} ;


        //execute
        db.execSQL(sqlCT);
        db.execSQL(sqlCTX);

        for (String[] v : variable) {
            db.execSQL(sqlII,v);
        }

        db.execSQL(sqlUp,varUp);
        db.execSQL(sqlDel);
        db.rawQuery(sqlRq,varRq) ;
    }


    public  long add(String name,String number){
        SQLiteDatabase db = helper.getWritableDatabase() ;  //可能是这群叼毛
        ContentValues cv = new ContentValues() ;
        cv.put("name",name);
        cv.put("number",number);
        long id = db.insert("male",null,cv) ;
        db.close();
        return id ;
    }
    public  int update(String name,String number){
        SQLiteDatabase db = helper.getWritableDatabase() ;
        ContentValues cv = new ContentValues() ;
        cv.put("name",name);
        cv.put("number",number);
        int id = db.update("male",cv,"name = ?",new String[]{name}) ;
        db.close();
        return id ;
    }

    public  int delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase() ;
        int id = db.delete("male","name = ?",new String[]{name}) ;
        db.close();
        return id ;
    }

    public  boolean find(String name,String tName){
        SQLiteDatabase db = helper.getWritableDatabase() ;

        Cursor cs = db.query(tName,null,"name = ?",new String[]{name},null,null,null) ;
        boolean result = cs.moveToNext() ;
        cs.close();
        db.close();
        return result ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
