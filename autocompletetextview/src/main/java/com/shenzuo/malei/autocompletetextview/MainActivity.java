package com.shenzuo.malei.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    //[0]声明AutoCompleteTextView要显示的数据
    private static final String[] COUNTRIES = new String[] {
            "laofang", "laozhang", "laoli", "laobi","laoli","laowang","aab","abb","cc"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.auto);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        auto.setAdapter(stringArrayAdapter);
    }
}
