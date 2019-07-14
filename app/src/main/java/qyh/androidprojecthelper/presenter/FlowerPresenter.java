package qyh.androidprojecthelper.presenter;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import qyh.androidprojecthelper.api.ApiService;
import qyh.androidprojecthelper.bean.AccessTokenBean;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.contract.FlowerContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：花卉presenter
 * Created by czn on 2018/10/6.
 */

public class FlowerPresenter implements FlowerContract.Presenter{

    private FlowerContract.View mView;
    private ApiService mFlowerApiService;

    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String API_KEY = "nchiqbpGeMqjWsTGfjMW6wxH";
    private static final String SECRET_KEY = "EWoA44XREdhZ4Z68kdPBe405l9mPC0hd";
    private static final String ACCESS_TOKEN = "24.18ee539e7725e438aa312bcdeba10750.2592000.1563367354.282335-11483842";


    public FlowerPresenter(FlowerContract.View mView){
        this.mView = mView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFlowerApiService = retrofit.create(ApiService.class);
    }
    @Override
    public void getAccessToken() {
        mFlowerApiService.getAccessToken(CLIENT_CREDENTIALS, API_KEY, SECRET_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessTokenBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("Access token", "error");
                    }

                    @Override
                    public void onNext(AccessTokenBean accessTokenBean) {
                        Log.e("Access token",accessTokenBean.getAccess_token());
                    }
                });
    }

    @Override
    public void getRecognitionResultByImage(Bitmap bitmap) {
        String encodeResult = bitmapToString(bitmap);
        Integer baikenum = 1;

        mFlowerApiService.getFlowerRecognitionResultByImage(ACCESS_TOKEN, encodeResult, baikenum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlowerRecognitionResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(FlowerRecognitionResultBean flowerRecognitionResultBean) {
                        Log.e("onNext",flowerRecognitionResultBean.toString());
                        List<FlowerRecognitionResultBean.ResultBean> resultBeans = flowerRecognitionResultBean.getResult();
                        Log.e("进入1：",flowerRecognitionResultBean.toString());
                        //mDataPresenter.getDataToWeb(flowerRecognitionResultBean);
                        //mDataPresenter = new WebPresenter(flowerRecognitionResultBean.toString());
                        //mView.showListData(resultBeans.toString());
                        mView.showListData(resultBeans);
                        //mView.showListData(flowerRecognitionResultBean.toString());
                    }
                });
    }

    private String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

}
