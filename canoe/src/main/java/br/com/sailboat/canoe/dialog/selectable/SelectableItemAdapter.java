package br.com.sailboat.canoe.dialog.selectable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class SelectableItemAdapter extends RecyclerView.Adapter<SelectableItemViewHolder> {

    private SelectableItemAdapter.Callback callback;

    public SelectableItemAdapter(Callback callback) {
        this.callback = callback;
    }

    public SelectableItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SelectableItemViewHolder.newInstance(parent, callback);
    }

    @Override
    public void onBindViewHolder(SelectableItemViewHolder holder, int position) {
        SelectableItem item = callback.getItems().get(position);
        holder.bindToView(item);
    }

    @Override
    public int getItemCount() {
        return callback.getItems().size();
    }

    public interface Callback extends SelectableItemViewHolder.Callback {
        List<SelectableItem> getItems();
    }

}
