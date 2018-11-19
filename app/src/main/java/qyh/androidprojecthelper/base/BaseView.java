package qyh.androidprojecthelper.base;
/**
 * 描述：
 * Created by czn on 2018/9/17.
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
