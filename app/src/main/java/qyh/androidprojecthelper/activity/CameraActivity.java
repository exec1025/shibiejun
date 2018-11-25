package qyh.androidprojecthelper.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.contract.AnimalContract;
import qyh.androidprojecthelper.contract.CarContract;
import qyh.androidprojecthelper.contract.DishContract;
import qyh.androidprojecthelper.contract.FlowerContract;
import qyh.androidprojecthelper.presenter.AnimalPresenter;
import qyh.androidprojecthelper.presenter.CarPresenter;
import qyh.androidprojecthelper.presenter.DishPresenter;
import qyh.androidprojecthelper.presenter.FlowerPresenter;
import qyh.androidprojecthelper.presenter.WebPresenter;
import qyh.androidprojecthelper.view.MyScrollView;

/**
 * 描述：相机调用
 * Created by czn on 2018/10/3.
 */

public class CameraActivity extends AppCompatActivity implements FlowerContract.View, AnimalContract.View, CarContract.View, DishContract.View{

    private FlowerPresenter mFlowerPresenter;
    private AnimalPresenter mAnimalPresenter;
    private CarPresenter mCarPresenter;
    private DishPresenter mDishPresenter;
    private WebPresenter mWebPresenter;

    private MyScrollView mScrollView;
    private int JudgeType;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_takephoto_result);

        //启动图像选择器
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);


        Bundle extras = getIntent().getExtras();
        //Log.e("extras_type:", extras.getString("type"));
        String type = extras.getString("type");
        if (type.equals("flower")){
            //Log.e("extras_type:", "return flower");
            mFlowerPresenter = new FlowerPresenter(this);
            mWebPresenter = new WebPresenter();
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
            //Log.e("extras_type:", "return dish");
            mDishPresenter = new DishPresenter(this);
            JudgeType = 4;
        }

        mScrollView = new MyScrollView(this);
        initListener();
    }

    protected void initListener(){
        mScrollView.setOnScrollListener(new MyScrollView.OnScrollListener(){
            @Override
            public void onScroll(int scrollY, int state) {
            }
        });
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
                ((ImageView) findViewById(R.id.image_result)).setImageURI(result.getUri());
                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                    if (JudgeType == 1){
                        mFlowerPresenter.getRecognitionResultByImage(photo);
                        mWebPresenter.getRecognitionResultByImage(photo);
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
        String listdata = listData.toString();
        ((TextView) findViewById(R.id.tv_description)).setText(listdata);
    }

}
