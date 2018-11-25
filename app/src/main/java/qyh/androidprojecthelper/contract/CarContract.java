package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;

import java.util.List;

import qyh.androidprojecthelper.base.BaseView;

/**
 * 描述：车型识别结果回调
 * Created by czn on 2018/11/22.
 */

public interface CarContract {
    interface View<T> extends BaseView {
        //void showListData(String listData);
        void showListData(List<T> listData);
    }

    interface Presenter{
        void getAccessToken();
        void getRecognitionResultByImage(Bitmap bitmap);
    }
}
