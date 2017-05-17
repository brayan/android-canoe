package br.com.sailboat.canoe.view.info;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class InfoActivity extends BaseActivitySingleFragment<InfoFragment> {

    public static void start(Activity activity, ArrayList<RecyclerItem> recyclerItems) {
        Intent starter = new Intent(activity, InfoActivity.class);
        starter.putExtra("RECYCLER_ITEMS", recyclerItems);
        activity.startActivity(starter);
    }

    @Override
    protected InfoFragment newFragmentInstance() {
        return InfoFragment.newInstance((ArrayList<RecyclerItem>) getIntent().getSerializableExtra("RECYCLER_ITEMS"));
    }

}
