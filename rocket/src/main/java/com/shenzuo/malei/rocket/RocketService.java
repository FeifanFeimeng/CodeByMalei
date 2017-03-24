
package com.shenzuo.malei.rocket;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import static android.view.WindowManager.*;

/**
 * Created by MaLei on 2017/3/24 0024.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class RocketService extends Service {

    private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    private WindowManager mWM;
    private int mScreenHeight;
    private int mScreenWidth;
    private View viewToast;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mParams.y= (int) msg.obj;
            mWM.updateViewLayout(viewToast,mParams);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //初始化窗体对象
        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        //获取屏幕宽度和高度
        mScreenWidth = mWM.getDefaultDisplay().getWidth();
        mScreenHeight = mWM.getDefaultDisplay().getHeight();
        Log.i("malei","屏幕高度"+mScreenHeight+"屏幕宽度"+mScreenWidth);

        showRocker();
        super.onCreate();
    }

    private void showRocker() {
        final WindowManager.LayoutParams params = mParams;
        params.height = LayoutParams.WRAP_CONTENT;
        params.width = LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE	因为吐司需要根据手势去移动,所以必须要能触摸
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;//不在和吐司类型相互绑定,通话的类型相互绑定
        //将吐司放置在左上角显示
        params.gravity= Gravity.TOP+Gravity.LEFT;
        params.setTitle("Toast");

        //定义吐司布局xml--->view挂载到屏幕上

        viewToast = View.inflate(this, R.layout.rocket_view, null);

        ImageView iv_rocket = (ImageView) viewToast.findViewById(R.id.iv_rocket);
        //获取设置了动画的背景,然后让此背景执行
        AnimationDrawable drawable = (AnimationDrawable) iv_rocket.getBackground();
		//iv_rocket.startAnimation(drawable);
        //获取背景图片后,让其动起来
        drawable.start();

        iv_rocket.setOnTouchListener(new View.OnTouchListener() {

            private int startX;
            private int startY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //获取按下的XY坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //获取移动xy坐标和按下的xy坐标做差,做差得到的值小火箭移动的距离
                        //移动过程中做容错处理
                        //第一次移动到的位置,作为第二次移动的初始位置
                        int endX = (int) event.getRawX();
                        int endY = (int) event.getRawY();

                        int disX = endX-startX;
                        int disY = endY-startY;

                        params.x = params.x+disX;
                        params.y = params.y+disY;

                        //容错处理
                        if(params.x<0){
                            params.x=0;
                        }
                        if(params.y<0){
                            params.y=0;
                        }
                        if(params.x>mScreenWidth-viewToast.getWidth()){
                            params.x = mScreenWidth-viewToast.getWidth();
                        }
                        if(params.y>mScreenHeight-22-viewToast.getHeight()){
                            params.y = mScreenHeight-22-viewToast.getHeight();
                        }
                        mWM.updateViewLayout(viewToast,params);

                        //在第一次移动完成后,将最终坐标作为第二次移动的起始坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //手指放开的时候,如果放手坐标,则指定区域内
                        if(params.x>180 && params.x<400 && params.y>300){
                            Log.i("malei","进入发射区域");
                            //火箭的发射
                            sendRocket();
                            //在开启火箭过程中,去开启一个新的activity,activity透明,在此activity中放置两张图片(淡入淡出效果)
                            Intent intent = new Intent(getApplicationContext(),BackgroundActivity.class);
                            //指定开启新的activity任务栈
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        mWM.addView(viewToast,params);
    }

    private void sendRocket() {
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<11;i++)
                {
                    int y = 350-i*35;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.obj=y;
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }


    @Override
    public void onDestroy() {
        if(mWM!=null && viewToast!=null)
            mWM.removeView(viewToast);
        super.onDestroy();
    }
}
