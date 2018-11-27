package qyh.androidprojecthelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * Created by lenovo on 2018/11/27.
 */

public class LuncherActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher2);
        final LinearLayout tv_lin = (LinearLayout) findViewById(R.id.text_lin);//要显示的字体
        final LinearLayout tv_hide_lin = (LinearLayout) findViewById(R.id.text_hide_lin);//所谓的布
        ImageView logo = (ImageView) findViewById(R.id.image);//图片
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        logo.startAnimation(animation);//开始执行动画
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //第一个动画执行完后执行第二个动画就是那个字体显示那部分
                animation = AnimationUtils.loadAnimation(LuncherActivity2.this, R.anim.luncher_text_splash_position);
                tv_lin.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(LuncherActivity2.this, R.anim.luncher_test_canvas);
                tv_hide_lin.startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 5000);
    }

    private void toMainActivity(){
        Intent intent=new Intent(LuncherActivity2.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
