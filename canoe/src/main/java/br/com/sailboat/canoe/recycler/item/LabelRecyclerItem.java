package br.com.sailboat.canoe.recycler.item;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class LabelRecyclerItem implements RecyclerItem {

    private int viewType;
    private String label;

    private LabelRecyclerItem() {
    }

    public LabelRecyclerItem(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
