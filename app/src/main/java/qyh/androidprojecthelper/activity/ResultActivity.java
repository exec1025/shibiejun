package qyh.androidprojecthelper.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.adapter.ActivityResultAdapter;
import qyh.androidprojecthelper.adapter.listener.HidingScrollListener;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;

/**
 * Created by lenovo on 2018/10/4.
 */

public class ResultActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageButton mFabButton;
    private List<FlowerRecognitionResultBean.ResultBean> mList;

    public ResultActivity( List<FlowerRecognitionResultBean.ResultBean> mList){
        mList = mList;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppThemeRed);
        super.onCreate(savedInstanceState);
        setContentView(R.id.activity_result_linearLayout);

        initToolbar();
        mFabButton = (ImageButton) findViewById(R.id.fabButton);
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActivityResultAdapter activityResultAdapter = new ActivityResultAdapter(mList);
        recyclerView.setAdapter(activityResultAdapter);

        recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

//    private List<FlowerRecognitionResultBean> createItemList() {
//        List<FlowerRecognitionResultBean> itemList = new ArrayList<>();
//        for(int i=0;i<20;i++) {
//            itemList.add("Item "+i);
//        }
//        return itemList;
//    }
}
