package br.com.sailboat.canoe.view.template;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.helper.ScrollHelper;
import br.com.sailboat.canoe.recycler.RecyclerItem;

public class TemplateListFragment extends BaseFragment<TemplateListPresenter> implements TemplateListPresenter.View {

    private View emptyList;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private RecyclerView recycler;
    private FloatingActionButton fab;

    protected int getLayoutId() {
        return R.layout.frg_list_template;
    }

    @Override
    protected TemplateListPresenter newPresenterInstance() {
        return new TemplateListPresenter(this);
    }

    @Override
    protected void initViews() {
        initEmptyView();
        initToolbar();
        initRecycler();
        initFab();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_template, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void updateList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void hideList() {
        recycler.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showEmptyList() {
        emptyList.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showList() {
        recycler.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideEmptyList() {
        emptyList.setVisibility(android.view.View.GONE);
    }

    @Override
    public void startInsertActivity() {
        // TODO
    }

    @Override
    public void startDetailsActivity(RecyclerItem taskId) {
        // TODO
    }

    @Override
    public void setTitle(String title) {
        // TODO
    }

    @Override
    public void expandToolbar() {
        appbar.setExpanded(true, true);
    }

    @Override
    public void startAboutActivity() {
        // TODO
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.menu_about: {
//                presenter.onClickMenuAbout();
//                return true;
//            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

    private void initEmptyView() {
        emptyList = getView().findViewById(R.id.emptyList);
    }

    private void initToolbar() {
        appbar = (AppBarLayout) getView().findViewById(R.id.appbar);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        toolbar.setTitle("Template List");

        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void initRecycler() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recycler.setAdapter(new TaskListAdapter(getPresenter()));
    }

    private void initFab() {
        fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                getPresenter().onClickFab();
            }
        });

        ScrollHelper.hideFabWhenScrolling(recycler, fab);
    }

}
