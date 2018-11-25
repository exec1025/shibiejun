package qyh.androidprojecthelper.contract;

import android.graphics.Bitmap;
import android.view.View;

import java.util.List;

import qyh.androidprojecthelper.base.BaseModel;
import qyh.androidprojecthelper.base.BaseView;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import rx.Observable;

/**
 * 描述：花卉信息回调
 * Created by lenovo on 2018/10/6.
 */

public interface FlowerContract {

//    interface Model extends BaseModel{
//        //请求获取数据
//        Observable<List<FlowerRecognitionResultBean>> getFlowerRecognitionResultByImage(String accessToken,String image);
//    }

    interface View extends BaseView{
        //void showListData(FlowerRecognitionResultBean listData);
        void showListData(List<FlowerRecognitionResultBean.ResultBean> listData);
    }

    interface Presenter{
        void getAccessToken();
        void getRecognitionResultByImage(Bitmap bitmap);
    }
}
