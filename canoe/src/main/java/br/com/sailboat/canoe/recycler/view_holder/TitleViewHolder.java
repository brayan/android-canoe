package br.com.sailboat.canoe.recycler.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem;

public class TitleViewHolder extends BaseViewHolder {

    private TextView tvName;


    public static TitleViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_title);
        return new TitleViewHolder(view);
    }


    public TitleViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void bindItem(TitleRecyclerItem title) {
        tvName.setText(title.getTitle());
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.vh_exercise_name__tv__name);
    }

}
