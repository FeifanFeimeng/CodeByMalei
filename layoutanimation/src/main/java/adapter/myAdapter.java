package adapter;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenzuo.malei.layoutanimation.R;

import java.util.ArrayList;

import bean.NewsBean;

/**
 * Created by MaLei on 2017/3/22 0022.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class myAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NewsBean> list;
    public myAdapter(Context mContext,ArrayList<NewsBean> list)
    {
        this.mContext=mContext;
        this.list=list;
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
        View view=null;
        if(convertView==null){
            view=View.inflate(mContext, R.layout.item_news,null);
        }else{
            view=convertView;
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
        return view;
    }
}
