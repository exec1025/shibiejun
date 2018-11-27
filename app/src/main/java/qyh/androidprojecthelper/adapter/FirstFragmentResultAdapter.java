package qyh.androidprojecthelper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.utils.ImageLoaderUtils;
import qyh.androidprojecthelper.utils.TemporaryDataUtil;

public class FirstFragmentResultAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM_LARGE = 1;    //大图详细布局
    private List<TemporaryDataUtil.TemporaryDataBean> mItemList;
    private String type;

    public FirstFragmentResultAdapter(List itemList, String type){
        this.mItemList = itemList;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
//        if(type.equals("flower")){
//
//        }else if(type.equals("animal")){
//
//        }
        return TYPE_ITEM_LARGE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view;
        switch(viewType){
            //大图布局
            case TYPE_ITEM_LARGE :
                view = LayoutInflater.from(context).inflate(R.layout.fragment_first_show_large, parent, false);
                return RecyclerItemViewHolder.newDetailItemViewInstance(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(position >= mItemList.size())
            return;

        int itemType = viewHolder.getItemViewType();

        if(itemType == TYPE_ITEM_LARGE){   //大图布局
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            TextView tv_title = (TextView)holder.getView(R.id.tv_title);
            TextView tv_description = ((TextView) holder.getView(R.id.tv_description));
            ImageView img = ((ImageView) holder.getView(R.id.img_large));

            Log.i("测试", mItemList.get(position).toString());
            //设置图片
            String url = mItemList.get(position).getImage_url().toString();
            ImageLoaderUtils.display(holder.parent.getContext(), img, url);

            //设置标题
            tv_title.setText(mItemList.get(position).getName());

            //设置描述
            tv_description.setText(mItemList.get(position).getDescription());
        }
    }

    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }


    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1; // header
    }


}
