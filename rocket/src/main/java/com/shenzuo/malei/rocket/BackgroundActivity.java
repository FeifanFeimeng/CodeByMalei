package com.shenzuo.malei.rocket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * Created by MaLei on 2017/3/24 0024.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class BackgroundActivity extends Activity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        ImageView iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
        ImageView iv_top = (ImageView) findViewById(R.id.iv_top);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);

        iv_bottom.startAnimation(alphaAnimation);
        iv_top.startAnimation(alphaAnimation);

        mHandler.sendEmptyMessageDelayed(0,1000);
    }
}
