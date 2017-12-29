package br.com.sailboat.canoe.recycler.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.recycler.item.SubheaderView;

public class SubheaderViewHolder extends BaseViewHolder {

    private TextView tvSubheader;


    public static SubheaderViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_subheader);
        return new SubheaderViewHolder(view);
    }


    public SubheaderViewHolder(View itemView) {
        super(itemView);
        initViews();
    }

    public void bind(SubheaderView item) {
        tvSubheader.setText(item.getText());
    }

    private void initViews() {
        tvSubheader = (TextView) itemView.findViewById(R.id.vh_subheader__tv__name);
    }

}
