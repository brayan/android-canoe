package br.com.sailboat.canoe.recycler.item;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class SubheaderView implements RecyclerItem {

    private int viewType;
    private String text;

    public SubheaderView(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
