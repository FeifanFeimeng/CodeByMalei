package com.shenzuo.malei.youkumenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import Utils.AnimationUtils;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private RelativeLayout rv_level1;
    private RelativeLayout rv_level2;
    private RelativeLayout rv_level3;

    boolean isLevel3Display = true;//为true时表示已经显示
    boolean isLevel2Display = true;
    boolean isLevel1Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initViews();
    }

    private void initViews() {
        //添加点击事件
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_mean).setOnClickListener(this);
        rv_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rv_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rv_level3 = (RelativeLayout) findViewById(R.id.rl_level3);

    }

    //监听menu按键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下了menu按键
        if(keyCode==KeyEvent.KEYCODE_MENU)
        {
            //如果当前有动画正在执行，则取消事件
            if(AnimationUtils.runningAnimationCount>0)
                return true;
            //如果有页面显示，则隐藏
            if(isLevel1Display)
            {
                long delay=0;
                //如果三级页面已显示则转出
                if(isLevel3Display)
                {
                    AnimationUtils.RotateOutAnim(rv_level3,delay);
                    isLevel3Display=false;
                    delay+=200;
                }
                //如果二次页面已显示则转出
                if(isLevel2Display)
                {
                    AnimationUtils.RotateOutAnim(rv_level2,delay);
                    isLevel2Display=false;
                    delay+=200;
                }
                AnimationUtils.RotateOutAnim(rv_level1,delay);

            }
            else//此时没有页面显示，则全部转出
            {
                AnimationUtils.RotateInAnim(rv_level1,0);
                AnimationUtils.RotateInAnim(rv_level2,200);
                AnimationUtils.RotateInAnim(rv_level3,400);

                isLevel2Display=true;
                isLevel3Display=true;
            }
            isLevel1Display=!isLevel1Display;
            return true;//消费了此事件
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        //如果当前有动画在执行，则消费此事件
        if (AnimationUtils.runningAnimationCount>0)
            return;
        switch (v.getId())
        {
            case R.id.ib_home:
                //点击了home
                //如果二级页面已经显示转出去
                if(isLevel2Display)
                {
                    long delay=0;
                    //如果三级页面已经显示转出去
                    if(isLevel3Display)
                    {
                        AnimationUtils.RotateOutAnim(rv_level3,0);
                        isLevel3Display=false;
                        delay+=200;
                    }
                    AnimationUtils.RotateOutAnim(rv_level2,delay);
                }
                else//二级页面未显示，则转出来
                {
                    AnimationUtils.RotateInAnim(rv_level2,0);
                    AnimationUtils.RotateInAnim(rv_level3,200);
                    isLevel3Display=true;
                }
                //置反
                isLevel2Display=!isLevel2Display;
                break;

            case R.id.ib_mean:
                //点击了menu
                //如果三级页面已显示，则转出去
                if(isLevel3Display){
                    AnimationUtils.RotateOutAnim(rv_level3,0);
                }else{//否则转进来
                    AnimationUtils.RotateInAnim(rv_level3,0);
                }
                //置反
                isLevel3Display=!isLevel3Display;
                break;

            default:
                break;
        }

    }
}
