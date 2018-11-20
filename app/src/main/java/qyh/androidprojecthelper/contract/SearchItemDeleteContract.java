package qyh.androidprojecthelper.contract;

import android.view.View;

/**
 * 描述：搜索栏item删除监听接口
 * Created by lenovo on 2018/11/20.
 */

public interface SearchItemDeleteContract {
    void onItemDelete(View view, int position);
}
