package com.shenzuo.malei.tabhostexcesice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by MaLei on 2017/3/30.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class WeiXinActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv_weixin = new TextView(this);
        tv_weixin.setText("weixin");
        setContentView(tv_weixin);
    }
}
