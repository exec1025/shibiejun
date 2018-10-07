package qyh.androidprojecthelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

import qyh.androidprojecthelper.base.BaseActivity;
import qyh.androidprojecthelper.bean.TabEntity;
import qyh.androidprojecthelper.fragment.FirstTabFragment;

import qyh.androidprojecthelper.fragment.MoreWindow;
import qyh.androidprojecthelper.fragment.SecondMapFragment;
import qyh.androidprojecthelper.fragment.SecondMapFragment_;
import qyh.androidprojecthelper.fragment.ThirdTabFragment;

/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                      **
 * ************************************************************************
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private String[] mTitles={"随便看看","附近","拍一拍","我的"};
    private int[] mIconUnselectIds={R.mipmap.n_suibiankankan, R.mipmap.n_fujin, R.mipmap.n_paizhao, R.mipmap.n_wode};
    private int[] mIconSelectIds={R.mipmap.y_suibiankankan, R.mipmap.y_fujin, R.mipmap.y_paizhao, R.mipmap.y_wode};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FirstTabFragment firstTabFragment;
    private SecondMapFragment_ secondMapFragment;
    private ThirdTabFragment thirdTabFragment;
    private MoreWindow mMoreWindow;

    private Fragment LastFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        initTab();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    //初始化碎片
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        int currentTabPosition=1;
        if(null==savedInstanceState){
            firstTabFragment = new FirstTabFragment();
            secondMapFragment = new SecondMapFragment_();
            thirdTabFragment = new ThirdTabFragment();

            fragmentTransaction.add(R.id.fl_body,firstTabFragment,"firstTabFragment");
            fragmentTransaction.add(R.id.fl_body,secondMapFragment,"secondTabFragment");
            fragmentTransaction.add(R.id.fl_body,thirdTabFragment,"thirdTabFragment");
        }else{
            firstTabFragment= (FirstTabFragment) getSupportFragmentManager().findFragmentByTag("firstTabFragment");
            secondMapFragment= (SecondMapFragment_) getSupportFragmentManager().findFragmentByTag("secondMapFragment");
            thirdTabFragment= (ThirdTabFragment) getSupportFragmentManager().findFragmentByTag("thirdTabFragment");
        }
        fragmentTransaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        mMoreWindow = new MoreWindow(this);
        mMoreWindow.init(tabLayout);
        switch (position){
            case 0:
                transaction.show(firstTabFragment);
                transaction.hide(secondMapFragment);
                transaction.hide(thirdTabFragment);
                LastFragment = firstTabFragment;
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(secondMapFragment);
                transaction.hide(firstTabFragment);
                transaction.hide(thirdTabFragment);
                LastFragment = secondMapFragment;
                transaction.commitAllowingStateLoss();
                break;
            case 2:
//                transaction.hide(secondMapFragment);
//                transaction.hide(firstTabFragment);
//                transaction.hide(thirdTabFragment);
                transaction.commitAllowingStateLoss();
                showMoreWindow();

                break;
            case 3:
                transaction.show(thirdTabFragment);
                transaction.hide(secondMapFragment);
                transaction.hide(firstTabFragment);
                LastFragment = thirdTabFragment;
                transaction.commitAllowingStateLoss();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //1 显示tabLayout的角标 显示未读数,为负数是显示红点
        tabLayout.showMsg(3,-1);
        //设置位置
        tabLayout.setMsgMargin(3,0,2);
        //隐藏消息
        //tabLayout.hideMsg(1);
    }

    private void showMoreWindow() {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init(tabLayout);
        }

        mMoreWindow.showMoreWindow(tabLayout);
    }
}
