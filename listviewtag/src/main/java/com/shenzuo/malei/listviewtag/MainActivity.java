package com.shenzuo.malei.listviewtag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.shenzuo.malei.listviewtag.adapter.TagsAdapter;
import com.shenzuo.malei.listviewtag.view.FlowLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView mTagsListView;
    private ArrayList<String> mTagContents;
    private LinearLayout mRoot;
    private ArrayList<String> mCheckedTags;
    private String mNames[] = {
            "welcome", "android", "TextView",
            "apple", "jamy", "kobe bryant",
            "jordan", "layout", "viewgroup",
            "margin", "padding", "text",
            "name", "type", "search", "logcat"
    };
    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initDate() {
        mTagContents = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String s = "tags" + i;
            mTagContents.add(s);
        }
        mTagsListView.setAdapter(new TagsAdapter(this, mTagContents,mFlowLayout));
        //listView条目点击事件的监听
        mTagsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox tagCB = (CheckBox) view.findViewById(R.id.cb_tag);
                //只进行单选框状态的切换，标签的显示并不处理，因为单选框状态一旦修改就会被TagsAdapter监听到并做处理
                if(tagCB.isChecked())
                {
                    tagCB.setChecked(false);
                }else
                {
                    tagCB.setChecked(true);
                }
            }
        });

    }

    private void initView() {
        mRoot = (LinearLayout) findViewById(R.id.ll_root);
        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        mTagsListView = (ListView) findViewById(R.id.lv_tags);
    }


}
