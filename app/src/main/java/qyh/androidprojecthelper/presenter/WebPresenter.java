package qyh.androidprojecthelper.presenter;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import qyh.androidprojecthelper.api.ApiService;
import qyh.androidprojecthelper.bean.WebRecognitionResultBean;
import qyh.androidprojecthelper.contract.WebContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/11/22.
 */

public class WebPresenter implements WebContract.Presenter{

    private WebContract.View mView;
    private ApiService mWebApiService;

    private String recognitioResultBean;

    public WebPresenter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.79.20.19:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Log.e("WebPresenter", "WebPresenter被创建");
        mWebApiService = retrofit.create(ApiService.class);
    }


    @Override
    public void getRecognitionResultByImage(Bitmap bitmap) {
        //String encodeResult = bitmapToString(bitmap);
        String Json = recognitioResultBean;
        String encodeResult ="test";
        //String Json = "test";

        Log.e("WebPresenter", "进入getRecognitionResultByImage");
        mWebApiService.getWebRecognitionResultByImage(encodeResult, Json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WebRecognitionResultBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:",e.toString());
                    }

                    @Override
                    public void onNext(WebRecognitionResultBean webrRecognitionResultBean) {
                        Log.e("onNext",webrRecognitionResultBean.toString());
                        //List<WebRecognitionResultBean.ResultBean> resultBeans = flowerRecognitionResultBean.getResult();
                        //mView.showListData(webrRecognitionResultBean.toString());
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
