package br.com.sailboat.canoe.recycler.view_holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseViewHolder;
import br.com.sailboat.canoe.recycler.item.ImageTitleDividerRecyclerItem;

public class ImageTitleDividerViewHolder extends BaseViewHolder {

    private ImageView imageView;
    private TextView tvTitle;


    public static ImageTitleDividerViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_image_title_divider);
        return new ImageTitleDividerViewHolder(view);
    }


    public ImageTitleDividerViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void bindItem(ImageTitleDividerRecyclerItem item) {
        imageView.setImageResource(item.getImageRes());
        tvTitle.setText(item.getTitle());
    }

    private void initViews(View view) {
        imageView = (ImageView) view.findViewById(R.id.vh_image_title_divider__img);
        tvTitle = (TextView) view.findViewById(R.id.vh_image_title_divider__tv__title);
    }

}
