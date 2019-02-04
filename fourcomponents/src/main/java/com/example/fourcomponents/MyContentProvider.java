package com.example.fourcomponents;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class MyContentProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH) ;

    private static final int  INSERT =  1 ;
    private static final int  DELETE=  2 ;
    private static final int  UPDATE =  3 ;
    private static final int  QUERY =  4 ;
    private static final int  QUERYONE =  5 ;

    static{
        matcher.addURI("this.is.my.first.test","insert",INSERT);
        matcher.addURI("this.is.my.first.test","delete",DELETE);
        matcher.addURI("this.is.my.first.test","update",UPDATE);
        matcher.addURI("this.is.my.first.test","query",QUERY);
        matcher.addURI("this.is.my.first.test","queryon",QUERYONE);

    }

    private MyOpenHelper helper ;
    private String table = "test";
    @Override
    public boolean onCreate() {   //创建即调用不需要构造方法？
        helper = new MyOpenHelper(getContext()) ;
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,  String  selection   ,  String[] selectionArg,  String Orderby) {
        if (matcher.match(uri) == QUERY) {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cur = db.query(table, projection, selection, selectionArg, null, null, Orderby);
            return cur;
        } else if (matcher.match(uri) == QUERYONE){
            SQLiteDatabase db = helper.getReadableDatabase();
            long id = ContentUris.parseId(uri) ;
            Cursor cur = db.query(table, projection, "id=?", new String[]{""+id}, null, null, Orderby);
            return cur;
        }else{
            throw new IllegalArgumentException("查询失败，路径错误") ;
        }
    }

    @Override
    public String getType( Uri uri) {
        if (matcher.match(uri) == QUERY) {
            return "vnd.android.cursor.dir/"+ table ;
        } else if (matcher.match(uri) == QUERYONE){
            return  "vnd.android.cursor.item/"+table ;
        }
        return null;
    }

    @Override
    public Uri insert( Uri uri,  ContentValues contentValues) {
        if (matcher.match(uri) == INSERT){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            db.insert(table,null,contentValues) ;
            getContext().getContentResolver().notifyChange(uri,null) ;
        }else{
            throw new IllegalArgumentException("非法路径") ;
        }
        return null;
    }

    @Override
    public int delete( Uri uri,  String s,  String[] strings) {
        if (matcher.match(uri) == DELETE){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            db.delete(table,s,strings) ;
            getContext().getContentResolver().notifyChange(uri,null) ;
        }else{
            throw new IllegalArgumentException("非法路径") ;
        }
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues contentValues,  String s,  String[] strings) {
        if (matcher.match(uri) == UPDATE){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            db.update(table,contentValues,s,strings) ;
            getContext().getContentResolver().notifyChange(uri,null) ;
        }else{
            throw new IllegalArgumentException("非法路径") ;
        }
        return 0;
    }
}
