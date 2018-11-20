package qyh.androidprojecthelper.adapter;

import android.widget.ImageView;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.baseadapter.BaseQuickAdapter;
import qyh.androidprojecthelper.base.baseadapter.BaseViewHolder;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.utils.ImageLoaderUtils;

public class ActivityResultAdapter extends BaseQuickAdapter {

    public ActivityResultAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item, int position) {

    }
}
