package com.shenzuo.malei.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MaLei on 2017/3/15 0015.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    private Context mContext;
    private List<String> mDatas;
    private OnItemClickLitener mOnItemClickLitener;

    HomeAdapter(Context context, List<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    //创建新VIew，并被LayoutManger调用
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

 /*   //将数据与界面进行绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }*/

    //获取数据数量
    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //onBindViewHolder方法则负责将数据与ViewHolder绑定
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.tv.setText(mDatas.get(position));//设置tv的文本

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }


    ////自定义的ViewHolder，持有每个Item的的所有界面元素
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



}
