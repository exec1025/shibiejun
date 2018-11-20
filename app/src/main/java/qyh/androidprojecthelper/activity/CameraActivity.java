package qyh.androidprojecthelper.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.contract.FlowerContract;
import qyh.androidprojecthelper.presenter.FlowerPresenter;
import qyh.androidprojecthelper.view.MyScrollView;

/**
 * Created by lenovo on 2018/10/3.
 */

public class CameraActivity extends AppCompatActivity implements FlowerContract.View{

    private FlowerPresenter mFlowerPresenter;
    private MyScrollView mScrollView;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_takephoto_result);

        //启动图像选择器
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);

        mFlowerPresenter = new FlowerPresenter(this);
        mScrollView = new MyScrollView(this);
        initListener();
    }

    protected void initListener(){
        mScrollView.setOnScrollListener(new MyScrollView.OnScrollListener(){
            @Override
            public void onScroll(int scrollY, int state)
            {

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

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageView) findViewById(R.id.image_result)).setImageURI(result.getUri());
                try {
                    Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                    mFlowerPresenter.getRecognitionResultByImage(photo);
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
    public void showListData(String listData) {
        String listdata = listData.toString();
        ((TextView) findViewById(R.id.tv_description)).setText(listdata);
    }

//    @Override
//    public void showListData(FlowerRecognitionResultBean listData) {
//        String listdata = listData.toString();
//        ((TextView) findViewById(R.id.information_result)).setText(listdata);
//    }
}
