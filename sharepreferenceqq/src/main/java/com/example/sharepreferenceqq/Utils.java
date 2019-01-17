package com.example.sharepreferenceqq;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static boolean saveUserInfo(Context context,String name,String psw){
        //可能只有activity才有getShared..方法
        SharedPreferences sp = context.getSharedPreferences("QQInfos",Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = sp.edit() ;

        editor.putString("name",name) ;
        editor.putString("psw",psw) ;

        editor.commit() ;
        return true ;
    }

    public static Map<String,?> getUserInfo(Context context){
        //可能只有activity才有getShared..方法
        SharedPreferences sp = context.getSharedPreferences("QQInfos",Context.MODE_PRIVATE) ;
        return  sp.getAll();
    }
}
