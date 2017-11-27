package br.com.sailboat.canoe.view.about;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class AboutActivity extends BaseActivitySingleFragment<AboutFragment> {

    public static void start(Context context, ArrayList<RecyclerItem> recyclerItems) {
        Intent starter = new Intent(context, AboutActivity.class);
        starter.putExtra("RECYCLER_ITEMS", recyclerItems);
        context.startActivity(starter);
    }

    @Override
    protected AboutFragment newFragmentInstance() {
        return AboutFragment.newInstance((ArrayList<RecyclerItem>) getIntent().getSerializableExtra("RECYCLER_ITEMS"));
    }

}
