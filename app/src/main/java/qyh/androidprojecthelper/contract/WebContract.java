package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;

import qyh.androidprojecthelper.base.BaseView;

/**
 * Created by lenovo on 2018/11/22.
 */

public interface WebContract {
    interface View extends BaseView {
        void showListData(String listData);
    }

    interface Presenter{
        void getRecognitionResultByImage(Bitmap bitmap);
    }

    interface DataPresenter<T>{
        void getDataToWeb(T data);
    }
}
