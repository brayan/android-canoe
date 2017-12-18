package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class ImageViewColorHelper {

    public static void setColorOfVector(Context context, ImageView imageView, int colorId) {
        imageView.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public static void setColorOfImage(Context context, ImageView imageView, int colorId) {
        imageView.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setBackgroundTint(Context context, ImageView imageView, int colorId) {
        imageView.setBackgroundTintList(ContextCompat.getColorStateList(context, colorId));
    }

}
