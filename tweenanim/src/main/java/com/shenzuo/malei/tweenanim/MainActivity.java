package com.shenzuo.malei.tweenanim;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
                translate(v);
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
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.together);
        iv.startAnimation(ra);
    }

    private void playxml(View v) {
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.palyxml);
        iv.startAnimation(ra);
    }

    private void translate(View v) {
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        iv.startAnimation(ra);
    }

    private void scale(View v) {
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        iv.startAnimation(ra);
    }

    private void rotate(View v) {
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        iv.startAnimation(ra);
    }

    private void alpha(View v) {
        Animation ra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        iv.startAnimation(ra);
    }
}
