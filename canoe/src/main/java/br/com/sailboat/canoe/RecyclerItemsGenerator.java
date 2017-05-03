package br.com.sailboat.canoe;

import java.util.ArrayList;
import java.util.List;

public class RecyclerItemsGenerator {

    private List<List<Object>> lists = new ArrayList<>();

    public void add(String category, List<Object> list) {
        lists.add(list);
    }

}
