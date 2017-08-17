package br.com.sailboat.canoe.view.template;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.recycler.RecyclerItem;

public class TemplateListViewModel {

    private static final List<RecyclerItem> list = new ArrayList<>();

    public static List<RecyclerItem> getList() {
        return list;
    }

}
