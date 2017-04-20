package com.shenzuo.malei.viewpagertest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义控件
    private WeixinFragment mWeixin;
    private FriendFragment mFriend;
    private FindFragment mFind;
    private MyFragment mMy;


    //四个Tab每个Tab都有一个按钮
    private LinearLayout mTabMyCircle;
    private LinearLayout mTabMyDiscovery;
    private LinearLayout mTabMyMsg;
    private LinearLayout mTabMyCenter;
    //四个按钮
    private ImageButton myCircleImg;
    private ImageButton myDiscoveryImg;
    private ImageButton myMsgImg;
    private ImageButton myCenterImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();//初始化控件

        myCircleImg.setOnClickListener(this);
        myDiscoveryImg.setOnClickListener(this);
        myMsgImg.setOnClickListener(this);
        myCenterImg.setOnClickListener(this);

        setDefaultFragment();
    }
    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mWeixin = new WeixinFragment();
        transaction.replace(R.id.id_content, mWeixin);
        transaction.commit();
    }

    private void initViews() {

        //初始化四个布局
        mTabMyCircle = (LinearLayout) findViewById(R.id.id_tab_mycircle);
        mTabMyDiscovery = (LinearLayout) findViewById(R.id.id_tab_discovery);
        mTabMyMsg = (LinearLayout) findViewById(R.id.id_tab_message);
        mTabMyCenter = (LinearLayout) findViewById(R.id.id_tab_setting);
        //初始化四个按钮
        myCircleImg = (ImageButton) findViewById(R.id.id_tab_mycirlceImg);
        myDiscoveryImg = (ImageButton) findViewById(R.id.id_tab_discovery_img);
        myMsgImg = (ImageButton) findViewById(R.id.id_tab_message_img);
        myCenterImg = (ImageButton) findViewById(R.id.id_tab_setting_img);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.id_tab_mycirlceImg:
                if (mWeixin == null)
                {
                    mWeixin = new WeixinFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, mWeixin);
                break;
            case R.id.id_tab_discovery_img:
                if (mFriend == null)
                {
                    mFriend = new FriendFragment();
                }
                transaction.replace(R.id.id_content, mFriend);
                break;

            case R.id.id_tab_message_img:
                if(mFind == null)
                {
                    mFind = new FindFragment();
                }
                transaction.replace(R.id.id_content,mFind);
                break;
            case R.id.id_tab_setting_img:
                if(mMy == null)
                {
                    mMy = new MyFragment();
                }
                transaction.replace(R.id.id_content,mMy);
                break;
            default:
                break;

        }
        transaction.commit();


    }


}
