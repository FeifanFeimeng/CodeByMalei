package com.shenzuo.malei.propertyanimator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends Activity implements View.OnClickListener{

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alpha = (Button) findViewById(R.id.bt_alpha);
        Button rotate = (Button) findViewById(R.id.bt_rotate);
        Button translate = (Button) findViewById(R.id.bt_translate);
        Button scale = (Button) findViewById(R.id.bt_scale);
        Button palyxml = (Button) findViewById(R.id.bt_playxml);
        Button together = (Button) findViewById(R.id.bt_together);


        iv = (ImageView) findViewById(R.id.iv);

        alpha.setOnClickListener(this);
        rotate.setOnClickListener(this);
        translate.setOnClickListener(this);
        scale.setOnClickListener(this);
        palyxml.setOnClickListener(this);
        together.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bt_alpha:
                alpha(v);
                break;
            case R.id.bt_rotate:
                rotate(v);
                break;
            case R.id.bt_scale:
                scale(v);
                break;
            case R.id.bt_translate:
                tranlate(v);
                break;
            case R.id.bt_together:
                together(v);
                break;
            case R.id.bt_playxml:
                playxml(v);
                break;
            default:
                break;

        }

    }

    private void together(View v) {
        System.out.println("执行了together");
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, 50, 20, 100);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleY", 0.1f, 2, 1, 2);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.5f, 0, 1);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180, 90, 360);
        as.setDuration(2000);//执行动画时长
        as.setTarget(iv);//iv执行动画
        //往集合中添加动画
        //挨个飞
  //      as.playSequentially(oa, oa2, oa3, oa4);
        //一起飞
		as.playTogether(oa, oa2, oa3, oa4);
        as.start();
    }

    private void playxml(View v) {
        ObjectAnimator oa = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.oanimator);
        oa.setTarget(iv);
        oa.start();
    }

    private void tranlate(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, 50, 20, 100);
        oa.setDuration(2000);
        oa.start();
    }

    private void scale(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "scaleY", 0.1f, 2, 1, 2);
        oa.setDuration(2000);
        oa.start();
    }

    private void rotate(View v) {
        //ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotationX", 0, 180, 90, 360);
        oa.setDuration(2000);
        oa.start();
    }

    private void alpha(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.5f, 0, 1,0,1);
        oa.setDuration(2000);
        oa.start();
    }
}
