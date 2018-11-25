package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;

import java.util.List;

import qyh.androidprojecthelper.base.BaseView;

/**
 * 描述：花卉信息回调
 * Created by czn on 2018/10/6.
 */

public interface FlowerContract {

//    interface Model extends BaseModel{
//        //请求获取数据
//        Observable<List<FlowerRecognitionResultBean>> getFlowerRecognitionResultByImage(String accessToken,String image);
//    }

    interface View<T> extends BaseView{
        //void showListData(FlowerRecognitionResultBean listData);
        //void showListData(String listData);
        void showListData(List<T> listData);
    }

    interface Presenter{
        void getAccessToken();
        void getRecognitionResultByImage(Bitmap bitmap);
    }

//    interface DataToWeb{
//        String showListData(String listData);
//    }
}
