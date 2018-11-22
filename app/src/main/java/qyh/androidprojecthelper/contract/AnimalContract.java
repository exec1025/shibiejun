package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;

import qyh.androidprojecthelper.base.BaseView;

/**
 * 描述:动物信息回调
 * Created by czn on 2018/11/22.
 */

public interface AnimalContract {
    interface View extends BaseView {
        //void showListData(FlowerRecognitionResultBean listData);
        void showListData(String listData);
    }

    interface Presenter{
        void getAccessToken();
        void getRecognitionResultByImage(Bitmap bitmap);
    }
}
