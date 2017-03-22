package activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shenzuo.malei.layoutanimation.R;

import java.util.ArrayList;

import adapter.myAdapter;
import bean.NewsBean;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        ListView lv = (ListView) findViewById(R.id.lv_a);
        lv.setAdapter(new myAdapter(mContext,initDate(mContext)));
        lv.setOnItemClickListener(this);
    }


        //封装新闻的假数据到list中返回
        public static ArrayList<NewsBean> initDate(Context mContext) {
            ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
            for(int i=0;i<100;i++)
            {
                NewsBean newsBean = new NewsBean();
                newsBean.title ="谢霆锋经纪人：偷拍系侵权行为：";
                newsBean.des= "称谢霆锋隐私权收到侵犯，将保留追究法律责任";
                newsBean.news_url= "http://www.sina.cn";
                newsBean.icon = mContext.getResources().getDrawable(R.drawable.ic_launcher);//通过context对象将一个资源id转换成一个Drawable对象。
                arrayList.add(newsBean);

                NewsBean newsBean1 = new NewsBean();
                newsBean1.title ="知情人：王菲是谢霆锋心头最爱的人";
                newsBean1.des= "身边的人都知道谢霆锋最爱王菲，二人早有复合迹象";
                newsBean1.news_url= "http://www.baidu.cn";
                newsBean1.icon = mContext.getResources().getDrawable(R.drawable.icon);//通过context对象将一个资源id转换成一个Drawable对象。
                arrayList.add(newsBean1);



                NewsBean newsBean2 = new NewsBean();

                newsBean2.title ="热烈祝贺黑马74高薪就业";
                newsBean2.des= "74期平均薪资20000，其中有一个哥们超过10万，这些It精英都迎娶了白富美.";
                newsBean2.news_url= "http://www.itheima.com";
                newsBean2.icon = mContext.getResources().getDrawable(R.drawable.icon2);//通过context对象将一个资源id转换成一个Drawable对象。
                arrayList.add(newsBean2);
            }
            return arrayList;

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

