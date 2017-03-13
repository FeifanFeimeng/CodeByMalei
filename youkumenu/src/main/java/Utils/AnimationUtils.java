package Utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by MaLei on 2017/3/13 0013.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class AnimationUtils {

    // 正在运行的动画个数
    public static int runningAnimationCount = 0;

    //旋转出去的动画
    public static void RotateOutAnim(RelativeLayout layout, long delay) {

        int childCount = layout.getChildCount();
        // 如果隐藏. 则找到所有的子View, 禁用
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(false);
        }
        RotateAnimation ra = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        ra.setDuration(500);
        ra.setFillAfter(true);//设置停留位置
        ra.setStartOffset(delay);//设置动画延时
        ra.setAnimationListener(new MyAnimtionListener());//添加监听
        layout.startAnimation(ra);

    }
    //旋转进去的动画
    public static void RotateInAnim(RelativeLayout layout, long delay) {
        int childCount = layout.getChildCount();
        //如果启用，则找到所有子View，启用
        for (int i=0;i<childCount;i++)
            layout.getChildAt(i).setEnabled(true);

        RotateAnimation ra = new RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(delay);
        ra.setAnimationListener(new MyAnimtionListener());
        layout.startAnimation(ra);

    }

    private static class MyAnimtionListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimationCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimationCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
