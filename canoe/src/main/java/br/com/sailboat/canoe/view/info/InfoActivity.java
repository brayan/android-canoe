package br.com.sailboat.canoe.view.info;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class InfoActivity extends BaseActivitySingleFragment<InfoFragment> {

    public static void start(Context context, ArrayList<RecyclerItem> recyclerItems) {
        Intent starter = new Intent(context, InfoActivity.class);
        starter.putExtra("RECYCLER_ITEMS", recyclerItems);
        context.startActivity(starter);
    }

    @Override
    protected InfoFragment newFragmentInstance() {
        return InfoFragment.newInstance((ArrayList<RecyclerItem>) getIntent().getSerializableExtra("RECYCLER_ITEMS"));
    }

}
