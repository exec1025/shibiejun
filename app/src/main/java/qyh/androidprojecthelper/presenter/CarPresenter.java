package qyh.androidprojecthelper.presenter;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import qyh.androidprojecthelper.api.ApiService;
import qyh.androidprojecthelper.bean.AccessTokenBean;
import qyh.androidprojecthelper.bean.CarRecognitionResultBean;
import qyh.androidprojecthelper.contract.CarContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：车型presenter
 * Created by czn on 2018/11/22.
 */

public class CarPresenter implements CarContract.Presenter{
    private CarContract.View mView;
    private ApiService mCarApiService;

    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String API_KEY = "nchiqbpGeMqjWsTGfjMW6wxH";
    private static final String SECRET_KEY = "EWoA44XREdhZ4Z68kdPBe405l9mPC0hd";
    private static final String ACCESS_TOKEN = "24.18ee539e7725e438aa312bcdeba10750.2592000.1563367354.282335-11483842";

    //   private static final String ACCESS_TOKEN = "24.265dae68a7fe517017a96da94f615d2c.2592000.1544703164.282335-11483842";

    //   private static final String ACCESS_TOKEN = "24.406c471bac1aca7a25d2f71939880a9a.2592000.1541409596.282335-11483842";


    public CarPresenter(CarContract.View mView){
        this.mView = mView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mCarApiService = retrofit.create(ApiService.class);
    }
    @Override
    public void getAccessToken() {
        mCarApiService.getAccessToken(CLIENT_CREDENTIALS, API_KEY, SECRET_KEY)
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

        mCarApiService.getCarRecognitionResultByImage(ACCESS_TOKEN, encodeResult, baikenum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarRecognitionResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:",e.toString());
                    }

                    @Override
                    public void onNext(CarRecognitionResultBean CarRecognitionResultBean) {
                        Log.e("onNext",CarRecognitionResultBean.toString());
                        List<CarRecognitionResultBean.ResultBean> resultBeans = CarRecognitionResultBean.getResult();
                        qyh.androidprojecthelper.bean.CarRecognitionResultBean.LocationResultBean location_result = CarRecognitionResultBean.getLocation_result();
                        String color_result = CarRecognitionResultBean.getColor_result();
                        //mView.showListData(resultBeans.toString()+",color_result:"+color_result+","+location_result.toString());
                        mView.showListData(resultBeans);
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
