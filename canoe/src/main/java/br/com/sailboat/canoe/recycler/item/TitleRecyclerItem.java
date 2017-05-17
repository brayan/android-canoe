package br.com.sailboat.canoe.recycler.item;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class TitleRecyclerItem implements RecyclerItem {

    private int viewType;
    private String title;

    private TitleRecyclerItem() {
    }

    public TitleRecyclerItem(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
