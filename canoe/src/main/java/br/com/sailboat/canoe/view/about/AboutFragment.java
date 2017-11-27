package br.com.sailboat.canoe.view.about;

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

public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutPresenter.View {

    private Toolbar toolbar;
    private RecyclerView recycler;


    public static AboutFragment newInstance(ArrayList<RecyclerItem> recyclerItems) {
        Bundle args = new Bundle();
        args.putSerializable("RECYCLER_ITEMS", recyclerItems);
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frg_about;
    }

    @Override
    protected AboutPresenter newPresenterInstance() {
        return new AboutPresenter(this);
    }

    @Override
    protected void initViews() {
        initToolbar();
        initRecyclerView();
    }

    @Override
    public void updateList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    private void initToolbar() {
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        toolbar.setTitle(R.string.about);
    }

    private void initRecyclerView() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new AboutAdapter(getPresenter()));
    }


}
