package br.com.sailboat.canoe.recycler.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem;

public class LabelValueViewHolder extends BaseViewHolder {

    private TextView tvLabel;
    private TextView tvValue;


    public static LabelValueViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_label_value);
        return new LabelValueViewHolder(view);
    }


    public LabelValueViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void bindItem(LabelValueRecyclerItem item) {
        tvLabel.setText(item.getLabel());
        tvValue.setText(item.getValue());
    }

    private void initViews(View view) {
        tvLabel = (TextView) view.findViewById(R.id.vh_label_value__tv__label);
        tvValue = (TextView) view.findViewById(R.id.vh_label_value__tv__value);
    }
}
