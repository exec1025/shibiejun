package qyh.androidprojecthelper.base;

import android.content.Context;

import qyh.androidprojecthelper.baserx.RxManager;


/**
 * 描述:基类presenter
 * Created by czn on 2018/9/17.
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
