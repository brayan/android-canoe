package br.com.sailboat.canoe.recycler;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.sailboat.canoe.R;

public class SwipeLeftRight extends ItemTouchHelper.Callback {

    private SwipeLeftRight.Callback callback;

    private Paint paint;

    public SwipeLeftRight(Context ctx, SwipeLeftRight.Callback callback) {
        this.callback = callback;

        paint = new Paint();
        paint.setColor(ContextCompat.getColor(ctx, R.color.md_blue_grey_200));
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        callback.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return super.getSwipeEscapeVelocity(defaultValue * 3);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return super.getSwipeVelocityThreshold(defaultValue * 2);
    }


    public interface Callback {
        void onItemDismiss(int position);
    }


}
