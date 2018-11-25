package qyh.androidprojecthelper.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.adapter.ActivityResultAdapter;
import qyh.androidprojecthelper.adapter.listener.HidingScrollListener;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.contract.AnimalContract;
import qyh.androidprojecthelper.contract.CarContract;
import qyh.androidprojecthelper.contract.DishContract;
import qyh.androidprojecthelper.contract.FlowerContract;
import qyh.androidprojecthelper.presenter.AnimalPresenter;
import qyh.androidprojecthelper.presenter.CarPresenter;
import qyh.androidprojecthelper.presenter.DishPresenter;
import qyh.androidprojecthelper.presenter.FlowerPresenter;
import qyh.androidprojecthelper.utils.ImageLoaderUtils;
import qyh.androidprojecthelper.view.MyScrollView;

/**
 * 描述：相机调用
 * Created by czn on 2018/10/3.
 */

public class CameraActivity extends AppCompatActivity implements FlowerContract.View,AnimalContract.View, CarContract.View, DishContract.View{

    private FlowerPresenter mFlowerPresenter;
    private AnimalPresenter mAnimalPresenter;
    private CarPresenter mCarPresenter;
    private DishPresenter mDishPresenter;

    private MyScrollView mScrollView;
    private int JudgeType;
    private String type;

    private Toolbar mToolbar;
    private ImageButton mFabButton;
    private TextView result_toolbar_title;
    private ImageView toolbar_back;
    private List<FlowerRecognitionResultBean.ResultBean> mList;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_takephoto_result);

        //启动图像选择器
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);


        Bundle extras = getIntent().getExtras();
        //Log.e("extras_type:", extras.getString("type"));
        type = extras.getString("type");
        if (type.equals("flower")){
            //Log.e("extras_type:", "return flower");
            mFlowerPresenter = new FlowerPresenter(this);
            JudgeType = 1;
        }else if (type.equals("animal")){
            //Log.e("extras_type:", "return animal");
            mAnimalPresenter = new AnimalPresenter(this);
            JudgeType = 2;
        }else if (type.equals("car")){
            //Log.e("extras_type:", "return car");
            mCarPresenter = new CarPresenter(this);
            JudgeType = 3;
        }else if (type.equals("dish")){
            //Log.e("extras_type:", "return car");
            mDishPresenter = new DishPresenter(this);
            JudgeType = 4;
        }

        mScrollView = new MyScrollView(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFabButton = (ImageButton) findViewById(R.id.fabButton);
        toolbar_back = (ImageView) findViewById(R.id.toolbar_back);
        result_toolbar_title = (TextView) findViewById(R.id.result_toolbar_title);
        result_toolbar_title.setText("识别结果");
        initListener();
    }

    protected void initListener(){
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("测试", "回到顶部被点击");
                recyclerView.smoothScrollToPosition(0);
            }
        });
        mScrollView.setOnScrollListener(new MyScrollView.OnScrollListener(){
            @Override
            public void onScroll(int scrollY, int state){

            }
        });

    }



    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActivityResultAdapter activityResultAdapter = new ActivityResultAdapter(mList, type);
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


    /**
     * 启动图像选择器
     */
//    public void onSelectImageClick(View view){
//        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 裁剪图片并返回结果
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
            //    ((ImageView) findViewById(R.id.image_result)).setImageURI(result.getUri());
                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                    if (JudgeType == 1){
                        mFlowerPresenter.getRecognitionResultByImage(photo);
                    }else if (JudgeType == 2){
                        mAnimalPresenter.getRecognitionResultByImage(photo);
                    }else if (JudgeType == 3){
                        mCarPresenter.getRecognitionResultByImage(photo);
                    }else if (JudgeType == 4){
                        mDishPresenter.getRecognitionResultByImage(photo);
                    }

                } catch (IOException e) {
                    Log.e("photo error:", "图片出错");
                }


//                Bitmap photo = BitmapFactory.decodeFile(result.getUri());
//                Toast.makeText(
//                        this, "裁剪成功,图片大小为:" + result.getSampleSize(), Toast.LENGTH_LONG)
//                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "裁剪失败，错误信息:" + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void showListData(List listData) {
        //String listdata = listData.toString();

        mList = listData;
        initRecyclerView();

//        TextView tv_title = ((TextView) findViewById(R.id.tv_title));
//        TextView tv_score = ((TextView) findViewById(R.id.tv_score));
//        TextView tv_description = ((TextView) findViewById(R.id.tv_description));
//        ImageView img = ((ImageView) findViewById(R.id.image_result));
//
//        String url = listData.get(0).getBaike_info().getImage_url().toString();
//        ImageLoaderUtils.display(this, img, url);
//
//        tv_title.setText(listData.get(0).getName());
//
//        double score = Double.parseDouble(listData.get(0).getScore().substring(0, 5));
//        score *= 100;
//        String str_score = "置信度: ";
//        if(score >= 0.01){
//            str_score += score + "%";
//        }else{
//            str_score += "<0.01%";
//        }
//        tv_score.setText(str_score);
//
//        String description = listData.get(0).getBaike_info().getDescription();
//        tv_description.setText(description);


    }

//    @Override
//    public void showListData(FlowerRecognitionResultBean listData) {
//        String listdata = listData.toString();
//        ((TextView) findViewById(R.id.information_result)).setText(listdata);
//    }
}
