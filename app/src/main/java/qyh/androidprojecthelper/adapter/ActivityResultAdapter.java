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
import qyh.androidprojecthelper.base.baseadapter.BaseQuickAdapter;
import qyh.androidprojecthelper.base.baseadapter.BaseViewHolder;
import qyh.androidprojecthelper.bean.AnimalRecognitionResultBean;
import qyh.androidprojecthelper.bean.FirstBean;
import qyh.androidprojecthelper.bean.FlowerRecognitionResultBean;
import qyh.androidprojecthelper.utils.ImageLoaderUtils;

public class ActivityResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_EMPTY = 0;          //空布局
    private static final int TYPE_HEADER = 1;         //toolBar布局
    private static final int TYPE_ITEM_DETAIL = 2;    //大图详细布局(置信度最大)
    private static final int TYPE_ITEM_SIMPLE = 3;    //小图简单布局
    private List<FlowerRecognitionResultBean.ResultBean> mItemList;
    private String type;

    public ActivityResultAdapter(List itemList, String type){
            this.mItemList = itemList;
            this.type = type;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;  //toolBar布局
        }

        //暂时将非植物识别的数据全设为空布局
        if (!type.equals("flower")){
            return TYPE_EMPTY;  //空布局
        }


        String title_name = mItemList.get(position-1).getName();
        if(title_name.equals("非植物")){
            return TYPE_EMPTY;  //空布局
        }
//        if(type.equals("flower")){
//            FlowerRecognitionResultBean.ResultBean flowerResultBean = (FlowerRecognitionResultBean.ResultBean)mItemList.get(position-1);
//            baike_infoBean = flowerResultBean.getBaike_info();
//        }else if (type.equals("animal")){
//            AnimalRecognitionResultBean.ResultBean animalResultBean = (AnimalRecognitionResultBean.ResultBean)mItemList.get(position-1);
//            baike_infoBean = animalResultBean.getBaike_info();
//        }
        Log.i("测试", "position="+(position-1));
        FlowerRecognitionResultBean.ResultBean.Baike_infoBean baike_infoBean = mItemList.get(position-1).getBaike_info();

        if(baike_infoBean == null){
            return TYPE_ITEM_SIMPLE;  //小图简单布局
        }else{
            return TYPE_ITEM_DETAIL;  //大图详细布局
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        final View view;
        switch(viewType){
            //空布局
            case TYPE_EMPTY:
                view = LayoutInflater.from(context).inflate(R.layout.show_empty, parent, false);
                return RecyclerItemViewHolder.newEmptyItemViewInstance(view);
            //toolBar布局
            case TYPE_HEADER :
                view = LayoutInflater.from(context).inflate(R.layout.activity_result_toolbar, parent, false);
                return new RecyclerHeaderViewHolder(view);
            //大图详细布局
            case TYPE_ITEM_DETAIL :
                view = LayoutInflater.from(context).inflate(R.layout.activity_result_detail, parent, false);
                return RecyclerItemViewHolder.newDetailItemViewInstance(view);
            //小图简单布局
            case TYPE_ITEM_SIMPLE :
                view = LayoutInflater.from(context).inflate(R.layout.activity_result_simple, parent, false);
                return RecyclerItemViewHolder.newSimpleItemViewInstance(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        int itemType = viewHolder.getItemViewType();

        if (itemType == TYPE_HEADER) {  //toolBar布局
            //RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            //String itemText = mItemList.get(position - 1); // header
            //holder.setItemText(itemText);
        }else if(itemType == TYPE_EMPTY){
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            TextView textView = holder.getView(R.id.show_empty_hint);
            textView.setText("非植物识别");
        }else if(itemType == TYPE_ITEM_DETAIL){   //大图详细布局
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            TextView tv_title = (TextView)holder.getView(R.id.tv_title);
            TextView tv_score = ((TextView) holder.getView(R.id.tv_score));
            TextView tv_description = ((TextView) holder.getView(R.id.tv_description));
            ImageView img = ((ImageView) holder.getView(R.id.image_result));

            Log.i("测试", mItemList.get(position - 1).toString());
            //设置图片
            String url = mItemList.get(position - 1).getBaike_info().getImage_url().toString();
            ImageLoaderUtils.display(holder.parent.getContext(), img, url);

            //设置标题
            tv_title.setText(mItemList.get(position - 1).getName());

            //设置置信度
            double score = Double.parseDouble(mItemList.get(position - 1).getScore());
            score = score * 100.00;
            String str_score = "置信度: ";
            if(score >= 0.01){
                str_score +=  String.format("%.2f", score) + "%";
            }else{
                str_score += " <0.01%";
            }
            tv_score.setText(str_score);

            String description = mItemList.get(position - 1).getBaike_info().getDescription();
            tv_description.setText("    "+description);
        }else if(itemType == TYPE_ITEM_SIMPLE){  //小图简单布局
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            TextView tv_title = (TextView)holder.getView(R.id.tv_title);
            TextView tv_score = ((TextView) holder.getView(R.id.tv_score));
            ImageView img = ((ImageView) holder.getView(R.id.image_result));

            //设置图片
            //暂时先用大图布局中的图片
            String url = mItemList.get(0).getBaike_info().getImage_url().toString();
            ImageLoaderUtils.display(holder.parent.getContext(), img, url);

            //设置标题
            tv_title.setText(mItemList.get(position - 1).getName());

            //设置置信度
            double score = Double.parseDouble(mItemList.get(position - 1).getScore());
            score = score * 100.00;
            String str_score = "置信度: ";
            if(score >= 0.01){
                str_score +=  String.format("%.2f", score) + "%";
            }else{
                str_score += " <0.01%";
            }
            tv_score.setText(str_score);
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
