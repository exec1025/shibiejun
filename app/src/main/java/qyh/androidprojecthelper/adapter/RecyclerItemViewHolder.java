package qyh.androidprojecthelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import qyh.androidprojecthelper.R;

public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

    private View mItemView;
    View parent;

    public RecyclerItemViewHolder(final View parent, View itemView) {
        super(parent);
        this.parent = parent;
        mItemView = itemView;
    }

    public static RecyclerItemViewHolder newEmptyItemViewInstance(View parent) {
        View itemView =  parent.findViewById(R.layout.show_empty);
        return new RecyclerItemViewHolder(parent, itemView);
    }

    public static RecyclerItemViewHolder newLargeViewInstance(View parent) {
        View itemView =  parent.findViewById(R.layout.fragment_first_show_large);
        return new RecyclerItemViewHolder(parent, itemView);
    }

    public static RecyclerItemViewHolder newDetailItemViewInstance(View parent) {
        View itemView =  parent.findViewById(R.layout.activity_result_detail);
        return new RecyclerItemViewHolder(parent, itemView);
    }

    public static RecyclerItemViewHolder newSimpleItemViewInstance(View parent) {
        View itemView =  parent.findViewById(R.layout.activity_result_simple);
        return new RecyclerItemViewHolder(parent, itemView);
    }

    public <T extends View> T getView(int ViewId){
        return (T) parent.findViewById(ViewId);
    }

//    public void setItemText(CharSequence text) {
//        mItemTextView.setText(text);
//    }





//    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
//    private Context mContext;//上下文对象
//
//    public RecyclerItemViewHolder(Context ctx, View itemView) {
//        super(itemView);
//        mContext = ctx;
//        mViews = new SparseArray<View>();
//    }
//
//    private <T extends View> T findViewById(int viewId) {
//        View view = mViews.get(viewId);
//        if (view == null) {
//            view = itemView.findViewById(viewId);
//            mViews.put(viewId, view);
//        }
//        return (T) view;
//    }
//
//    public Context getmContext() {
//        return mContext;
//    }
//
//    public View getView(int viewId) {
//        return findViewById(viewId);
//    }
//
//    public TextView getTextView(int viewId) {
//        return (TextView) getView(viewId);
//    }
//
//    public Button getButton(int viewId) {
//        return (Button) getView(viewId);
//    }
//
//    public ImageView getImageView(int viewId) {
//        return (ImageView) getView(viewId);
//    }
//
//    public ImageButton getImageButton(int viewId) {
//        return (ImageButton) getView(viewId);
//    }
//
//    public EditText getEditText(int viewId) {
//        return (EditText) getView(viewId);
//    }
//
//    public RecyclerItemViewHolder setText(int viewId, String value) {
//        TextView view = findViewById(viewId);
//        view.setText(value);
//        return this;
//    }
//    public RecyclerItemViewHolder setVisible(int viewId, int visible) {
//        View view = findViewById(viewId);
//        view.setVisibility(visible);
//        return this;
//    }
//
//
//    public RecyclerItemViewHolder setBackground(int viewId, int resId) {
//        View view = findViewById(viewId);
//        view.setBackgroundResource(resId);
//        return this;
//    }
//
//    public RecyclerItemViewHolder setImageResource(int viewId, int resId) {
//        ImageView view = findViewById(viewId);
//        view.setImageResource(resId);
//        return this;
//    }
//
//    public RecyclerItemViewHolder setClickListener(int viewId, View.OnClickListener listener) {
//        View view = findViewById(viewId);
//        view.setOnClickListener(listener);
//        return this;
//    }

}
