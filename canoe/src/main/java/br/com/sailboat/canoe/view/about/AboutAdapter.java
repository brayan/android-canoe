package br.com.sailboat.canoe.view.about;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.canoe.recycler.view_holder.ImageTitleDividerViewHolder;
import br.com.sailboat.canoe.recycler.view_holder.LabelValueViewHolder;
import br.com.sailboat.canoe.recycler.item.ImageTitleDividerRecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem;

public class AboutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_IMAGE_TITLE = 0;
    public static final int VIEW_TYPE_LABEL_VALUE = 1;

    private AboutAdapter.Callback callback;

    public AboutAdapter(Callback callback) {
        this.callback = callback;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_IMAGE_TITLE: {
                return ImageTitleDividerViewHolder.newInstance(parent);
            }
            case VIEW_TYPE_LABEL_VALUE: {
                return LabelValueViewHolder.newInstance(parent);
            }
            default: {
                throw new RuntimeException("ViewHolder not found");
            }
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerItem item = callback.getRecyclerItems().get(position);

        switch (item.getViewType()) {
            case VIEW_TYPE_IMAGE_TITLE: {
                ((ImageTitleDividerViewHolder) holder).bindItem((ImageTitleDividerRecyclerItem) item);
                return;
            }
            case VIEW_TYPE_LABEL_VALUE: {
                ((LabelValueViewHolder) holder).bindItem((LabelValueRecyclerItem) item);
                return;
            }
            default: {
                throw new RuntimeException("ViewHolder not found");
            }
        }

    }

    @Override
    public int getItemCount() {
        return callback.getRecyclerItems().size();
    }

    @Override
    public int getItemViewType(int position) {
        return callback.getRecyclerItems().get(position).getViewType();
    }


    public interface Callback {
        List<RecyclerItem> getRecyclerItems();
    }


}
