package qyh.androidprojecthelper.launcher;

import android.content.Context;

/**
 * 描述：
 * Created by czn on 2018/9/17.
 */
public class Utils {
    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
