package com.shenzuo.malei.tabhostexcesice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by MaLei on 2017/3/30.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class My extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv_my = new TextView(this);
        tv_my.setText("wode");
        setContentView(tv_my);

    }
}
