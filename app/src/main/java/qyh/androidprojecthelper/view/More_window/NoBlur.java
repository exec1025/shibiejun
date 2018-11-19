package qyh.androidprojecthelper.view.More_window;

/**
 * Created by lenovo on 2018/9/25.
 */

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.ms_square.etsyblur.BlurEngine;

/**
 * NoBlur.java
 *
 * @author Manabu-GT on 3/22/17.
 */
class NoBlur implements BlurEngine {

    @Override
    public Bitmap execute(@NonNull Bitmap inBitmap, boolean canReuseInBitmap) {
        return null;
    }

    @Override
    public Bitmap execute(@NonNull Bitmap inBitmap, @NonNull Bitmap outBitmap) {
        return null;
    }

    @Override
    public void execute(@NonNull Bitmap inBitmap, boolean canReuseInBitmap, @NonNull Callback callback) {
        callback.onFinished(null);
    }

    @Override
    public void execute(@NonNull Bitmap inBitmap, @NonNull Bitmap outBitmap, @NonNull Callback callback) {
        callback.onFinished(null);
    }

    @Override
    public void destroy() {

    }

    @Override
    public String methodDescription() {
        return "No Blur Effect";
    }
}

