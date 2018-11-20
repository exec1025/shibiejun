package qyh.androidprojecthelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView
{
    private OnScrollListener mOnScrollListener;

    /**
     * 是否用户手指触摸滑动
     */
    private boolean mIsTouch = false;

    public MyScrollView(Context context)
    {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mOnScrollListener != null)
        {
            mOnScrollListener.onScroll(t, mIsTouch ? OnScrollListener.SCROLL_STATE_TOUCH_SCROLL : OnScrollListener.SCROLL_STATE_FLING);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                mIsTouch = true;

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsTouch = false;

                break;
        }

        return super.onTouchEvent(ev);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener)
    {
        mOnScrollListener = onScrollListener;
    }

    public interface OnScrollListener
    {
        /**
         * 用户手指拖动滚动
         */
        int SCROLL_STATE_TOUCH_SCROLL = 0x0;

        /**
         * 惯性滑行滚动
         */
        int SCROLL_STATE_FLING = 0x1;

        /**
         * 滚动时的回调
         *
         * @param scrollY   Y方向滚动的距离
         * @param scroll_state 当前滚动状态：自由滚动或者手势拖动滚动
         */
        void onScroll(int scrollY, int scroll_state);
    }
}

