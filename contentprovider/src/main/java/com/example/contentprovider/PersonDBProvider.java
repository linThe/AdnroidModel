package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

//用于暴露数据
public class PersonDBProvider extends ContentProvider {

    private PersonSQLiteOpenHelper helper ;
    //Uri匹配器，不匹配返回-1
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH) ;
    //Uri匹配成功时的返回码
    private static final int INSERT = 1 ;
    private static final int DELETE = 2 ;
    private static final int UPDATE = 1 ;
    private static final int QUERY = 1 ;
    private static final int QUERYONE = 1 ;

    //添加uri匹配规则(清单文件中指定的android:authorites)
    static{
        matcher.addURI("cn.itcast.db.personprovider","insert",INSERT);
        matcher.addURI("cn.itcast.db.personprovider","delete",DELETE);
        matcher.addURI("cn.itcast.db.personprovider","update",UPDATE);
        matcher.addURI("cn.itcast.db.personprovider","query",QUERY);
        //#为通配符，凡是符合query/都匹配
        matcher.addURI("cn.itcast.db.personprovider","query/#",QUERYONE);
    }
    @Override
    public boolean onCreate() {
        //getContext 获取创建该provider对象的Context ？？
        helper = new PersonSQLiteOpenHelper(getContext()) ;
        //为什么返回一个false？？
        return false ;
    }

    @Override
    //查询暴露数据
    public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs, String sortOrder) {
        //用uri作匹配，若返回码相等
        if (matcher.match(uri) == QUERY){
            SQLiteDatabase db = helper.getReadableDatabase() ;
//            表名，查询列，查询条件，查询参数，分组，having条件，排序方式
            Cursor cursor = db.query("person",projection,selection,selectionArgs,null,null,sortOrder);
            return cursor ;
        }else if (matcher.match(uri) == QUERYONE){
          SQLiteDatabase db = helper.getReadableDatabase() ;
            //这话在说什么？只知道拿了一个id而已
            long id = ContentUris.parseId(uri) ;
            Cursor cursor = db.query("person",projection,"id = ? " ,new String[]{id+""}, null,null,sortOrder) ;
            return cursor ;
        }else{
            throw new IllegalArgumentException("非法参数，路径不匹配") ;
        }
    }

    @Override
    public String getType( Uri uri) {
        if (matcher.match(uri) == QUERY){
            //返回uri是集合还是单个记录？？未知
             return  "vnd.android.cursor.dir/person" ;
        }else if (matcher.match(uri) == QUERYONE){
            return "vnd.android.cursor.item./person";
        }
        return null ;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (matcher.match(uri) == INSERT){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            db.insert("person",null, values) ;
            Log.i("INSERT","添加数据成功") ;
        }else{
            throw new IllegalArgumentException("非法参数，路径不匹配") ;
        }
        return null ;
    }

    @Override
    public int delete( Uri uri, String selection, String[] selectionArgs) {
        if (matcher.match(uri) == DELETE){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            return db.delete("person",selection,selectionArgs) ;
        }else{
            throw new IllegalArgumentException("非法参数，路径不匹配") ;
        }
    }

    @Override
    public int update( Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        if (matcher.match(uri) == UPDATE){
            SQLiteDatabase db = helper.getWritableDatabase() ;
            return db.update("person",values,selection,selectionArgs) ;
        }else{
            throw new IllegalArgumentException("非法参数，路径不匹配") ;
        }
    }

}
