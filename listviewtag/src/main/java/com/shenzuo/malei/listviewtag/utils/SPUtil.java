package com.shenzuo.malei.listviewtag.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by MaLei on 2017/4/1.
 * Email:ml1995@mail.ustc.edu.cn
 * SharedPreference工具类
 */

public class SPUtil {

    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public static void setBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static int getInt(Context context,String key,int defValue){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }

    public static void setint(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static String getString(Context context,String key,String defValue){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    public static void setString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("mCheckedTags", context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

}

