package com.shenzuo.malei.recyclerview;


import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tv = (TextView)findViewById(R.id.textView1);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
               //tv.setText("正在刷新");
                Toast.makeText(MainActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                       // tv.setText("刷新完成");
                        Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 6000);
            }
        });

        initData();//初始化数据
        //获取这个RecyclerView控件
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //创建默认的LinearLayoutManager管理器
       /*
       //横屏
       mLayoutManager= new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);*/
        //默认竖屏
        mLayoutManager=new LinearLayoutManager(this);//创建管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        /*//格局排列
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));*/

        //创建并设置适配器
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(this,mDatas));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
         DividerItemDecoration.VERTICAL_LIST));

        //设置点击事件
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
                TextView tv = (TextView) view.findViewById(R.id.id_num);
                tv.setTextColor(Color.parseColor("#996699"));
               // mAdapter.addData(position);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();

                mAdapter.removeData(position);
            }
        });

    }
    //初始化数据
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }



}