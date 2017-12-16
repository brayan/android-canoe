package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class ImageViewColorHelper {

    public static void setColorOfVector(Context context, ImageView imageView, int colorId) {
        imageView.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public static void setColorOfImage(Context context, ImageView imageView, int colorId) {
        imageView.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

}
