package br.com.sailboat.canoe.recycler.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.recycler.item.TipRecyclerItem;

public class TipViewHolder extends BaseViewHolder {

    private TipViewHolder.Callback callback;

    private TextView tvTip;
    private TextView tvGotIt;

    public static TipViewHolder newInstance(ViewGroup parent, TipViewHolder.Callback callback) {
        View view = inflateLayout(parent, R.layout.vh_tip);
        return new TipViewHolder(view, callback);
    }

    public TipViewHolder(View itemView, TipViewHolder.Callback callback) {
        super(itemView);
        setCallback(callback);
        initViews(itemView);
    }

    public void bindItem(TipRecyclerItem item) {
        tvTip.setText(item.getText());
    }

    private void initViews(View view) {
        tvTip = (TextView) view.findViewById(R.id.vh_tip__tv__tip);
        tvGotIt = (TextView) view.findViewById(R.id.vh_tip__tv__got_it);
        tvGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallback().onClickGotIt(getAdapterPosition());
            }
        });
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public interface Callback {
        void onClickGotIt(int position);
    }


}
