package com.shenzuo.malei.tabhostexcesice;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhostlayout);

        TabHost.TabSpec weixin = getTabHost().newTabSpec("weixin").setIndicator("微信");
        TabHost.TabSpec my = getTabHost().newTabSpec("wode").setIndicator("我的");

        weixin.setContent(new Intent(this,WeiXinActivity.class));
        my.setContent(new Intent(this,My.class));

        getTabHost().addTab(weixin);
        getTabHost().addTab(my);
    }
}
