package br.com.sailboat.canoe.view.info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class InfoFragment extends BaseFragment<InfoPresenter> implements InfoPresenter.View {

    private Toolbar toolbar;
    private RecyclerView recycler;


    public static InfoFragment newInstance(ArrayList<RecyclerItem> recyclerItems) {
        Bundle args = new Bundle();
        args.putSerializable("RECYCLER_ITEMS", recyclerItems);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_info;
    }

    @Override
    protected InfoPresenter newPresenterInstance() {
        return new InfoPresenter(this);
    }

    @Override
    protected void initViews(View view) {
        initToolbar(view);
        initRecyclerView(view);
    }

    @Override
    public void updateList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    private void initToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        toolbar.setTitle(R.string.info);
    }

    private void initRecyclerView(View view) {
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new InfoAdapter(getPresenter()));
    }


}
