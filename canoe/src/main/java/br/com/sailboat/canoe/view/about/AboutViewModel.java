package br.com.sailboat.canoe.view.about;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class AboutViewModel {

    private final List<RecyclerItem> recyclerItems = new ArrayList<>();

    public List<RecyclerItem> getRecyclerItems() {
        return recyclerItems;
    }

}
