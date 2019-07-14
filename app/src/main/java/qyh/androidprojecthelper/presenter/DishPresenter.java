package qyh.androidprojecthelper.presenter;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import qyh.androidprojecthelper.api.ApiService;
import qyh.androidprojecthelper.bean.AccessTokenBean;
import qyh.androidprojecthelper.bean.DishRecognitionResultBean;
import qyh.androidprojecthelper.contract.DishContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述： 菜品presenter

 * Created by czn on 2018/11/22.
 */

public class DishPresenter implements DishContract.Presenter{

    private DishContract.View mView;
    private ApiService mDishApiService;

    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String API_KEY = "nchiqbpGeMqjWsTGfjMW6wxH";
    private static final String SECRET_KEY = "EWoA44XREdhZ4Z68kdPBe405l9mPC0hd";
    private static final String ACCESS_TOKEN = "24.18ee539e7725e438aa312bcdeba10750.2592000.1563367354.282335-11483842";
    private static final float FILTER_THRESHOLD = (float) 0.95;

    public DishPresenter(DishContract.View mView){
        this.mView = mView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mDishApiService = retrofit.create(ApiService.class);
    }
    @Override
    public void getAccessToken() {
        mDishApiService.getAccessToken(CLIENT_CREDENTIALS, API_KEY, SECRET_KEY)
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

        mDishApiService.getDishRecognitionResultByImage(ACCESS_TOKEN, encodeResult, FILTER_THRESHOLD,baikenum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DishRecognitionResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:",e.toString());
                    }

                    @Override
                    public void onNext(DishRecognitionResultBean DishRecognitionResultBean) {
                        Log.e("onNext",DishRecognitionResultBean.toString());
                        List<DishRecognitionResultBean.ResultBean> resultBeans = DishRecognitionResultBean.getResult();
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
