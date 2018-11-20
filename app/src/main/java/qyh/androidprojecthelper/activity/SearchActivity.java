package qyh.androidprojecthelper.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 2018/11/15.
 */

public class SearchActivity extends AppCompatActivity {
// //   @BindView(R.id.tv_search)
//    TextView tvSearch;
////    @BindView(R.id.ll_search)
//    LinearLayout mSearchLayout;
////    @BindView(R.id.scrollView)
//    ScrollView mScrollView;
//    boolean isExpand = false;
//    @BindView(R.id.iv_img)
//    ImageView ivImg;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    private TransitionSet mSet;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_first_search);
//        ButterKnife.bind(this);
//        //设置全屏透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup rootView = (ViewGroup) ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
//            ViewCompat.setFitsSystemWindows(rootView,false);
//            rootView.setClipToPadding(true);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//        //设置toolbar初始透明度为0
//        toolbar.getBackground().mutate().setAlpha(0);
//        //scrollview滚动状态监听
//        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                //改变toolbar的透明度
//                changeToolbarAlpha();
//                //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
//                if (mScrollView.getScrollY() >=ivImg.getHeight() - toolbar.getHeight()  && !isExpand) {
//                    expand();
//                    isExpand = true;
//                }
//                //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
//                else if (mScrollView.getScrollY()<=0&& isExpand) {
//                    reduce();
//                    isExpand = false;
//                }
//            }
//        });
//    }

//    private void changeToolbarAlpha() {
//        int scrollY = mScrollView.getScrollY();
//        //快速下拉会引起瞬间scrollY<0
//        if(scrollY<0){
//            toolbar.getBackground().mutate().setAlpha(0);
//            return;
//        }
//        //计算当前透明度比率
//        float radio= Math.min(1,scrollY/(ivImg.getHeight()-toolbar.getHeight()*1f));
//        //设置透明度
//        toolbar.getBackground().mutate().setAlpha( (int)(radio * 0xFF));
//    }
//
//
//    private void expand() {
//        //设置伸展状态时的布局
//        tvSearch.setText("请输入搜索内容");
//        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = LayoutParams.MATCH_PARENT;
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //开始动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    private void reduce() {
//        //设置收缩状态时的布局
//        tvSearch.setText("搜索");
//        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = dip2px(80);
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //开始动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    void beginDelayedTransition(ViewGroup view) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            mSet = new AutoTransition();
//            mSet.setDuration(300);
//            android.transition.TransitionManager.beginDelayedTransition(view, mSet);
//        }
//
//    }
//
//    private int dip2px(float dpVale) {
//        final float scale = getResources().getDisplayMetrics().density;
//        return (int) (dpVale * scale + 0.5f);
//    }

}
