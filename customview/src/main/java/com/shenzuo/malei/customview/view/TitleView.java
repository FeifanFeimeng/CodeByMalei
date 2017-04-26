package com.shenzuo.malei.customview.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shenzuo.malei.customview.R;

/**
 * Created by MaLei on 2017/4/26.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class TitleView extends FrameLayout {

    private TextView titleText;
    private Button back;
    public TitleView(@NonNull Context context) {
        super(context);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);

        titleText = (TextView) findViewById(R.id.tv_text);
        back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }

    public void setTitleText(String text) {
        titleText.setText(text);
    }

    public void setLeftButtonText(String text) {
        back.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l) {
        back.setOnClickListener(l);
    }

}
