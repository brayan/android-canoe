package br.com.sailboat.canoe.helper;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.WindowManager;

public class RecyclerHelper {

    public static void scrollPostionToMiddleScreen(Context ctx, LinearLayoutManager manager, int position) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        manager.scrollToPositionWithOffset(position, (point.y / 4));
    }

    public static void scrollToTop(LinearLayoutManager manager) {
        manager.scrollToPositionWithOffset(0, 0);
    }

}
