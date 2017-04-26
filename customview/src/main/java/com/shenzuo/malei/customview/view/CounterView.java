package com.shenzuo.malei.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MaLei on 2017/4/26.
 * Email:ml1995@mail.ustc.edu.cn
 * 实现自定义点击计数view
 */

public class CounterView extends View implements View.OnClickListener {

    private Paint mPaint;
    private Rect mBounds;
    private int mCount;

    public CounterView(Context context) {
        super(context);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mBounds);
        int textWidth = mBounds.width();
        int textHeight = mBounds.height();
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);

    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
