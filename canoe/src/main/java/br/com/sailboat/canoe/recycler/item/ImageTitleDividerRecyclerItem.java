package br.com.sailboat.canoe.recycler.item;

import android.support.annotation.DrawableRes;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class ImageTitleDividerRecyclerItem implements RecyclerItem {

    private int viewType;
    private @DrawableRes int imageRes;
    private String title;

    private ImageTitleDividerRecyclerItem() {
    }

    public ImageTitleDividerRecyclerItem(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
