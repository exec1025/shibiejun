package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;

import qyh.androidprojecthelper.base.BaseView;

/**
 * 描述：车型识别结果回调
 * Created by czn on 2018/11/22.
 */

public interface CarContract {
    interface View extends BaseView {
        void showListData(String listData);
    }

    interface Presenter{
        void getAccessToken();
        void getRecognitionResultByImage(Bitmap bitmap);
    }
}
