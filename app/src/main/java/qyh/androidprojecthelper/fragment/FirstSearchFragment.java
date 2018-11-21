package qyh.androidprojecthelper.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.activity.SearchDetailActivity;
import qyh.androidprojecthelper.base.BaseFragment;

/**
 * Created by lenovo on 2018/11/15.
 */

public class FirstSearchFragment extends BaseFragment{
    //@BindView(R.id.tv_search)
    public TextView tvSearch;
    //@BindView(R.id.ll_search)
    LinearLayout mSearchLayout;
    //@BindView(R.id.scrollView)
    public ScrollView mScrollView;
    boolean isExpand = false;
    //@BindView(R.id.iv_img)
    public ImageView ivImg;
    //@BindView(R.id.toolbar)
    public Toolbar toolbar;
    private TransitionSet mSet;

    private Context mContext;

    @Override
    protected int getLayoutResource() {
        mContext = getActivity();
        return R.layout.fragment_first_search;
    }

    @Override
    public void initPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("onCreateView:", "开始监听");
        View view = inflater.inflate(R.layout.fragment_first_search, container, false);
        initSearchView(savedInstanceState,view);
        return view;
    }

    @Override
    protected void initView() {

    }

    private void initSearchView(Bundle savedInstanceState,View view){
        //Log.e("initView:", "开始监听");
        tvSearch = (TextView)view.findViewById(R.id.tv_search);
        mSearchLayout = (LinearLayout)view.findViewById(R.id.ll_search);
        mScrollView = (ScrollView)view.findViewById(R.id.scrollView);
        ivImg = (ImageView)view.findViewById(R.id.iv_img);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        ButterKnife.bind(getActivity());
        //设置全屏透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup rootView = (ViewGroup) ((ViewGroup)view.findViewById(android.R.id.content)).getChildAt(0);
//            ViewCompat.setFitsSystemWindows(rootView,false);
//            rootView.setClipToPadding(true);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
        //Log.e("mScrollView:", "开始监听");
        //设置toolbar初始透明度为0
        toolbar.getBackground().mutate().setAlpha(0);
        //scrollview滚动状态监听
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                //改变toolbar的透明度
                changeToolbarAlpha();
                //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
                if (mScrollView.getScrollY() >=ivImg.getHeight() - toolbar.getHeight()  && !isExpand) {
                    expand();
                    isExpand = true;
                }
                //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
                else if (mScrollView.getScrollY()<=0&& isExpand) {
                    reduce();
                    isExpand = false;
                }

               // Log.d("mScrollView:", "滚动");
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void changeToolbarAlpha() {
        int scrollY = mScrollView.getScrollY();
        //快速下拉会引起瞬间scrollY<0
        if(scrollY<0){
            toolbar.getBackground().mutate().setAlpha(0);
            return;
        }
        //计算当前透明度比率
        float radio= Math.min(1,scrollY/(ivImg.getHeight()-toolbar.getHeight()*1f));
        //设置透明度
        toolbar.getBackground().mutate().setAlpha( (int)(radio * 0xFF));

    }


    private void expand() {
        //设置伸展状态时的布局
        tvSearch.setText("请输入搜索内容");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = LayoutParams.MATCH_PARENT;
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
       // Log.d("expand", "进入expand");
    }

    private void reduce() {
        //设置收缩状态时的布局
        tvSearch.setText("搜索");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = dip2px(80);
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
        Log.d("expand", "退出expand");
    }

    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);

    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

//    @Override
//    public void onResume() {
//        int id = getActivity().getIntent().getIntExtra("id", 0);
//        super.onResume();
//    }
}
