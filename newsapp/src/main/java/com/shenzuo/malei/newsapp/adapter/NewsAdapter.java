package com.shenzuo.malei.newsapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzuo.malei.newsapp.R;
import com.shenzuo.malei.newsapp.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by MaLei on 2017/2/28 0028.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NewsBean> list;

    //通过构造方法接受要显示的新闻数据集合
    public NewsAdapter(Context context, ArrayList<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
        if (convertView == null) {
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
            view = View.inflate(context, R.layout.item_news_layout,null);//将一个布局文件转换成一个view对象

        } else
            view = convertView;
        //2.获取view上的子控件对象
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
        NewsBean newsBean = list.get(position);
        //4.将数据设置给这些子控件做显示
        item_img_icon.setImageDrawable(newsBean.icon);
        item_tv_des.setText(newsBean.des);
        item_tv_title.setText(newsBean.title);
        return view;
    }
}
