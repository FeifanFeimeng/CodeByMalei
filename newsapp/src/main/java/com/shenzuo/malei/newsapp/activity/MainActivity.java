package com.shenzuo.malei.newsapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shenzuo.malei.newsapp.R;
import com.shenzuo.malei.newsapp.adapter.NewsAdapter;
import com.shenzuo.malei.newsapp.bean.NewsBean;

import java.util.ArrayList;

import static com.shenzuo.malei.newsapp.activity.NewsUtils.getAllNews;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView news_listView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        news_listView = (ListView) findViewById(R.id.lv_newsList);
        ArrayList<NewsBean> allNews=NewsUtils.getAllNews(mContext);

        //3.创建一个adapter设置给listview
        NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
        news_listView.setAdapter(newsAdapter);
        news_listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //需要获取条目上bean对象中url做跳转
        NewsBean newsBean = (NewsBean) parent.getItemAtPosition(position);
        String url = newsBean.news_url;
        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
