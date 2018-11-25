package qyh.androidprojecthelper.contract;

/**
 * Created by czn on 2018/11/22.
 */

public interface DataToWebContract {

//    interface View extends BaseView {
//        void showListData(String listData);
//    }

    interface Presenter<T>{
        void getDataToWeb(T data);
    }
}
