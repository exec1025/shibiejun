package qyh.androidprojecthelper.presenter;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import qyh.androidprojecthelper.api.ApiService;
import qyh.androidprojecthelper.bean.AccessTokenBean;
import qyh.androidprojecthelper.bean.AnimalRecognitionResultBean;
import qyh.androidprojecthelper.contract.AnimalContract;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：动物presenter
 * Created by czn on 2018/11/22.
 */

public class AnimalPresenter implements AnimalContract.Presenter {
    private AnimalContract.View mView;
    private ApiService mAnimalApiService;

    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String API_KEY = "nchiqbpGeMqjWsTGfjMW6wxH";
    private static final String SECRET_KEY = "EWoA44XREdhZ4Z68kdPBe405l9mPC0hd";
    private static final String ACCESS_TOKEN = "24.265dae68a7fe517017a96da94f615d2c.2592000.1544703164.282335-11483842";

    //   private static final String ACCESS_TOKEN = "24.265dae68a7fe517017a96da94f615d2c.2592000.1544703164.282335-11483842";

    //   private static final String ACCESS_TOKEN = "24.406c471bac1aca7a25d2f71939880a9a.2592000.1541409596.282335-11483842";


    public AnimalPresenter(AnimalContract.View mView){
        this.mView = mView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://aip.baidubce.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mAnimalApiService = retrofit.create(ApiService.class);
    }
    @Override
    public void getAccessToken() {
        mAnimalApiService.getAccessToken(CLIENT_CREDENTIALS, API_KEY, SECRET_KEY)
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

        mAnimalApiService.getAnimalRecognitionResultByImage(ACCESS_TOKEN, encodeResult, baikenum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnimalRecognitionResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError:",e.toString());
                    }

                    @Override
                    public void onNext(AnimalRecognitionResultBean animalRecognitionResultBean) {
                        Log.e("onNext",animalRecognitionResultBean.toString());
                        List<AnimalRecognitionResultBean.ResultBean> resultBeans = animalRecognitionResultBean.getResult();
                        mView.showListData(resultBeans.toString());
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
