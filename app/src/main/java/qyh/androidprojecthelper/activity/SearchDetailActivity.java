package qyh.androidprojecthelper.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.adapter.SearchContentAdapter1;
import qyh.androidprojecthelper.adapter.SearchContentAdapter2;
import qyh.androidprojecthelper.contract.SearchItemClickContract;
import qyh.androidprojecthelper.contract.SearchItemDeleteContract;

/**
 * 描述:搜索栏详情界面
 * Created by czn on 2018/11/20.
 */

public class SearchDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerViewRecommend1;
    private RecyclerView recyclerViewRecommend2;
    private RecyclerView recyclerViewHistory;
    private ImageView ivHistoryArrow;
    private TextView tvHistory;
    private TextView tvCancel;

    private ImageView ivHistoryDelete;
    private TextView tvHistoryDeleteFinish;

    private LinearLayout llShowRecommendBar;
    private LinearLayout llRecommend1Bar;
    private LinearLayout llRecommend2Bar;

    private List<String> recommend1ContentList = new ArrayList<>();
    private List<String> recommend2ContentList = new ArrayList<>();
    private List<String> historyContentList = new ArrayList<>();

    private SearchContentAdapter1 recommend1Adapter;
    private SearchContentAdapter2 recommend2Adapter;
    private SearchContentAdapter2 historyAdapter;

    private boolean isShowRecommend = true; //是否显示推荐词栏
    private boolean isHidePartialHistory = true; //是否隐藏部分历史,默认为true
    private boolean isInHistoryDelete = false; //是否处于删除历史模式

    private WebView webview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        initData();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews(){
        //沉浸式处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        View view = LayoutInflater.from(this).inflate(R.layout.fragment_baike, webview, false);
        webview = (WebView) view.findViewById(R.id.webview);
        recyclerViewRecommend1 = (RecyclerView) findViewById(R.id.rv_recommend_bar_1);
        recyclerViewRecommend2 = (RecyclerView) findViewById(R.id.rv_recommend_bar_2);
        recyclerViewHistory = (RecyclerView) findViewById(R.id.rv_recommend_bar_history);
        tvHistory = (TextView) findViewById(R.id.tv_history);
        ivHistoryArrow = (ImageView) findViewById(R.id.iv_arrow);
        ivHistoryDelete = (ImageView) findViewById(R.id.iv_delete);
        tvHistoryDeleteFinish = (TextView) findViewById(R.id.tv_finish);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        llShowRecommendBar = (LinearLayout) findViewById(R.id.linear_show_recommend);
        llRecommend1Bar = (LinearLayout) findViewById(R.id.linear_recommendbar1);
        llRecommend2Bar = (LinearLayout) findViewById(R.id.linear_recommendbar2);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DividerItemDecoration itemDecorationHor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL);
        itemDecorationHor.setDrawable(new ColorDrawable(Color.parseColor("#e0e0e0")));

        DividerItemDecoration itemDecorationVer = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorationVer.setDrawable(new ColorDrawable(Color.parseColor("#e0e0e0")));

        recommend1Adapter = new SearchContentAdapter1(getApplicationContext(), recommend1ContentList);
        recommend1Adapter.setItemClickListener(recommend1BarItemClickListener);

        GridLayoutManager gmRecommend1 = new GridLayoutManager(getApplicationContext(), 3);
        recyclerViewRecommend1.setLayoutManager(gmRecommend1);
        recyclerViewRecommend1.addItemDecoration(itemDecorationHor);
        recyclerViewRecommend1.addItemDecoration(itemDecorationVer);
        recyclerViewRecommend1.setAdapter(recommend1Adapter);

        recommend2Adapter = new SearchContentAdapter2(getApplicationContext(), recommend2ContentList);
        recommend2Adapter.setItemClickListener(recommend2BarItemClickListener);

        GridLayoutManager gmRecommend2 = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewRecommend2.setLayoutManager(gmRecommend2);
        recyclerViewRecommend2.addItemDecoration(itemDecorationHor);
        recyclerViewRecommend2.addItemDecoration(itemDecorationVer);
        recyclerViewRecommend2.setAdapter(recommend2Adapter);


        historyAdapter = new SearchContentAdapter2(getApplicationContext(), historyContentList);
        //历史记录先不显示全部
        int displayCount = historyContentList.size() > 4 ? 4 : historyContentList.size();
        historyAdapter.setDisplayCount(displayCount);
        historyAdapter.setItemClickListener(historyBarItemClickListener);
        historyAdapter.setItemDeleteListener(historyBarItemDeleteListener);

        GridLayoutManager gmHistory = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewHistory.setLayoutManager(gmHistory);
        recyclerViewHistory.addItemDecoration(itemDecorationHor);
        recyclerViewHistory.addItemDecoration(itemDecorationVer);
        recyclerViewHistory.setAdapter(historyAdapter);
    }

    /**
     * 初始化数据，到时可自定义绑定
     * */
    private void initData(){
        recommend1ContentList.add("兰花");
        recommend1ContentList.add("荷花");
        recommend1ContentList.add("仙人掌");

        recommend2ContentList.add("梅花");
        recommend2ContentList.add("牡丹");
        recommend2ContentList.add("菊花");
        recommend2ContentList.add("月季");
        recommend2ContentList.add("杜鹃花");
        recommend2ContentList.add("荷花");
        recommend2ContentList.add("茶花");
        recommend2ContentList.add("桂花");
        recommend2ContentList.add("水仙");
        recommend2ContentList.add("兰花");
        recommend2ContentList.add("松树");
        recommend2ContentList.add("文竹");
        recommend2ContentList.add("银杏");

        historyContentList.add("蓝色妖姬");
        historyContentList.add("炸弹树");
        historyContentList.add("鸡冠花");
        historyContentList.add("铃兰");
        historyContentList.add("紫罗兰");
        historyContentList.add("依米花");
        historyContentList.add("玫瑰");
        historyContentList.add("断肠草");
        historyContentList.add("曼陀罗花");
        historyContentList.add("月季");
        historyContentList.add("三叶草");
        historyContentList.add("七色堇");
        historyContentList.add("郁金香");
        historyContentList.add("金银花");
        historyContentList.add("车前草");
        historyContentList.add("昙花");
        historyContentList.add("牵牛花");
    }


    private SearchItemClickContract recommend1BarItemClickListener = new SearchItemClickContract() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(getApplicationContext(), String.format("你点击了-%s", recommend1ContentList.get(position)), Toast.LENGTH_SHORT).show();
            webview = (WebView) findViewById(R.id.webview);
            WebSettings setting = webview.getSettings();
            setting.setJavaScriptEnabled(true);//支持js
            setting.setSupportZoom(false);//不支持缩放
            setting.setBuiltInZoomControls(false);//不出现放大和缩小的按钮
            setting.setCacheMode(WebSettings.LOAD_NO_CACHE);//不设置网络缓存

//            webview.setWebViewClient(new WebViewClient() {});
//            webview.setWebChromeClient(new WebChromeClient() {});
            String url = "https://baike.baidu.com/item/"+recommend1ContentList.get(position);
            webview.loadUrl(url);
        }
    };

    private SearchItemClickContract recommend2BarItemClickListener = new SearchItemClickContract() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(getApplicationContext(), String.format("你点击了-%s", recommend2ContentList.get(position)), Toast.LENGTH_SHORT).show();
            WebSettings setting = webview.getSettings();
            setting.setJavaScriptEnabled(true);//支持js
            setting.setSupportZoom(false);//不支持缩放
            setting.setBuiltInZoomControls(false);//不出现放大和缩小的按钮
            setting.setCacheMode(WebSettings.LOAD_NO_CACHE);//不设置网络缓存

//            webview.setWebViewClient(new WebViewClient() {});
//            webview.setWebChromeClient(new WebChromeClient() {});
            String url = "https://baike.baidu.com/item/"+recommend1ContentList.get(position);
            webview.loadUrl(url);
        }
    };

    private SearchItemClickContract historyBarItemClickListener = new SearchItemClickContract() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(getApplicationContext(), String.format("你点击了-%s", historyContentList.get(position)), Toast.LENGTH_SHORT).show();
            WebSettings setting = webview.getSettings();
            setting.setJavaScriptEnabled(true);//支持js
            setting.setSupportZoom(false);//不支持缩放
            setting.setBuiltInZoomControls(false);//不出现放大和缩小的按钮
            setting.setCacheMode(WebSettings.LOAD_NO_CACHE);//不设置网络缓存

//            webview.setWebViewClient(new WebViewClient() {});
//            webview.setWebChromeClient(new WebChromeClient() {});
            String url = "https://baike.baidu.com/item/"+recommend1ContentList.get(position);
            Log.d("url::", url);
            webview.loadUrl(url);
        }
    };

    private SearchItemDeleteContract historyBarItemDeleteListener = new SearchItemDeleteContract() {
        @Override
        public void onItemDelete(View view, int position) {
            Toast.makeText(getApplicationContext(), String.format("你删除了-%s", historyContentList.get(position)), Toast.LENGTH_SHORT).show();
            historyContentList.remove(position);

            historyAdapter.setDisplayCount(historyContentList.size());
            historyAdapter.notifyDataSetChanged();
        }
    };

    private void onHistoryHideToggle(){
        isHidePartialHistory = !isHidePartialHistory;

        if (isHidePartialHistory){
            int displayCount = historyContentList.size() > 4 ? 4 : historyContentList.size();
            historyAdapter.setDisplayCount(displayCount);

            ivHistoryArrow.setRotation(180);
        }else{
            historyAdapter.setDisplayCount(historyContentList.size());
            ivHistoryArrow.setRotation(0);
        }

        historyAdapter.notifyDataSetChanged();
    }

    private void onHistoryDeleteToggle(){
        isInHistoryDelete = !isInHistoryDelete;

        if (isInHistoryDelete){
            //显示所有历史记录
            if (isHidePartialHistory){
                onHistoryHideToggle();
            }

            historyAdapter.setShowDelete(true);
            ivHistoryDelete.setVisibility(View.GONE);
            tvHistoryDeleteFinish.setVisibility(View.VISIBLE);
        }else{
            historyAdapter.setShowDelete(false);
            ivHistoryDelete.setVisibility(View.VISIBLE);
            tvHistoryDeleteFinish.setVisibility(View.GONE);
        }

        //在删除历史不能点击历史记录
        ivHistoryArrow.setVisibility(isInHistoryDelete ? View.GONE : View.VISIBLE);
        tvHistory.setClickable(!isInHistoryDelete);

        historyAdapter.notifyDataSetChanged();
    }

    private void onRecommendBarToggle(){
        isShowRecommend = !isShowRecommend;

        llRecommend1Bar.setVisibility(isShowRecommend ? View.VISIBLE : View.GONE);
        llRecommend2Bar.setVisibility(isShowRecommend ? View.VISIBLE : View.GONE);
        llShowRecommendBar.setVisibility(!isShowRecommend ? View.VISIBLE : View.GONE);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel:
                finish();
                break;

            case R.id.tv_history:
            case R.id.iv_arrow:
                onHistoryHideToggle();
                break;

            case R.id.iv_delete:
            case R.id.tv_finish:
                onHistoryDeleteToggle();
                break;

            case R.id.iv_recommend_toggle:
            case R.id.linear_show_recommend:
                onRecommendBarToggle();
                break;
        }
    }
}
