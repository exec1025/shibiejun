package qyh.androidprojecthelper.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import qyh.androidprojecthelper.R;
import qyh.androidprojecthelper.base.BaseFragment;
import qyh.androidprojecthelper.dialog.CommomDialog;
import qyh.androidprojecthelper.view.Mine.MyOneLineView;


/**
 * 描述：
 * Created by czn on 2018/9/17.
 */
public class ThirdTabFragment extends BaseFragment implements MyOneLineView.OnRootClickListener, MyOneLineView.OnArrowClickListener{

    LinearLayout ll_mine_item;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_firtst;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ll_mine_item = (LinearLayout) getActivity().findViewById(R.id.ll_mine_item);
        //icon + 文字 + 箭头
        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_collection, "我的收藏", "", true)
                .setOnRootClickListener(this, 1));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_history, "识别历史", "", true)
                .setOnRootClickListener(this, 2));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_advice, "我要吐槽", "", true)
                .setOnRootClickListener(this, 3));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_about, "关于APP", "", true)
                .setDividerTopColor(R.color.gray)
                .showDivider(true,true)
                .setDividerTopHigiht(10)
                .setOnRootClickListener(this, 4));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_update, "版本更新", "", true)
                .setOnRootClickListener(this, 5));

        ll_mine_item.addView(new MyOneLineView(getActivity())
                .initMine(R.mipmap.ic_user, "账户设置", "", true)
                .setOnRootClickListener(this, 6));
//        //icon + 文字 + 文字 + 箭头
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initMine(R.mipmap.ic_launcher, "第二行", "第二行", true)
//                .setOnArrowClickListener(this, 2));
//        //icon + 文字 + 输入框
//        ll_mine_item.addView(new MyOneLineView(getActivity())
//                .initItemWidthEdit(R.mipmap.ic_launcher, "第三行", "这是一个输入框")
//                .setRootPaddingTopBottom(20, 20));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onRootClick(View view) {
        switch ((int) view.getTag()) {
            case 1:
                Toast.makeText(getActivity(), "尽请期待！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(), "尽请期待！", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getActivity(), "尽请期待！", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                new CommomDialog(this.getContext(), R.style.dialog, "15组：陈振南 庄易鸿 林泽辉      方正友 吴悠 郭振弘", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            dialog.dismiss();
                        }
                    }
                }).setTitle("关于APP").show();
                break;
            case 5:
                new CommomDialog(this.getContext(), R.style.dialog, "当前版本已是最新版本", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            dialog.dismiss();
                        }
                    }
                }).setTitle("版本更新").show();
                break;
            case 6:
                Toast.makeText(getActivity(), "尽请期待！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onArrowClick(View view) {

    }
}
