package qyh.androidprojecthelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.contract.SearchItemClickContract;


/**
 * 描述：搜索详情界面Adapter1
 * Created by czn on 2018/11/20.
 */

public class SearchContentAdapter1 extends RecyclerView.Adapter<SearchContentAdapter1.ViewHolder>{
    private Context context;
    private List<String> contentList;
    private SearchItemClickContract itemClickListener;


    public SearchContentAdapter1(Context context, List<String> contentList){
        this.context = context;
        this.contentList = contentList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search_item1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(v, position);
                }
            }
        });

        holder.tvContent.setText(contentList.get(position));
    }

    @Override
    public int getItemCount() {
        if (contentList == null){
            return 0;
        }
        return contentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }


    public void setItemClickListener(SearchItemClickContract itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
