package com.shenzuo.malei.smsmonitor;

import android.database.ContentObservable;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("Content://sms/");
        getContentResolver().registerContentObserver(uri,true,new MyContentObserver(new Handler()));
    }
    private class MyContentObserver extends ContentObserver{
        public MyContentObserver(Handler handler)
        {
            super(handler);
        }
        public void onChange(boolean selfChange)
        {
            System.out.println("来短信啦");
            super.onChange(selfChange);
        }
    }
}
