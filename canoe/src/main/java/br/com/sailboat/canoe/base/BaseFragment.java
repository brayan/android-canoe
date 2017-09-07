package br.com.sailboat.canoe.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import br.com.sailboat.canoe.R;
import br.com.sailboat.canoe.dialog.ErrorDialog;
import br.com.sailboat.canoe.dialog.MessageDialog;
import br.com.sailboat.canoe.dialog.ProgressDialog;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.canoe.helper.StringHelper;
import br.com.sailboat.canoe.helper.UIHelper;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BasePresenter.View {

    protected T presenter;
    private ProgressDialog progressDialog;
    private boolean showingSearchView = true;
    private boolean searchViewOpen;
    protected AppBarLayout appbar;
    protected Toolbar toolbar;
    protected View emptyView;
    protected RecyclerView recycler;
    protected FloatingActionButton fab;
    private String emptyViewMessage1;
    private String emptyViewMessage2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setPresenter(newPresenterInstance());
        restoreViewModel(savedInstanceState);
        extractExtrasFromArguments();
        initEmptyViewMessages();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initToolbar();
        initEmptyView();
        initRecycler();
        initFab();
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case Activity.RESULT_OK: {
                onActivityResultOk(requestCode, data);
                break;
            }
            case Activity.RESULT_CANCELED: {
                onActivityResultCanceled(requestCode, data);
                break;
            }
        }

        postActivityResult(requestCode, data);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        initSearchViewMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveViewModelState(outState);
    }

    @Override
    public void closeKeyboard() {
        UIHelper.hideKeyboard(getActivity());
    }

    @Override
    public void setActivityToHideKeyboard() {
        UIHelper.setActivityToHideKeyboard(getActivity());
    }

    @Override
    public void showMessageDialog(String message) {
        MessageDialog.showMessage(getFragmentManager(), message, null);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeActivityWithResultOk() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultOk(Intent intent) {
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void closeActivityWithResultCanceled() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    @Override
    public void printLogAndShowDialog(Exception e) {
        if (getActivity() != null) {
            LogHelper.logException(e);
            ErrorDialog.showDialog(getFragmentManager(), getActivity(), e);
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog();
            progressDialog.show(getFragmentManager(), ProgressDialog.class.getName());
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void expandToolbar() {
        appbar.setExpanded(true, true);
    }

    @Override
    public void updateMenus() {
        getActivity().invalidateOptionsMenu();
    }

    private void restoreViewModel(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            getPresenter().restoreViewModel(savedInstanceState);
        }
    }

    private void extractExtrasFromArguments() {
        if (getArguments() != null && !getArguments().isEmpty()) {
            getPresenter().extractExtrasFromArguments(getArguments());
        }
    }

    private void initSearchViewMenu(Menu menu) {
        MenuItem menuSearchView = menu.findItem(R.id.menu_search);

        if (menuSearchView != null) {
            SearchView searchView = (SearchView) menuSearchView.getActionView();
            initListenerSearchView(searchView);
            updateSearchView(searchView, menuSearchView);
        }
    }

    private void initListenerSearchView(final SearchView searchView) {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSearchViewOpen(true);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setSearchViewOpen(false);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private Timer timer = new Timer();
            private final long DELAY = 500;

            @Override
            public boolean onQueryTextChange(final String text) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        presenter.setSearchText(text);
                        getPresenter().onQueryTextChange();
                    }
                }, DELAY);

                return true;

            }

            @Override
            public boolean onQueryTextSubmit(String texto) {
                return true;
            }
        });
    }

    private void updateSearchView(SearchView searchView, MenuItem menuSearchView) {

        menuSearchView.setVisible(isShowingSearchView());

        if (isSearchViewOpen()) {
            MenuItemCompat.expandActionView(menuSearchView);
            searchView.setQuery(presenter.getSearchText(), true);
            searchView.clearFocus();
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
        }

    }

    private void initToolbar() {
        appbar = (AppBarLayout) getView().findViewById(R.id.appbar);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        if (toolbar != null) {
            AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
            appCompatActivity.setSupportActionBar(toolbar);
            onInitToolbar();
        }
    }

    private void initEmptyView() {
        emptyView = getView().findViewById(R.id.ept_view);

        if (emptyView != null && StringHelper.isNotEmpty(getEmptyViewMessage1())) {
            ((TextView) emptyView.findViewById(R.id.ept_view__tv__message1)).setText(getEmptyViewMessage1());
        }

        if (emptyView != null && StringHelper.isNotEmpty(getEmptyViewMessage2())) {
            ((TextView) emptyView.findViewById(R.id.ept_view__tv__message2)).setText(getEmptyViewMessage2());
        }

    }

    private void initRecycler() {
        recycler = (RecyclerView) getView().findViewById(R.id.recycler);

        if (recycler != null) {
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            onInitRecycler();
        }
    }

    private void initFab() {
        fab = (FloatingActionButton) getView().findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    presenter.onClickFab();
                }
            });

            onInitFab();
        }
    }

    @Override
    public void removeItemFromRecycler(int position) {
        recycler.getAdapter().notifyItemRemoved(position);
    }

    @Override
    public void updateRecycler() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void hideRecycler() {
        recycler.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showRecycler() {
        recycler.setVisibility(android.view.View.VISIBLE);
    }

    public T getPresenter() {
        return presenter;
    }

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    protected abstract int getLayoutId();

    protected abstract T newPresenterInstance();

    protected abstract void initViews();

    protected void initEmptyViewMessages() {
    }

    protected void onActivityResultOk(int requestCode, Intent data) {
    }

    protected void onActivityResultCanceled(int requestCode, Intent data) {
    }

    protected void postActivityResult(int requestCode, Intent data) {
    }

    protected void onInitToolbar() {
    }

    protected void onInitRecycler() {
    }

    protected void onInitFab() {
    }

    @Override
    public boolean isShowingSearchView() {
        return showingSearchView;
    }

    @Override
    public void setShowingSearchView(boolean showingSearchView) {
        this.showingSearchView = showingSearchView;
    }

    public boolean isSearchViewOpen() {
        return searchViewOpen;
    }

    public void setSearchViewOpen(boolean searchViewOpen) {
        this.searchViewOpen = searchViewOpen;
    }

    public String getEmptyViewMessage1() {
        return emptyViewMessage1;
    }

    public void setEmptyViewMessage1(String emptyViewMessage1) {
        this.emptyViewMessage1 = emptyViewMessage1;
    }

    public String getEmptyViewMessage2() {
        return emptyViewMessage2;
    }

    public void setEmptyViewMessage2(String emptyViewMessage2) {
        this.emptyViewMessage2 = emptyViewMessage2;
    }
}
