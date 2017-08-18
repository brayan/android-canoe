package br.com.sailboat.canoe.dialog.selectable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;

public class SelectableItemViewHolder extends BaseViewHolder {

    private TextView tvName;
    private ImageView ivSelected;

    private SelectableItemViewHolder.Callback callback;


    public static SelectableItemViewHolder newInstance(ViewGroup parent, SelectableItemViewHolder.Callback callback) {
        View view = inflateLayout(parent, R.layout.vh_selectable_item);
        return new SelectableItemViewHolder(view, callback);
    }


    public SelectableItemViewHolder(View itemView, SelectableItemViewHolder.Callback callback) {
        super(itemView);
        this.callback = callback;
        initViews();
    }

    public void bindToView(SelectableItem item) {
        tvName.setText(item.getName());

        SelectableItem selectedItem = callback.getSelectedItem();
        if (selectedItem != null && selectedItem == item) {
            ivSelected.setVisibility(View.VISIBLE);
        } else {
            ivSelected.setVisibility(View.GONE);
        }

    }

    private void initViews() {
        tvName = (TextView) itemView.findViewById(R.id.vh_selectable_item__tv__name);
        ivSelected = (ImageView) itemView.findViewById(R.id.vh_selectable_item__img__selected);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClickItem(getAdapterPosition());
            }
        });
    }

    public interface Callback {
        void onClickItem(int position);
        SelectableItem getSelectedItem();
    }

}
