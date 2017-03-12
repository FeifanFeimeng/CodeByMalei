package com.shenzuo.malei.codebymalei;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaLei on 2017/2/26 0026.
 * Email:ml1995@mail.ustc.edu.cn
 */
public class UserInfoUtil {
    public static boolean savUserInfo_android(Context mContext, String username, String password) {
        try{
            //通过context对象创造一个SharedPreference对象
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserInfo", mContext.MODE_PRIVATE);
            //通过SharedPreferences对象获得一个Edit对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //向Edit里添加数据
            editor.putString("username",username);
            editor.putString("password",password);
            //提交edit
            editor.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String,String> getUserIndo_android(Context mContext) {
        try{
            //通過Context創建一個shaerdPrefences對象
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserInfo", mContext.MODE_PRIVATE);
            //通過sharedPreference獲取存儲數據
            String username = sharedPreferences.getString("username","admin");
            String password = sharedPreferences.getString("password","admin");
            HashMap<String, String> hashMap = new HashMap<String,String>();
            hashMap.put("username",username);
            hashMap.put("password",password);
            return hashMap;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
