package com.shenzuo.malei.listviewtag.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzuo.malei.listviewtag.R;
import com.shenzuo.malei.listviewtag.utils.SPUtil;
import com.shenzuo.malei.listviewtag.view.FlowLayout;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by MaLei on 2017/4/7.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class TagsAdapter extends BaseAdapter {

    private ArrayList<String> mTagContents;
    private Context mContext;
    private ArrayList<String> mCheckedTags;
    private FlowLayout mFlowLayout;
    private CheckBox tagCB;

    public TagsAdapter(Context context, ArrayList<String> mTagContents, FlowLayout mFlowLayout) {
        this.mContext = context;
        this.mTagContents = mTagContents;
        this.mFlowLayout = mFlowLayout;
        //将保存在本地的标签取出并显示
        mCheckedTags = new ArrayList<>();
        String CheckedTags = SPUtil.getString(mContext, "mCheckedTags", "");
        String[] sourceStrArray = CheckedTags.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            System.out.print("    "+sourceStrArray[i]);
            if(!TextUtils.isEmpty(sourceStrArray[i]))
                mCheckedTags.add(sourceStrArray[i]);
        }
        reDraw();
    }

    @Override
    public int getCount() {
        return mTagContents.size();
    }

    @Override
    public String getItem(int position) {
        return mTagContents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        //mCheckedTags = new ArrayList<>();
        view = View.inflate(mContext, R.layout.tag_list_item, null);
        TextView tagContent = (TextView) view.findViewById(R.id.tv_tag);

        tagCB = (CheckBox) view.findViewById(R.id.cb_tag);
        tagContent.setText(mTagContents.get(position));
        //设置已勾选的标签
        String CheckedTags = SPUtil.getString(mContext, "mCheckedTags", "");
        if(CheckedTags.contains(mTagContents.get(position)))
        {
            //如果已经勾选此标签
            tagCB.setChecked(true);
        }else
        {
            //未勾选
            tagCB.setChecked(false);
        }

        //监听单选框状态
        tagCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String currentCheckTags=SPUtil.getString(mContext, "mCheckedTags", "");
                if(isChecked){
                    //Toast.makeText(mContext, mTagContents.get(position)+"被选中", Toast.LENGTH_SHORT).show();
                    if(mCheckedTags.contains(mTagContents.get(position)))
                    {
                        mCheckedTags.remove(mTagContents.get(position));
                        //将修改保存到SP
                        if(currentCheckTags.contains(mTagContents.get(position)))
                            currentCheckTags=currentCheckTags.replace(mTagContents.get(position),"");
                    }
                    else
                    {
                        mCheckedTags.add(mTagContents.get(position));
                        currentCheckTags = currentCheckTags+mTagContents.get(position)+",";
                    }
                }else{
                    mCheckedTags.remove(mTagContents.get(position));
                    if(currentCheckTags.contains(mTagContents.get(position)))
                        currentCheckTags=currentCheckTags.replace(mTagContents.get(position),"");
                }
                //提交本次修改
                SPUtil.setString(mContext,"mCheckedTags",currentCheckTags);
                reDraw();
            }
        });
        return view;
    }

/*    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // View view = null;
        mCheckedTags = new ArrayList<>();
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.tag_list_item, null);
            viewHolder.tagContent = (TextView) convertView.findViewById(R.id.tv_tag);
            viewHolder.tagCB = (CheckBox) convertView.findViewById(R.id.cb_tag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tagContent.setText(mTagContents.get(position));
        //单选框的点击事件
        viewHolder.tagCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Toast.makeText(mContext, mTagContents.get(position)+"被选中", Toast.LENGTH_SHORT).show();
                    if(mCheckedTags.contains(mTagContents.get(position)))
                    {
                        mCheckedTags.remove(mTagContents.get(position));
                    }
                    else
                    {
                        mCheckedTags.add(mTagContents.get(position));
                    }
                }else{
                    mCheckedTags.remove(mTagContents.get(position));
                }
                reDraw();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        public TextView tagContent;
        public CheckBox tagCB;
    }*/

    //当选中tag集合数据变化时，重新绘画
    public void reDraw(){
        //Log.i("malei","redraw被调用");
        mFlowLayout.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mCheckedTags.size(); i++) {
            final View textView =  View.inflate(mContext,R.layout.tag_item,null);
            final TextView tagContent = (TextView) textView.findViewById(R.id.tv_tagContent);
            ImageView delete = (ImageView) textView.findViewById(R.id.iv_delete);
            tagContent.setText(mCheckedTags.get(i));
            tagContent.setTextColor(Color.WHITE);

            //设置删除点击事件
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.i("malei","delete触发");
                    int i = mCheckedTags.indexOf(tagContent.getText());
                    String CheckedTags = SPUtil.getString(mContext, "mCheckedTags", "");
                    if(i>=0 && i<mCheckedTags.size())
                    {
                        mCheckedTags.remove(i);
                        if(CheckedTags.contains(tagContent.getText()))
                            CheckedTags=CheckedTags.replace(tagContent.getText(),"");
                        SPUtil.setString(mContext,"mCheckedTags",CheckedTags);
                    }
                    reDraw();
                    notifyDataSetChanged();
                }
            });
            //设置圆角背景
            textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.textview_bg));
            //标签长按删除
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int i = mCheckedTags.indexOf(tagContent.getText());
                    String CheckedTags = SPUtil.getString(mContext, "mCheckedTags", "");
                    if(i>=0 && i<mCheckedTags.size())
                    {
                        mCheckedTags.remove(i);
                        if(CheckedTags.contains(tagContent.getText()))
                            CheckedTags=CheckedTags.replace(tagContent.getText(),"");
                        SPUtil.setString(mContext,"mCheckedTags",CheckedTags);
                    }
                    reDraw();
                    notifyDataSetChanged();
                    return true;
                }
            });
            mFlowLayout.addView(textView,lp);
        }
    }

   /* //当选中tag集合数据变化时，重新绘画
    public void reDraw(){
        //Log.i("malei","redraw被调用");
        mFlowLayout.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        // ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(100, 100);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mCheckedTags.size(); i++) {
            final TextView textView = new TextView(mContext);
            textView.setText(mCheckedTags.get(i));
            textView.setTextColor(Color.WHITE);
            //设置删除标签图片
            Drawable tag_delete=mContext.getResources().getDrawable(R.mipmap.tag_delete);
            tag_delete.setBounds(0, 0, tag_delete.getMinimumWidth(), tag_delete.getMinimumHeight());
            textView.setCompoundDrawables(null, null, tag_delete, null);

            textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.textview_bg));
            //标签长按删除
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int i = mCheckedTags.indexOf(textView.getText());
                    String CheckedTags = SPUtil.getString(mContext, "mCheckedTags", "");
                    if(i>=0 && i<mCheckedTags.size())
                    {
                        mCheckedTags.remove(i);
                        if(CheckedTags.contains(textView.getText()))
                            CheckedTags=CheckedTags.replace(textView.getText(),"");
                        SPUtil.setString(mContext,"mCheckedTags",CheckedTags);
                    }
                    reDraw();
                    notifyDataSetChanged();
                   return true;
                }
            });
            mFlowLayout.addView(textView, lp);
        }
    }*/
  /*  //1.回调接口
    public interface OnDataChangeListener{
        public void OnDataChange();
    }
    //2.暴露接口，设置监听
    public void setOnDataChangeListener( OnDataChangeListener mListener){
        this.mListerner=mListener;

    }
    //定义成员变量，接收监听对象
    private OnDataChangeListener mListerner;*/
}
